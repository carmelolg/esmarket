package it.carmelolagamba.esmarket.persistence.sales.dao;

import java.util.Date;
import java.util.List;

import it.carmelolagamba.esmarket.domain.sales.Order;
import it.carmelolagamba.esmarket.domain.sales.Order.OrderStatus;
import it.carmelolagamba.esmarket.persistence.GenericDAO;

public interface OrderDAO extends GenericDAO<Order>{

	public List<Order> getOrdersByAccount(Long idAccount);
	public List<Order> getOrdersByDate(Date startDate, Date endDate);
	public List<Order> getOrdersByOrderStatus(OrderStatus orderStatus);
	public boolean validAndSaveOrder(Order order);
	public List<Order> getOrdersByManager(Long idAccount);
	public Order getLazyInitializedOrder(Long orderID);
	public boolean checkProductOrder(Long userID, Long productID);
}
