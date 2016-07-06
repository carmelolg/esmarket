package it.carmelolagamba.esmarket.domain.storageManagement;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import it.carmelolagamba.esmarket.domain.sales.Promotion;

@Entity
@Table(name = "PRODUCT")
public class Product {


	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_ID")
	private Long ID;
	@Column(name = "PRODUCT_NAME")
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "Name of product must be alphanumeric")
	private String name;
	@Column(name = "PRODUCT_CATEGORY")
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "Name of category must be alphanumeric")
	private String category;
	@Column(name = "PRODUCT_BRAND")
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "Name of brand must be alphanumeric")
	private String brand;
	@Column(name = "PRODUCT_UNIT_PRICE")
	private float unitPrice;
	@Column(name = "PRODUCT_QUANTITY")
	private int quantity;
	@Column(name = "PRODUCT_URL_IMAGE")
	private String image;
	@Column(name = "PRODUCT_DESCRIPTION")
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "Description must be alphanumeric")
	private String description;
	@Column(name = "PRODUCT_FORSALE")
	private boolean forSale;
	@Column(name = "PRODUCT_DATE")
	private Date productDate;

	@ManyToOne()
	@JoinTable(name = "PRODUCT_PROMOTION", joinColumns = { @JoinColumn(name = "PROMOTION_ID", nullable = true) }, inverseJoinColumns = { @JoinColumn(name = "PRODUCT_ID") })
	private Promotion promotion;

//	@Version
//	@Column(name = "VERSION")
//	private Integer version;
//	
//	public Integer getVersion() {
//		return version;
//	}

	public Long getID() {
		return ID;
	}
	
	public void setID(Long iD) {
		ID = iD;
	}

	public float getPriceWithDiscount() {
		if (promotion != null)
			return unitPrice - unitPrice * promotion.getDiscount() / 100;
		return unitPrice;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public boolean isForSale() {
		return forSale;
	}

	public void setForSale(boolean forSale) {
		this.forSale = forSale;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Product() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (forSale ? 1231 : 1237);
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((promotion == null) ? 0 : promotion.hashCode());
		result = prime * result + quantity;
		result = prime * result + Float.floatToIntBits(unitPrice);
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
		Product other = (Product) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (forSale != other.forSale)
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (promotion == null) {
			if (other.promotion != null)
				return false;
		} else if (!promotion.equals(other.promotion))
			return false;
		if (quantity != other.quantity)
			return false;
		if (Float.floatToIntBits(unitPrice) != Float.floatToIntBits(other.unitPrice))
			return false;
		return true;
	}

}
