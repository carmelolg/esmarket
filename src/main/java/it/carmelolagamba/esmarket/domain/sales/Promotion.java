package it.carmelolagamba.esmarket.domain.sales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PROMOTION")
public class Promotion {

	@Id
	@GeneratedValue
	@Column(name = "PROMOTION_ID")
	private Long ID;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "PROMOTION_START_DATE")
	private Date startPromo;
	@Column(name = "PROMOTION_END_DATE")
	private Date endPromo;
	@Column(name = "PROMOTION_DISCOUNT")
	private float discount;

//	@Version
//	@Column(name = "VERSION")
//	private Integer version;
//	
//	public Integer getVersion() {
//		return version;
//	}

	
	public Promotion() {
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Date getStartPromo() {
		return startPromo;
	}

	public void setStartPromo(Date startPromo) {
		this.startPromo = startPromo;
	}

	public Date getEndPromo() {
		return endPromo;
	}

	public void setEndPromo(Date endPromo) {
		this.endPromo = endPromo;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public Long getID() {
		return ID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + Float.floatToIntBits(discount);
		result = prime * result + ((endPromo == null) ? 0 : endPromo.hashCode());
		result = prime * result + ((startPromo == null) ? 0 : startPromo.hashCode());
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
		Promotion other = (Promotion) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (Float.floatToIntBits(discount) != Float.floatToIntBits(other.discount))
			return false;
		if (endPromo == null) {
			if (other.endPromo != null)
				return false;
		} else if (!endPromo.equals(other.endPromo))
			return false;
		if (startPromo == null) {
			if (other.startPromo != null)
				return false;
		} else if (!startPromo.equals(other.startPromo))
			return false;
		return true;
	}

}
