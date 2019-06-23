package app.services;

import app.db.MongoConnection;
import app.mappers.CouponMapper;
import app.models.Coupon;
import app.models.Shop;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 17:51
 */
public class CouponService {

//	private final CouponMapper couponMapper;
//
//	public CouponService() {
//		this.couponMapper = CouponMapper.INSTANCE;
//	}

	public List<Coupon> getCoupons() {
		MongoConnection mongoConnection = new MongoConnection();
		MongoCollection<Document> mongoCollection = mongoConnection.getMongoCollectionCoupons();
		List<Coupon> coupons = new ArrayList<>();
		MongoCursor<Document> cursor = mongoCollection.find().iterator();

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
}
