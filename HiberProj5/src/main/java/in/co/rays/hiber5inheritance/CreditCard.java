package in.co.rays.hiber5inheritance;

import javax.persistence.Entity;

@Entity
public class CreditCard extends Payment {
	private String ccTye;

	public String getCcTye() {
		return ccTye;
	}

	public void setCcTye(String ccTye) {
		this.ccTye = ccTye;
	}

}
