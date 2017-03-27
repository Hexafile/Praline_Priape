package fr.iutinfo.skeleton.api;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import fr.iutinfo.skeleton.common.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.security.SecureRandom;

public class User implements Principal {
	final static Logger logger = LoggerFactory.getLogger(User.class);
	private static User anonymous = new User("Anonymous", "anonym");

	private int id = 0;
	private int role = 0;
	private String name;
	private String surname;
	private String alias;
	private String adresse;
	private String societe;
	private int tel;
	private int sexe;
	private int ptsFidelite;
	private java.util.Date dateNaissance = new java.util.Date();
	private java.util.Date dateInscription = new java.util.Date();
	private boolean newsLetter;
	private String email;
	private String password;
	private String passwdHash;
	private String salt;
	private String search;

	public User(String name) {
		this.name = name;
	}

	public User(String name, String alias) {
		this.name = name;
		this.alias = alias;
	}

	public User() {
	}

	public static User getAnonymousUser() {
		return anonymous;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public String getEmail() {
		return email;
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

	public java.util.Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(java.util.Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public boolean isNewsLetter() {
		return newsLetter;
	}

	public void setNewsLetter(boolean newsLetter) {
		this.newsLetter = newsLetter;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public java.util.Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(java.util.Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		passwdHash = buildHash(password, getSalt());
		this.password = password;
	}

	private String buildHash(String password, String s) {
		Hasher hasher = Hashing.sha256().newHasher();
		hasher.putString(password + s, Charsets.UTF_8);
		return hasher.hash().toString();
	}

	public boolean isGoodPassword(String password) {
		if (isAnonymous()) {
			return false;
		}
		String hash = buildHash(password, getSalt());
		return hash.equals(getPasswdHash());
	}

	public String getPasswdHash() {
		return passwdHash;
	}

	public void setPasswdHash(String passwdHash) {
		this.passwdHash = passwdHash;
	}

	@Override
	public boolean equals(Object arg) {
		if (getClass() != arg.getClass())
			return false;
		User user = (User) arg;
		return name.equals(user.name) && alias.equals(user.alias) && email.equals(user.email)
				&& passwdHash.equals(user.getPasswdHash()) && salt.equals((user.getSalt()));
	}

	@Override
	public String toString() {
		return id + ": " + alias + ", " + name + ", " + surname + " <" + email + ">";
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getSalt() {
		if (salt == null) {
			salt = generateSalt();
		}
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	private String generateSalt() {
		SecureRandom random = new SecureRandom();
		Hasher hasher = Hashing.sha256().newHasher();
		hasher.putLong(random.nextLong());
		return hasher.hash().toString();
	}

	public void resetPasswordHash() {
		if (password != null && !password.isEmpty()) {
			setPassword(getPassword());
		}
	}

	public boolean isInUserGroup() {
		return !(id == anonymous.getId());
	}

	public boolean isAnonymous() {
		return this.getId() == getAnonymousUser().getId();
	}

	public String getSearch() {
		search = name + " " + alias + " " + email;
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void initFromDto(UserDto dto) {
		this.setId(dto.getId());
		this.setRole(dto.getRole());
		this.setName(dto.getName());
		this.setSurname(dto.getSurname());
		this.setAlias(dto.getAlias());
		this.setAdresse(dto.getAdresse());
		this.setSociete(dto.getSociete());
		this.setTel(dto.getTel());
		this.setSexe(dto.getSexe());
		this.setPtsFidelite(dto.getPtsFidelite());
		if (dto.getDateInscription() != null) {
			this.setDateInscription(new java.util.Date(dto.getDateInscription().getTime()));
		}
		if (dto.getDateNaissance() != null) {
			this.setDateNaissance(new java.util.Date(dto.getDateNaissance().getTime()));
		}
		this.setNewsLetter(dto.isNewsLetter());
		this.setEmail(dto.getEmail());
		this.setPassword(dto.getPassword());
	}

	public UserDto convertToDto() {
		UserDto dto = new UserDto();
		dto.setId(this.getId());
		dto.setRole(this.getRole());
		dto.setName(this.getName());
		dto.setSurname(this.getSurname());
		dto.setAlias(this.getAlias());
		dto.setAdresse(this.getAdresse());
		dto.setSociete(this.getSociete());
		dto.setTel(this.getTel());
		dto.setSexe(this.getSexe());
		dto.setPtsFidelite(this.getPtsFidelite());
		dto.setDateInscription(this.getDateInscription());
		dto.setDateNaissance(this.getDateNaissance());
		dto.setNewsLetter(this.isNewsLetter());
		dto.setEmail(this.getEmail());
		dto.setPassword(this.getPassword());
		return dto;
	}
}
