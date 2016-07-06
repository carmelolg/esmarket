package it.carmelolagamba.esmarket.domain.storageManagement;

import javax.persistence.Embeddable;

@Embeddable
public class Position {

	private String address;
	private String city;
	private String region;

	public Position() {
	}
	
	public Position(String address, String city, String region){
		this.address = address;
		this.city = city;
		this.region = region;
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address: "+getAddress()+"\n");
		builder.append("City: "+getCity()+"\n");
		builder.append("Region: "+getRegion()+"\n");
		return builder.toString();
	}
}
