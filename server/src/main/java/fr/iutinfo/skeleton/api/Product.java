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
	private boolean available;
    private String imageurl;
    private java.util.List<String> dealer;
    
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
    
    public java.util.List<String> getDealer() {
		return dealer;
	}

	public void setDealer(java.util.List<String> dealer) {
		this.dealer = dealer;
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

	public void initFromDto(ProductDto dto) {
		this.name=dto.getName();
		this.id=dto.getId();
		this.description=dto.getDescription();
		this.baseprice=dto.getBaseprice();
		this.promotionalamount=dto.getPromotionalamount();
		this.amount=dto.getAmount();
		this.available = dto.getAvailable();
	    this.imageurl= dto.getImageurl();
	    this.dealer= dto.getDealer();
	}
	
	public ProductDto convertToDto() {
		ProductDto dto = new ProductDto();
		dto.setName(name);
		dto.setId(id);
	    dto.setDescription(description);
	    dto.setBaseprice(baseprice);
	    dto.setPromotionalamount(promotionalamount);
	    dto.setAmount(amount);
		dto.setAvailable(available);
	    dto.setImageurl(imageurl);
	    dto.setDealer(dealer);
		return dto;
	}
}
