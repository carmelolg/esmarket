package it.carmelolagamba.esmarket.persistence.storageManagement.dao;

import java.util.List;

import it.carmelolagamba.esmarket.domain.storageManagement.Store;
import it.carmelolagamba.esmarket.persistence.GenericDAO;

public interface StoreDAO extends GenericDAO<Store>{

	public Store getByName(String name);
	public Store getStoreByAccount(Long idAccount);
	public List<Store> getStoresContainingProuducts(List<String> productNames);
	public Store getLazyInitializedStore(Long storeID);
}
