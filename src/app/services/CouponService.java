package app.services;

import app.dataTransferObjects.CouponDTO;
import app.dataTransferObjects.ShopDTO;
import app.db.MongoConnection;
import app.mappers.CouponMapper;
import app.mappers.ShopMapper;
import app.models.Coupon;
import app.models.Shop;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 17:51
 */
public class CouponService {

//	private final CouponMapper couponMapper;
//	private final ShopMapper shopMapper;

	private MongoConnection mongoConnection;
	private MongoCollection<Document> mongoCollection;
	private MongoCursor<Document> cursor;
//	private ShopService shopService;

	public CouponService() {
//		this.couponMapper = CouponMapper.INSTANCE;
//		this.shopMapper = ShopMapper.INSTANCE;
		this.mongoConnection = new MongoConnection();
		this.mongoCollection = mongoConnection.getMongoCollectionCoupons();
		this.cursor = mongoCollection.find().iterator();
//		this.shopService = new ShopService();
	}

	public List<Coupon> getCoupons() {
		List<Coupon> coupons = new ArrayList<>();

		try {
			while (cursor.hasNext()) {
				Document document = cursor.next();
				Shop shop = new Shop(
						document.get("shop", Document.class).getString("ID"),
						document.get("shop", Document.class).getString("name")
				);
				Coupon coupon = new Coupon(
						document.getLong("ID"),
						shop,
						(String) document.get("product"),
						((Number) document.get("discountedPrice")).floatValue(),
						((Number) document.get("originalPrice")).floatValue(),
						document.getDate("validFrom"),
						document.getDate("validTo")
				);
				coupons.add(coupon);
			}
		} finally {
			cursor.close();
		}

		return coupons;
	}

	public Coupon addCoupon(Coupon coupon) {
		System.out.println(coupon);
		Document shopDocument = new Document("ID", coupon.getShop().getID())
				.append("name", coupon.getShop().getName());

		Document couponDocument = new Document("ID", coupon.getID())
				.append("shop", shopDocument)
				.append("product", coupon.getProduct())
				.append("discountedPrice", coupon.getDiscountedPrice())
				.append("originalPrice", coupon.getOriginalPrice())
				.append("validFrom", coupon.getValidFrom())
				.append("validTo", coupon.getValidTo());

		mongoCollection.insertOne(couponDocument);
		System.out.println("Inserted document with ID " + couponDocument.get("ID") + " to collection with name " + mongoCollection.getNamespace());
		return coupon;
	}

	public void deleteCoupon(long id) {
		mongoCollection.deleteOne(Filters.eq("ID", id));
		System.out.println("Successfully deleted document with ID " + id);
	}

	public void deleteCouponsByShopID(String id) {
		mongoCollection.deleteMany(Filters.eq("shop.ID", id));
		System.out.println("Successfully deleted documents with ID " + id);
	}

	public List<Coupon> getCouponsByShopID(String id) {
		List<Coupon> coupons = new ArrayList<>();
		MongoCursor<Document> shopCursor = mongoCollection.find(Filters.eq("shop.ID", id)).iterator();

		try {
			while (shopCursor.hasNext()) {
				Document document = shopCursor.next();
				Shop shop = new Shop(
						id,
						document.get("shop", Document.class).getString("name")
				);
				Coupon coupon = new Coupon(
						document.getLong("ID"),
						shop,
						(String) document.get("product"),
						((Number) document.get("discountedPrice")).floatValue(),
						((Number) document.get("originalPrice")).floatValue(),
						document.getDate("validFrom"),
						document.getDate("validTo")
				);
				coupons.add(coupon);
			}
		} finally {
			shopCursor.close();
		}

		return coupons;
	}
}
