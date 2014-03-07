package ro.alexdutescu.coduribare.service;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ro.alexdutescu.coduribare.model.Utilizator;

@Named
public class UtilizatoriServiceImpl extends AbstractFacade<Utilizator> implements UtilizatoriService {

	@PersistenceContext
    private EntityManager em;
	
	public UtilizatoriServiceImpl() {
		super(Utilizator.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public Utilizator findUtilizatorByUsernameAndPassword(String username,	String password) {
		Utilizator u = em.createQuery("SELECT u FROM Utilizatori u WHERE u.numeUtilizator=:usr AND u.parolaUtilizator=:psw", Utilizator.class)
                .setParameter("usr", username)
                .setParameter("psw", password)
                .getResultList().get(0);
        if(u != null) {return u;}
        return null;
	}
}
