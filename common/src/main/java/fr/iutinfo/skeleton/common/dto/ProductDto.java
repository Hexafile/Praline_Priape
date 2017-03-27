package fr.iutinfo.skeleton.common.dto;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductDto implements Principal {

    final static Logger logger = LoggerFactory.getLogger(ProductDto.class);
	private String name;
	private int id;
    private String description;
    private int baseprice;
    private int promotionalamount;
    private int amount;
	private boolean available;
    private String imageurl;
    private java.util.List<String> dealer;
    private int tmpSociete;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTmpSociete() {
		return tmpSociete;
	}

	public void setTmpSociete(int tmpSociete) {
		this.tmpSociete = tmpSociete;
	}

	public java.util.List<String> getDealer() {
		return dealer;
	}

	public void setDealer(java.util.List<String> dealer) {
		this.dealer = dealer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBaseprice() {
		return baseprice;
	}

	public void setBaseprice(int basePrice) {
		this.baseprice = basePrice;
	}

	public int getPromotionalamount() {
		return promotionalamount;
	}

	public void setPromotionalamount(int promotionalAmount) {
		this.promotionalamount = promotionalAmount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageURL) {
		this.imageurl = imageURL;
	}

	public String getName() {
		return name;
	}

    public void setName(String name) {
		this.name = name;
	}
}
