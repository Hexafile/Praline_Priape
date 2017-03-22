package fr.iutinfo.skeleton.api;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.ProductDto;

public class Product implements Principal {
    final static Logger logger = LoggerFactory.getLogger(User.class);
    private static Product anonymous = new Product(-1, "Anonymous", "anonym",-1);
    
    private String name;
    private int id = 0;
    private String dealer;
    private String description;
    private int basePrice;
    private int promotionalAmount;
	private boolean available =true;
    private String imageURL;
    
    public Product(int id,String name,String dealer,int basePrice) {
		this.id = id;
		this.name = name;
		this.dealer=dealer;
		this.basePrice=basePrice;
	}
    
    public Product(int id,String name,String dealer,int basePrice,String description) {
		this.id = id;
		this.name = name;
		this.dealer=dealer;
		this.basePrice=basePrice;
		this.description=description;
	}
    
    public static Product getAnonymousProduct() {
        return anonymous;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isAvailable() {
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

	@Override
	public String getName() {
		return name;
	}

	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	public void initFromDto(ProductDto dto) {
		this.name=dto.getName();
		this.id=dto.getId();
		this.dealer=dto.getDealer();
		this.description=dto.getDescription();
		this.basePrice=dto.getBasePrice();
		this.promotionalAmount=dto.getPromotionalAmount();
		this.available = dto.isAvailable();
	    this.imageURL= dto.getImageURL();
	}
	
	public ProductDto convertToDto() {
		ProductDto dto = new ProductDto();
		dto.setName(name);
		dto.setId(id);
	    dto.setDealer(dealer);
	    dto.setDescription(description);
	    dto.setBasePrice(basePrice);
	    dto.setPromotionalAmount(promotionalAmount);
		dto.setAvailable(available);
	    dto.setImageURL(imageURL);

		return dto;
	}
}
