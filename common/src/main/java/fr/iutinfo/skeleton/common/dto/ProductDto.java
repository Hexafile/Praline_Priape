package fr.iutinfo.skeleton.common.dto;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductDto implements Principal {

    final static Logger logger = LoggerFactory.getLogger(ProductDto.class);
	private String name;
	private int id;
    private List<String> dealer;
    private String description;
    private int basePrice;
    private int promotionalAmount;
    private int amount;
	private boolean available;
    private String imageURL;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getDealer() {
		return dealer;
	}

	public void setDealer(List<String> dealer) {
		this.dealer = dealer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}

	public int getPromotionalAmount() {
		return promotionalAmount;
	}

	public void setPromotionalAmount(int promotionalAmount) {
		this.promotionalAmount = promotionalAmount;
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

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getName() {
		return name;
	}

    public void setName(String name) {
		this.name = name;
	}
}
