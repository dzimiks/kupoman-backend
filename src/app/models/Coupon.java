package app.models;

import java.util.Date;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 14:40
 */
public class Coupon {

	private long ID;
	private Shop shop;
	private String product;
	private float discountedPrice;
	private float originalPrice;
	private Date validFrom;
	private Date validTo;

	public Coupon(long ID, Shop shop, String product, float discountedPrice, float originalPrice, Date validFrom, Date validTo) {
		this.ID = ID;
		this.shop = shop;
		this.product = product;
		this.discountedPrice = discountedPrice;
		this.originalPrice = originalPrice;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public float getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(float discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	@Override
	public String toString() {
		return "Coupon{" +
				"ID=" + ID +
				", shop=" + shop +
				", product='" + product + '\'' +
				", discountedPrice=" + discountedPrice +
				", originalPrice=" + originalPrice +
				", validFrom=" + validFrom +
				", validTo=" + validTo +
				'}';
	}
}
