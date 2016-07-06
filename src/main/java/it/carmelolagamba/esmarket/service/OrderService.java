package it.carmelolagamba.esmarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.carmelolagamba.esmarket.domain.sales.Order;
import it.carmelolagamba.esmarket.domain.sales.SaleItem;
import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.persistence.DAOFactory;
import it.carmelolagamba.esmarket.persistence.sales.dao.OrderDAO;

@Service
public class OrderService {

	private OrderDAO orderDAO;

	public OrderService() {
		DAOFactory daoFactory = DAOFactory.instance(DAOFactory.HIBERNATE);
		orderDAO = daoFactory.getOrderDAO();
	}

	public void addSaleItemToOrder(Order order, Product product, int quantity) {
		SaleItem item = new SaleItem();
		item.setProduct(product);
		item.setQuantity(quantity);
		order.addItem(item);
	}

	public boolean saveOrder(Order order) {

		return orderDAO.validAndSaveOrder(order);
	}

	public List<Order> getOrdersByManager(Long id) {
		return orderDAO.getOrdersByManager(id);
	}

	public List<Order> getOrdersByClient(Long id) {
		return orderDAO.getOrdersByAccount(id);
	}

	public boolean currentUserOrdersContainsProduct(Long userID, Long productID) {
		if (orderDAO.checkProductOrder(userID, productID))
			return true;
		return false;
	}

	public void removeOrder(Long id) {
		orderDAO.remove(id);
	}

	public Order getOrderByID(Long idOrd, boolean lazyLoad) {
		if (lazyLoad)
			return orderDAO.getById(idOrd);
		return orderDAO.getLazyInitializedOrder(idOrd);

	}

	public void updateOrder(Order ord) {
		orderDAO.update(ord);
	}

}
