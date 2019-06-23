package app.dataTransferObjects;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 17:35
 */
public class CouponDTO {

	private long ID;
	private ShopDTO shop;
	private String product;
	private float discountedPrice;
	private float originalPrice;
	private Date validFrom;
	private Date validTo;

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public ShopDTO getShop() {
		return shop;
	}

	public void setShop(ShopDTO shop) {
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

	public String getValidFrom() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		return dateFormat.format(validFrom);
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTo() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		return dateFormat.format(validTo);	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
}
