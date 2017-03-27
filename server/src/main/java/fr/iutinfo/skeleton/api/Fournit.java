package fr.iutinfo.skeleton.api;

import java.security.Principal;

import fr.iutinfo.skeleton.common.dto.FournitDto;

public class Fournit implements Principal{
	private int pno;
	private int sno;
	
	public Fournit() {}
	
	Fournit(int pno, int sno){
		this.pno=pno;
		this.sno=sno;
	}
	
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
	
	public void initFromDto(FournitDto dto){
		this.setPno(dto.getPno());
		this.setSno(dto.getSno());
	}
	
	public FournitDto convertToDto(){
		FournitDto dto = new FournitDto();
		dto.setPno(getPno());
		dto.setSno(getSno());
		return dto;
	}
}
