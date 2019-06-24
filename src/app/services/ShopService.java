package app.services;

import app.db.MongoConnection;
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
public class ShopService {

	private MongoConnection mongoConnection;
	private MongoCollection<Document> mongoCollection;
	private CouponService couponService;

	public ShopService() {
		this.mongoConnection = new MongoConnection();
		this.mongoCollection = mongoConnection.getMongoCollectionShops();
		this.couponService = new CouponService();
	}

	public List<Shop> getShops() {
		List<Shop> shops = new ArrayList<>();
		MongoCursor<Document> cursor = mongoCollection.find().iterator();

		try {
			while (cursor.hasNext()) {
				Document document = cursor.next();
				Shop shop = new Shop(document.getString("ID"), document.getString("name"));
				shops.add(shop);
			}
		} finally {
			cursor.close();
		}

		return shops;
	}

	public Shop getShopByID(String id) {
		Document document = mongoCollection.find(Filters.eq("ID", id)).first();
		Shop shop = new Shop();

		if (document != null) {
			shop = new Shop(document.getString("ID"), document.getString("name"));
			shop.setCoupons(couponService.getCouponsByShopID(id));
		}

		return shop;
	}
}
