package ro.alexdutescu.coduribare.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ro.alexdutescu.coduribare.model.StocTotal;

@Service
public interface StocTotalService {

	public void create(StocTotal entity);
	
	public void edit(StocTotal entity);
	
	public void remove(StocTotal entity);
	
	public StocTotal find(Object id);
	
	public List<StocTotal> findAll();
	
	public List<StocTotal> findRange(int[] range);
	
	public int count();

	List<StocTotal> findStocByFilters(String locatie, Integer cod, String nume,
			String producator, Boolean scanat);
	
}
