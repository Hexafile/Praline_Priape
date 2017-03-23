package fr.iutinfo.skeleton.api;

public class Societe {

	private int sno;
	private String nom;
	private int numSiret;
	private String adresse;
	private int tel;
	private String mail;
	private String methodeLivraison;
	private String livreur;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
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
	
	
}
