package it.carmelolagamba.esmarket.persistence.account.dao;

import java.util.List;

import it.carmelolagamba.esmarket.domain.account.Account;
import it.carmelolagamba.esmarket.persistence.GenericDAO;

public interface AccountDAO extends GenericDAO<Account>{	

	public boolean findMail(String mail);
	public Account findAccountByAuthenticationINFO(String mail, String password);
	public List<Account> getManagers();
	public Account getAdiministrator();
}
