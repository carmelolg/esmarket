package it.carmelolagamba.esmarket.domain.account;

import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

@Embeddable
public class Address {

	String address, city, region, state;

	@Pattern(regexp = "^\\d{5}$", message = "Postcode must be in the correct format")
	String zipCode;

	public Address() {
	}

	public Address(String address, String city, String region, String state, String zipCode) {
		this.address = address;
		this.city = city;
		this.region = region;
		this.state = state;
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address: " + getAddress() + "\n");
		builder.append("City: " + getCity() + "\n");
		builder.append("Region: " + getRegion() + "\n");
		builder.append("State:" + getState() + "\n");
		builder.append("ZIP:" + getZipCode());
		return builder.toString();
	}
}
