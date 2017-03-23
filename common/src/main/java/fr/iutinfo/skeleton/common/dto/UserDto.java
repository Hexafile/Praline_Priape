package fr.iutinfo.skeleton.common.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;

public class UserDto implements Principal {
    final static Logger logger = LoggerFactory.getLogger(UserDto.class);
    private int id = 0;
    private int role=0;
    private String name;
    private String surname;
    private String alias;
    private String adresse;
    private String societe;
    private int tel;
    private int sexe;
    private int ptsFidelite;
    private java.sql.Date dateInscription;
    private java.sql.Date dateNaissance;
    private boolean newsLetter;
    private String email;
    private String password;

    public java.sql.Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(java.util.Date dateInscription) {
		this.dateInscription = new java.sql.Date(dateInscription.getTime());;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
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

	public int getSexe() {
		return sexe;
	}

	public void setSexe(int sexe) {
		this.sexe = sexe;
	}

	public int getPtsFidelite() {
		return ptsFidelite;
	}

	public void setPtsFidelite(int ptsFidelite) {
		this.ptsFidelite = ptsFidelite;
	}

	public java.sql.Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(java.util.Date dateNaissance) {
		this.dateNaissance = new java.sql.Date(dateNaissance.getTime());
	}

	public boolean isNewsLetter() {
		return newsLetter;
	}

	public void setNewsLetter(boolean newsLetter) {
		this.newsLetter = newsLetter;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
