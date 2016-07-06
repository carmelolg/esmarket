package it.carmelolagamba.esmarket.domain.sales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.carmelolagamba.esmarket.domain.storageManagement.Product;

@Entity
@Table(name = "SALE_ITEM")
public class SaleItem {

	@Id
	@GeneratedValue
	@Column(name = "SALE_ITEM_ID")
	private Long ID;

	@Column(name = "SALE_ITEM_QUANTITY")
	private int quantity;

	@ManyToOne()
	@JoinTable(name = "SALE_ITEM_PRODUCT", joinColumns = { @JoinColumn(name = "SALE_ITEM_ID") }, inverseJoinColumns = { @JoinColumn(name = "PRODUCT_ID") })
	private Product product;

//	@Version
//	@Column(name = "VERSION")
//	private Integer version;
//	
//	public Integer getVersion() {
//		return version;
//	}

	
	public SaleItem() {
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getID() {
		return ID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaleItem other = (SaleItem) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	
	
	

}
