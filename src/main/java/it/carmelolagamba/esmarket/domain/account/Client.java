package it.carmelolagamba.esmarket.domain.account;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="Account")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type_of_user",
    discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue(value="Client")
public class Client extends Account {

	public Client() {
		super();
	}
}
