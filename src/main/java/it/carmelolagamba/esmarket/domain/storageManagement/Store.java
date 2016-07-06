package it.carmelolagamba.esmarket.domain.storageManagement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import it.carmelolagamba.esmarket.domain.account.Account;

@Entity
@Table(name = "STORE")
public class Store {

	@Id
	@GeneratedValue
	@Column(name = "STORE_ID")
	private Long ID;
	@Column(name = "STORE_NAME")
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "Name must be alphanumeric")
	private String name;
	@Embedded
	@Valid
	private Position position;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "STORE_PRODUCTS", joinColumns = { @JoinColumn(name = "STORE_ID") }, inverseJoinColumns = { @JoinColumn(name = "PRODUCT_ID") })
	private List<Product> products;

	@OneToOne()
	@JoinTable(name = "STORE_ACCOUNT", joinColumns = { @JoinColumn(name = "STORE_ID") }, inverseJoinColumns = { @JoinColumn(name = "ID_ACCOUNT") })
	private Account account;

//	@Version
//	@Column(name = "VERSION")
//	private Integer version;
//	
//	public Integer getVersion() {
//		return version;
//	}
	
	public Store() {
		name = new String();
		position = new Position();
		products = new ArrayList<Product>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void removeProductFromTheStore(Product product) {
		products.remove(product);
	}

	public void addProductToTheStore(Product product) {
		products.add(product);
	}

	public Product getStoreProduct(Long ID) {
		for (Product product : products)
			if (product.getID().equals(ID))
				return product;
		return null;
	}

}
