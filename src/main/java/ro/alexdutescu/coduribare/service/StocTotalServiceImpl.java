package ro.alexdutescu.coduribare.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import ro.alexdutescu.coduribare.model.StocTotal;

@Named
public class StocTotalServiceImpl extends AbstractFacade<StocTotal> implements StocTotalService{

	@PersistenceContext
    private EntityManager em;
	
	public StocTotalServiceImpl() {
		super(StocTotal.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<StocTotal> findStocByFilters(String locatie, Integer cod, String nume, String producator, Boolean scanat) {
        String query = "SELECT s FROM StocTotal s";
        if (locatie != null && !locatie.toLowerCase().equals("god_mode") && locatie.length() > 0) {
			query = query.concat(" where lower(s.locatie) = '" + locatie.toLowerCase() + "' and ");
			if(cod != null) {query = query.concat("lower(s.cod)="+ cod +" and ");}
			if(!scanat) {query = query.concat("(lower(s.codBare) IS  NULL  OR lower(s.codBare) = '') and ");}
			if(nume != null && !"".equals(nume)){ query = query.concat("lower(s.nume) LIKE '"+ nume +"%' and ");}
			if(producator != null && !"".equals(producator)){ query = query.concat("lower(s.producator) LIKE '"+ producator+"%' and ");}
			
//			if(query.length() > 14) {query =  query.substring(0,query.length()-4);}
			query =  query.substring(0,query.length()-4);
			
		}else {
			if (cod != null || !scanat || (nume != null && nume.length() > 0) || (producator != null && producator.length() > 0)) query = query.concat(" WHERE ");
			if(cod != null) {query = query.concat("lower(s.cod)="+ cod +" and ");}
			if(!scanat) {query = query.concat("(lower(s.codBare) IS  NULL  OR lower(s.codBare) = '') and ");}
			if(nume != null && nume.length() > 0){ query = query.concat("lower(s.nume) LIKE '"+ nume +"%' and ");}
			if(producator != null && producator.length() > 0){ query = query.concat("lower(s.producator) LIKE '"+ producator+"%' and ");}

//			if(query.length() > 14) {query =  query.substring(0,query.length()-4);}
			if (cod != null || !scanat || (nume != null && nume.length() > 0) || (producator != null && producator.length() > 0)) {
                            query =  query.substring(0,query.length()-4);
                        }
		}
        query = query.concat(" ORDER BY s.nume");
        Logger.getLogger(StocTotalServiceImpl.class.toString()).log(Level.INFO, query);
        return em.createQuery(query, StocTotal.class).getResultList();
    }

}
