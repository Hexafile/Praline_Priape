package fr.iutinfo.skeleton.api;

import java.util.List;
import java.security.Principal;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.ProductDto;

public class Product implements Principal {
    final static Logger logger = LoggerFactory.getLogger(User.class);
    private static Product anonymous = new Product(-1, "Anonymous", "anonym",-1);
    
    private int id = 0;
    private String name;
    private List<String> dealer = new ArrayList<>();
 	private String description;
    private int basePrice;
    private int promotionalAmount;
    private int amount;
	private boolean available =true;
    private String imageURL;
    
    public Product() {}
    
    public Product(int id,String name,String dealer,int basePrice) {
		this.id = id;
		this.name = name;
		this.dealer.add(dealer);
		this.basePrice=basePrice;
	}
    
    public Product(int id,String name,String dealer,int basePrice,String description) {
		this.id = id;
		this.name = name;
		this.dealer.add(dealer);
		this.basePrice=basePrice;
		this.description=description;
	}
    
    public static Product getAnonymousProduct() {
        return anonymous;
    }
    
    public int size() {
		return dealer.size();
	}

	public boolean isEmpty() {
		return dealer.isEmpty();
	}

	public boolean add(String e) {
		return dealer.add(e);
	}

	public String get(int index) {
		return dealer.get(index);
	}

	public String set(int index, String element) {
		return dealer.set(index, element);
	}

	public void add(int index, String element) {
		dealer.add(index, element);
	}

	public String remove(int index) {
		return dealer.remove(index);
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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

	public String getName() {
		return name;
	}
	
	public void addDealer(String d){
		
	}

	public void initFromDto(ProductDto dto) {
		this.name=dto.getName();
		this.id=dto.getId();
		this.dealer=dto.getDealer();
		this.description=dto.getDescription();
		this.basePrice=dto.getBasePrice();
		this.promotionalAmount=dto.getPromotionalAmount();
		this.amount=dto.getAmount();
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
	    dto.setAmount(amount);
		dto.setAvailable(available);
	    dto.setImageURL(imageURL);
		return dto;
	}
}
