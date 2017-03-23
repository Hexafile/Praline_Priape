package fr.iutinfo.skeleton.api;

import java.security.Principal;

import fr.iutinfo.skeleton.common.dto.ProductDto;
import fr.iutinfo.skeleton.common.dto.SocieteDto;

public class Societe implements Principal {

	private int sno;
	private String name;
	private int numSiret;
	private String adresse;
	private int tel;
	private String mail;
	private String methodeLivraison;
	private String livreur;
	
	public Societe(int sno, String name) {
		this.sno=sno;
		this.name=name;
	}
	
	public Societe() {}
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumSiret() {
		return numSiret;
	}
	public void setNumSiret(int numSiret) {
		this.numSiret = numSiret;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMethodeLivraison() {
		return methodeLivraison;
	}
	public void setMethodeLivraison(String methodeLivraison) {
		this.methodeLivraison = methodeLivraison;
	}
	public String getLivreur() {
		return livreur;
	}
	public void setLivreur(String livreur) {
		this.livreur = livreur;
	}
	
	public void initFromDto(SocieteDto dto) {
		this.name=dto.getName();
		this.sno=dto.getSno();
		this.numSiret=dto.getNumSiret();
		this.adresse=dto.getAdresse();
		this.tel=dto.getTel();
		this.mail=dto.getMail();
		this.methodeLivraison=dto.getMethodeLivraison();
		this.livreur=dto.getLivreur();
	}
	
	public SocieteDto convertToDto() {
		SocieteDto dto = new SocieteDto();
		dto.setName(name);
		dto.setSno(sno);
		dto.setNumSiret(numSiret);
		dto.setAdresse(adresse);
		dto.setTel(tel);
		dto.setMail(mail);
		dto.setMethodeLivraison(methodeLivraison);
		dto.setLivreur(livreur);
		return dto;
	}
}
