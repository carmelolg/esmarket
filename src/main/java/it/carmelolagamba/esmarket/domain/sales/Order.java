package it.carmelolagamba.esmarket.domain.sales;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import it.carmelolagamba.esmarket.domain.account.Account;
import it.carmelolagamba.esmarket.domain.account.Address;
import it.carmelolagamba.esmarket.domain.account.Client;
import it.carmelolagamba.esmarket.domain.storageManagement.Position;

@Entity
@Table(name = "ORD")
public class Order {

	public enum OrderStatus {
		SUBMITTED, SENT, COMPLETED, CREATED
	}

	@Id
	@GeneratedValue
	@Column(name = "ORDER_ID")
	private Long ID;
	@Column(name = "ORDER_DATE")
	private Date requestDate;
	@Column(name = "ORDER_STATUS")
	private OrderStatus status;
	@Column(name = "ORDER_TOTAL_PRICE")
	private float totalPrice;
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "address", column = @Column(name = "POS_ADDRESS")),
			@AttributeOverride(name = "city", column = @Column(name = "POS_CITY")),
			@AttributeOverride(name = "region", column = @Column(name = "POS_REGION")), })
	private Position position;
	@Valid
	@Embedded
	private Address destinationAddress;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ORDER_ITEM", joinColumns = { @JoinColumn(name = "ORDER_ID") }, inverseJoinColumns = { @JoinColumn(name = "SALE_ITEM_ID") })
	private List<SaleItem> items;
	@ManyToOne()
	@JoinTable(name = "ORDER_OWNER", joinColumns = { @JoinColumn(name = "ORDER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ID_ACCOUNT") })
	private Client orderOwner;
	@ManyToOne()
	@JoinTable(name = "ORDER_MANAGER", joinColumns = { @JoinColumn(name = "ORDER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ID_ACCOUNT") })
	private Account orderManager;

//	@Version
//	@Column(name = "VERSION")
//	private Integer version;
//	
//	public Integer getVersion() {
//		return version;
//	}
	public Order() {
		requestDate = new Date();
		status = OrderStatus.CREATED;
		items = new ArrayList<SaleItem>();
		position = new Position();
		orderOwner = new Client();
		setTotalPrice();
	}

	public Address getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(Address destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public void removeItem(SaleItem item) {
		items.remove(item);
		setTotalPrice();
	}

	public void addItem(SaleItem item) {
		items.add(item);
		setTotalPrice();
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public float getTotalDiscount() {
		float total = 0;
		for (SaleItem i : items)
			total += (i.getQuantity() * i.getProduct().getPriceWithDiscount());
		return (totalPrice - total);
	}

	public void setTotalPrice() {
		totalPrice = 0;
		for (SaleItem i : items)
			totalPrice += (i.getQuantity() * i.getProduct().getUnitPrice());
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Client getOrderOwner() {
		return orderOwner;
	}

	public void setOrderOwner(Client orderOwner) {
		this.orderOwner = orderOwner;
	}

	public Account getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(Account orderManager) {
		this.orderManager = orderManager;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public List<SaleItem> getItems() {
		return items;
	}

	public void setItems(List<SaleItem> items) {
		this.items = items;
	}

	// @Override
	// public String toString() {
	// StringBuilder builder = new StringBuilder();
	// builder.append("Order ID: " + getID() + "\n");
	// builder.append("Status: " + getStatus() + "\n");
	// builder.append("Date: " + getRequestDate().toString() + "\n");
	// builder.append("Client: " + getOrderOwner().toString() + "\n");
	// builder.append("Manager: " + getOrderManager().toString() + "\n");
	// builder.append("Product List: \n");
	// // Iterator<Product> it = products.iterator();
	// // while(it != null){
	// // builder.append(products.iterator().next().toString()+"\n");
	// // }
	//
	// for (Product p : products) {
	// builder.append(p.toString() + "\n");
	// }
	//
	// builder.append("Total price: " + getTotalPrice() + "\n");
	// return builder.toString();
	// }

}
