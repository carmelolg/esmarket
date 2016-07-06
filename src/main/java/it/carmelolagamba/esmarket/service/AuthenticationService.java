package it.carmelolagamba.esmarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.carmelolagamba.esmarket.domain.account.Account;
import it.carmelolagamba.esmarket.domain.account.Client;
import it.carmelolagamba.esmarket.domain.account.ManagerShop;
import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.domain.storageManagement.Store;
import it.carmelolagamba.esmarket.persistence.DAOFactory;
import it.carmelolagamba.esmarket.persistence.account.dao.AccountDAO;
import it.carmelolagamba.esmarket.persistence.storageManagement.dao.ProductDAO;
import it.carmelolagamba.esmarket.persistence.storageManagement.dao.StoreDAO;

@Service
public class AuthenticationService {

	private AccountDAO accountDAO;
	private StoreDAO storeDAO;
	private ProductDAO productDAO;

	public AuthenticationService() {
		DAOFactory daoFactory = DAOFactory.instance(DAOFactory.HIBERNATE);
		accountDAO = daoFactory.getAccountDAO();
		storeDAO = daoFactory.getStoreDAO();
		productDAO = daoFactory.getProductDAO();
	}

	public void updateUserINFO(Account account) {
		accountDAO.update(account);
	}

	public boolean registerUser(Client client) {
		if (accountDAO.findMail(client.geteMail())) {
			return false;
		} else {
			accountDAO.save(client);
			return true;
		}
	}

	public Account loginAccount(String mail, String password) {

		return accountDAO.findAccountByAuthenticationINFO(mail, password);

	}

	public Account getAdministrator() {
		return accountDAO.getAdiministrator();
	}

	public boolean setManager(Long idAccount) {
		Account account = accountDAO.getById(idAccount);
		if (account instanceof ManagerShop) {
			Account acc = new Client();
			acc.setUsername(account.getUsername());
			acc.setPassword(account.getPassword());
			acc.setAddress(account.getAddress());
			acc.seteMail(account.geteMail());
			List<Product> products = storeDAO.getStoreByAccount(idAccount).getProducts();
			for (Product p : products) {
				p.setForSale(false);
				productDAO.update(p);
			}
			storeDAO.remove(storeDAO.getStoreByAccount(idAccount).getID());
			accountDAO.remove(idAccount);
			accountDAO.save(acc);

			return false;

		} else if (account instanceof Client) {
			Account acc = new ManagerShop();
			acc.setUsername(account.getUsername());
			acc.setPassword(account.getPassword());
			acc.setAddress(account.getAddress());
			acc.seteMail(account.geteMail());
			accountDAO.remove(idAccount);
			accountDAO.save(acc);
			Store store = new Store();
			store.setAccount(acc);
			store.setName("Storeof" + acc.getUsername());
			storeDAO.save(store);
			return true;
		}
		return false;
	}

	public List<Account> getManagers() {
		return accountDAO.getManagers();
	}

	public void removeAccount(Long id) {
		accountDAO.remove(id);
	}

	public List<Account> getAll() {
		return accountDAO.getAll();
	}

	public Account getById(Long id) {
		return accountDAO.getById(id);
	}

}
