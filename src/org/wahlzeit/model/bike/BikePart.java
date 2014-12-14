package org.wahlzeit.model.bike;

public class BikePart {
	
	private String fabricator = "";
	private String price = "";
	private BikePartType type;
	
	protected BikePart(String fabricator, String price, BikePartType type){
		this.fabricator = fabricator;
		this.type = type;
		this.price = price;
	}
	
	public String getManufacturer(){
		return this.fabricator;
	}
	
	public String getPrice(){
		return this.price;
	}
	
	public String getPartTypeAsString(){
		return this.type.name();
	}
	
	public BikePartType getType(){
		return this.type;
	}
	
	@Override
	public int hashCode(){
		int start = 10;
		start += 12*fabricator.hashCode();
		start += 3*price.hashCode();
		start += 27*type.hashCode();
		return start;
	}
}
