package app.db;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 15:00
 */
public class MongoConnection {

	private static final String MONGO_DB_NAME = "kupoman";
	private static final String MONGO_COLLECTION_COUPONS = "coupons";
	private static final String MONGO_COLLECTION_SHOPS = "shops";
	private static final String MONGO_COLLECTION_USERS = "users";

	private MongoClient mongoClient = new MongoClient();
	private MongoDatabase mongoDatabase = mongoClient.getDatabase(MONGO_DB_NAME);

	private MongoCollection<Document> mongoCollectionCoupons = mongoDatabase.getCollection(MONGO_COLLECTION_COUPONS);
	private MongoCollection<Document> mongoCollectionShops = mongoDatabase.getCollection(MONGO_COLLECTION_SHOPS);
	private MongoCollection<Document> mongoCollectionUsers = mongoDatabase.getCollection(MONGO_COLLECTION_USERS);

	public void insertDocument(MongoCollection<Document> mongoCollection, Document document) {
		mongoCollection.insertOne(document);
		System.out.println("Inserted document with ID " + document.get("ID") + " to collection with name " + mongoCollection.getNamespace());
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public MongoDatabase getMongoDatabase() {
		return mongoDatabase;
	}

	public MongoCollection<Document> getMongoCollectionCoupons() {
		return mongoCollectionCoupons;
	}

	public MongoCollection<Document> getMongoCollectionShops() {
		return mongoCollectionShops;
	}

	public MongoCollection<Document> getMongoCollectionUsers() {
		return mongoCollectionUsers;
	}
}
