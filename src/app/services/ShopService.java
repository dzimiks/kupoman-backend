package app.services;

import app.db.MongoConnection;
import app.models.Shop;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 17:51
 */
public class ShopService {

	public List<Shop> getShops() {
		MongoConnection mongoConnection = new MongoConnection();
		MongoCollection<Document> mongoCollection = mongoConnection.getMongoCollectionShops();
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
}
