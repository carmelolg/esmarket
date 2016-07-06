package it.carmelolagamba.esmarket.domain.account;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_of_user", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "Generic")
public abstract class Account implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "ID_ACCOUNT")
	private Long id;

	@Column(name = "username", nullable = false)
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "Username must be alphanumeric")
	private String username;

	@Column(name = "password", nullable = false)
	@Size(min = 5, message = "Password must be of size 5 at least")
	private String password;

	@Embedded
	@Valid
	private Address address;

	@Column(name = "email", nullable = false)
	@Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]+", message = "Mail must be in a correct format (example: esmarket@esmarket.com)")
	private String eMail;

//	@Version
//	@Column(name = "VERSION")
//	private Integer version;
//	
//	public Integer getVersion() {
//		return version;
//	}

	
	public Account() {
		super();
		username = "";
		password = "";
		address = new Address("", "", "", "", "");
		eMail = "";
	}

	public Account(String username, String password, Address address, String eMail) {
		super();
		this.username = username;
		this.password = password;
		this.address = address;
		this.eMail = eMail;
	}

	public Long getID() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Id: " + id + "\n");
		builder.append("Username: " + getUsername() + "\n");
		builder.append("Password: " + getPassword() + "\n");
		builder.append("Address: " + getAddress().toString() + "\n");
		builder.append("eMail:" + geteMail() + "\n");
		return builder.toString();
	}

}
