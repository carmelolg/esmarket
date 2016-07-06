package it.carmelolagamba.esmarket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import it.carmelolagamba.esmarket.domain.account.Account;
import it.carmelolagamba.esmarket.domain.account.Administrator;
import it.carmelolagamba.esmarket.domain.sales.Order;
import it.carmelolagamba.esmarket.domain.sales.SaleItem;
import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.domain.storageManagement.Store;
import it.carmelolagamba.esmarket.persistence.DAOFactory;
import it.carmelolagamba.esmarket.persistence.account.dao.AccountDAO;
import it.carmelolagamba.esmarket.persistence.storageManagement.dao.ProductDAO;
import it.carmelolagamba.esmarket.persistence.storageManagement.dao.StoreDAO;

@Service
public class StoreService {

	private StoreDAO storeDAO;
	private AccountDAO accountDAO;
	private ProductDAO productDAO;

	public StoreService() {
		DAOFactory daoFactory = DAOFactory.instance(DAOFactory.HIBERNATE);
		storeDAO = daoFactory.getStoreDAO();
		accountDAO = daoFactory.getAccountDAO();
		productDAO = daoFactory.getProductDAO();
	}

	public List<Store> getAll(Long idAccount) {
		Account account = accountDAO.getById(idAccount);
		if (account instanceof Administrator) {
			return storeDAO.getAll();
		} else {
			List<Store> stores = new ArrayList<Store>();
			stores.add(storeDAO.getStoreByAccount(idAccount));
			return stores;
		}
	}

	public Store removeProductFromStore(Long idProd, Long storeID) {

		Product prod = productDAO.getById(idProd);
		Store store = storeDAO.getById(storeID);
		store.removeProductFromTheStore(prod);
		storeDAO.update(store);
		return store;
	}

	public List<Store> getStoresContainingOrderProducts(Order order) {

		List<String> productNames = new ArrayList<String>();

		for (SaleItem item : order.getItems()) {
			productNames.add(item.getProduct().getName());
		}

		return storeDAO.getStoresContainingProuducts(productNames);
	}

	public void removeStore(Long id) {
		storeDAO.remove(id);
	}

	public Store getById(Long id, boolean lazyLoad) {
		if (lazyLoad)
			return storeDAO.getById(id);
		return storeDAO.getLazyInitializedStore(id);
	}

	public void updateStore(Store store) {
		storeDAO.update(store);
	}

	public void saveStore(Store store) {

		storeDAO.save(store);
	}

	public Store getStoreByAccount(Long idAccount) {
		return storeDAO.getStoreByAccount(idAccount);
	}

}
