package it.carmelolagamba.esmarket.domain.sales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.carmelolagamba.esmarket.domain.account.Client;
import it.carmelolagamba.esmarket.domain.storageManagement.Product;

@Entity
@Table(name = "REVIEW")
public class Review {

	@Id
	@GeneratedValue
	@Column(name = "REVIEW_ID")
	private Long ID;
	
	@ManyToOne()
	@JoinTable(name = "REVIEW_AUTHOR", joinColumns = { @JoinColumn(name = "REVIEW_ID") }, inverseJoinColumns = { @JoinColumn(name = "ID_ACCOUNT") })
	private Client author;
	
	@OneToOne()
	@JoinTable(name = "REVIEW_PRODUCT", joinColumns = { @JoinColumn(name = "REVIEW_ID") }, inverseJoinColumns = { @JoinColumn(name = "PRODUCT_ID") })
	private Product reviewedProduct;
	
	@Column(name="REVIEW_TEXT")
	private String reviewText;

	
	
	public Review() {
		
		reviewText = new String();
		
	}

	public Client getAuthor() {
		return author;
	}

	public void setAuthor(Client author) {
		this.author = author;
	}

	public Product getReviewedProduct() {
		return reviewedProduct;
	}

	public void setReviewedProduct(Product reviewedProduct) {
		this.reviewedProduct = reviewedProduct;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public Long getID() {
		return ID;
	}
	
	
	
	
}
