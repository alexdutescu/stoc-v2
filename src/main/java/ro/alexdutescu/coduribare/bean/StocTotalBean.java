package ro.alexdutescu.coduribare.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import ro.alexdutescu.coduribare.model.StocTotal;
import ro.alexdutescu.coduribare.service.StocTotalService;

@Named("stocBean")
@Scope("session")
public class StocTotalBean implements Serializable{
	@Inject
	public StocTotalService stocService;
	
	public List<StocTotal> listStoc() {
		return stocService.findAll();
	}
}
