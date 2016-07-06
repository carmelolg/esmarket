package it.carmelolagamba.esmarket.controller.purchase;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

@Component
public class SearchForm {

	@Pattern(regexp = "[a-zA-Z0-9 ]*", message = "Product name must be alphanumeric")
	private String productName;
	@Max(value = 1000000, message = "Max Price value can't be more than 1000000")
	@Min(value = 0, message = "Max Price value can't be less than 0")
	private float maxPrice;
	@Max(value = 1000000, message = "Min Price value can't be more than 1000000")
	@Min(value = 0, message = "Min Price value can't be less than 0")
	private float minPrice;
	private String brand;
	private String category;
	private boolean open;

	public SearchForm() {
		this.setCategory("all");
		this.setBrand("all");
		this.setProductName("");
		this.setMaxPrice(100000);
		this.setMinPrice(0);
		this.setOpen(false);
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
