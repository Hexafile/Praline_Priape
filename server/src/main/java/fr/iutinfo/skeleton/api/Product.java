package fr.iutinfo.skeleton.api;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.ProductDto;

public class Product implements Principal {
    final static Logger logger = LoggerFactory.getLogger(User.class);
    private static Product anonymous = new Product(-1, "Anonymous",-1);
    
    private int id = 0;
    private String name;
 	private String description;
    private int baseprice;
    private int promotionalamount;
    private int amount;
	private boolean available =true;
    private String imageurl;
    
    public Product() {}
    
    public Product(int id,String name,int basePrice) {
		this.id = id;
		this.name = name;
		this.baseprice=basePrice;
	}
    
    public Product(int id,String name,int basePrice,String description) {
		this.id = id;
		this.name = name;
		this.baseprice=basePrice;
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBasePrice() {
		return baseprice;
	}

	public void setBasePrice(int basePrice) {
		this.baseprice = basePrice;
	}

	public int getPromotionalAmount() {
		return promotionalamount;
	}

	public void setPromotionalAmount(int promotionalAmount) {
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

	public String getImageURL() {
		return imageurl;
	}

	public void setImageURL(String imageURL) {
		this.imageurl = imageURL;
	}

	public void initFromDto(ProductDto dto) {
		this.name=dto.getName();
		this.id=dto.getId();
		this.description=dto.getDescription();
		this.baseprice=dto.getBasePrice();
		this.promotionalamount=dto.getPromotionalAmount();
		this.amount=dto.getAmount();
		this.available = dto.getAvailable();
	    this.imageurl= dto.getImageURL();
	}
	
	public ProductDto convertToDto() {
		ProductDto dto = new ProductDto();
		dto.setName(name);
		dto.setId(id);
	    dto.setDescription(description);
	    dto.setBasePrice(baseprice);
	    dto.setPromotionalAmount(promotionalamount);
	    dto.setAmount(amount);
		dto.setAvailable(available);
	    dto.setImageURL(imageurl);
		return dto;
	}
}
