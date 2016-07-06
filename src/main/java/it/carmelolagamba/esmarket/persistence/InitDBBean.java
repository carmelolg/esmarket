package it.carmelolagamba.esmarket.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import it.carmelolagamba.esmarket.domain.account.Address;
import it.carmelolagamba.esmarket.domain.account.Administrator;
import it.carmelolagamba.esmarket.domain.account.Client;
import it.carmelolagamba.esmarket.domain.account.ManagerShop;
import it.carmelolagamba.esmarket.domain.sales.Order;
import it.carmelolagamba.esmarket.domain.sales.Order.OrderStatus;
import it.carmelolagamba.esmarket.domain.sales.Promotion;
import it.carmelolagamba.esmarket.domain.sales.Review;
import it.carmelolagamba.esmarket.domain.sales.SaleItem;
import it.carmelolagamba.esmarket.domain.storageManagement.Position;
import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.domain.storageManagement.Store;
import it.carmelolagamba.esmarket.persistence.account.dao.AccountDAO;
import it.carmelolagamba.esmarket.persistence.sales.dao.OrderDAO;
import it.carmelolagamba.esmarket.persistence.sales.dao.PromotionDAO;
import it.carmelolagamba.esmarket.persistence.sales.dao.ReviewDAO;
import it.carmelolagamba.esmarket.persistence.sales.dao.SaleItemDAO;
import it.carmelolagamba.esmarket.persistence.storageManagement.dao.ProductDAO;
import it.carmelolagamba.esmarket.persistence.storageManagement.dao.StoreDAO;

@Component
public class InitDBBean {

	@PostConstruct
	public void fillDB() {
		DAOFactory daoFactory = DAOFactory.instance(DAOFactory.HIBERNATE);
		ProductDAO productDAO = daoFactory.getProductDAO();
		PromotionDAO promoDAO = daoFactory.getPromotionDAO();
		AccountDAO accountDAO = daoFactory.getAccountDAO();
		StoreDAO storeDAO = daoFactory.getStoreDAO();
		OrderDAO orderDAO = daoFactory.getOrderDAO();
		SaleItemDAO saleDAO = daoFactory.getSaleItemDAO();
		ReviewDAO reviewDAO = daoFactory.getReviewDAO();

		Client client = new Client();
		Address address = new Address();
		address.setAddress("address1");
		address.setCity("city1");
		address.setRegion("region1");
		address.setState("state1");
		address.setZipCode("00000");

		client.setAddress(address);
		client.seteMail("a@a.a");
		client.setPassword("aaaaa");
		client.setUsername("username1");

		accountDAO.save(client);

		ManagerShop manager = new ManagerShop();

		manager.setAddress(address);
		manager.seteMail("m@a.a");
		manager.setPassword("aaaaa");
		manager.setUsername("manager");

		accountDAO.save(manager);

		Administrator admin = new Administrator();

		admin.setAddress(address);
		admin.seteMail("q@a.a");
		admin.setPassword("aaaaa");
		admin.setUsername("admin");

		accountDAO.save(admin);

		Promotion promotion = new Promotion();
		promotion.setDiscount(10);
		promotion.setStartPromo(new Date(System.currentTimeMillis()));
		promotion.setEndPromo(new Date(System.currentTimeMillis()));
		promoDAO.save(promotion);

		Store forSaleStore = new Store();
		forSaleStore.setAccount(manager);
		forSaleStore.setName("adminStore");
		forSaleStore.setPosition(new Position("adminAddress", "cityAddress", "regionAddress"));

		for (int i = 0; i < 50; i++) {
			Product product = new Product();
			product.setBrand("brand" + i);
			product.setCategory("category" + i / 20);
			product.setDescription("description" + i);
			product.setForSale(true);
			product.setImage("images/products/product.jpg");
			product.setName("name" + i);
			product.setPromotion(promotion);
			product.setQuantity(5);
			product.setUnitPrice(i + 10);
			product.setProductDate(new Date(System.currentTimeMillis()));
			product = productDAO.save(product);
			forSaleStore.addProductToTheStore(product);

			Review review = new Review();
			review.setAuthor(client);
			review.setReviewedProduct(product);
			review.setReviewText("Questa lumix è una figata");
			reviewDAO.save(review);
		}

		storeDAO.save(forSaleStore);

		Order order = new Order();
		order.setOrderManager(manager);
		order.setOrderOwner(client);
		order.setPosition(new Position("add", "city", "region"));
		List<SaleItem> saleItems = new ArrayList<SaleItem>();
		SaleItem saleItem = new SaleItem();
		List<Product> products2 = productDAO.getAll();
		saleItem.setProduct(products2.get(0));
		saleDAO.save(saleItem);
		saleItems.add(saleItem);
		order.setItems(saleItems);
		order.setStatus(OrderStatus.COMPLETED);
		order.setTotalPrice();
		order.setDestinationAddress(address);
		orderDAO.save(order);
	}

}
