package org.wahlzeit.model.bike;

/***
 * This is a type-object also implemented as value-type due to protected constructor 
 * and a instance tracking factory
 * @collaboration BikePhoto
 * @role Domain Object
 * @collaboration BikePart Type-Object
 * @role Type Object
 * @collaboration BikePart Factory
 * @role Domain Object
 * @author sebi
 *
 */
public final class BikePart {
	
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
	
	@Override
	public boolean equals(Object other){
		if(this == other){
			return true;
		}
		return false;
	}
}
