package ro.alexdutescu.coduribare.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import ro.alexdutescu.coduribare.model.Utilizator;
import ro.alexdutescu.coduribare.service.UtilizatoriService;

@Named("login")
@Scope("session")
public class LoginBean implements Serializable {
	@Inject UtilizatoriService us;
//  @Inject PageController page;
  
  private String username, password;
  private Utilizator utilizator;

  public Utilizator getUtilizator() {
      return utilizator;
  }

  public void setUtilizator(Utilizator utilizator) {
      this.utilizator = utilizator;
  }

  public String getUsername() {
      return username;
  }

  public void setUsername(String username) {
      this.username = username;
  }

  public String getPassword() {
      return password;
  }

  public void setPassword(String password) {
      this.password = password;
  }
  
  public void redirect() throws IOException {
      
          utilizator = us.findUtilizatorByUsernameAndPassword(username, password);
      
      
      if (utilizator != null) {
          Faces.invalidateSession();
          Faces.setSessionAttribute("utilizator", utilizator);
          
          Faces.redirect("index.xhtml");
      }
      else RequestContext.getCurrentInstance().execute("loginFailedWidget.show()");
  }
  
  public void logout() throws IOException {
      Faces.invalidateSession();
      Faces.redirect("login.xhtml");
  }
}

