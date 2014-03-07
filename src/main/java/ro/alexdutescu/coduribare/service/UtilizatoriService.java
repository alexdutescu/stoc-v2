package ro.alexdutescu.coduribare.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ro.alexdutescu.coduribare.model.Utilizator;

@Service
public interface UtilizatoriService {

public void create(Utilizator entity);
	
	public void edit(Utilizator entity);
	
	public void remove(Utilizator entity);
	
	public Utilizator find(Object id);
	
	public List<Utilizator> findAll();
	
	public List<Utilizator> findRange(int[] range);
	
	public int count();

	public Utilizator findUtilizatorByUsernameAndPassword(String username,
			String password);
}
