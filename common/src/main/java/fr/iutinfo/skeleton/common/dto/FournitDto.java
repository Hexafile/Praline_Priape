package fr.iutinfo.skeleton.common.dto;

import java.security.Principal;

public class FournitDto implements Principal {
	private int pno;
	private int sno;
	
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	@Override
	public String getName() {
		return sno+"Fournit"+pno;
	}
}
