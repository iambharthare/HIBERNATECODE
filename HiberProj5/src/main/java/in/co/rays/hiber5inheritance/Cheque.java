package in.co.rays.hiber5inheritance;

import javax.persistence.Entity;

@Entity
public class Cheque extends Payment {
	private int cheqNo;

	public int getCheqNo() {
		return cheqNo;
	}

	public void setCheqNo(int cheqNo) {
		this.cheqNo = cheqNo;
	}

}
