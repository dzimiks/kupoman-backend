package app.db;

import app.enums.Privilege;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 15:14
 */
public class Main {

	public static void main(String[] args) {
		MongoConnection mongoConnection = new MongoConnection();
		Random random = new Random();

		// TODO: Valid From.
		Calendar calendarValidFrom = Calendar.getInstance();
		calendarValidFrom.set(Calendar.YEAR, 2019);
		calendarValidFrom.set(Calendar.MONTH, Calendar.JUNE);
		calendarValidFrom.set(Calendar.DAY_OF_MONTH, 1);
		Date validFrom = calendarValidFrom.getTime();

		// TODO: Valid To.
		Calendar calendarValidTo = Calendar.getInstance();
		calendarValidTo.set(Calendar.YEAR, 2019);
		calendarValidTo.set(Calendar.MONTH, Calendar.JUNE);
		calendarValidTo.set(Calendar.DAY_OF_MONTH, 1);
		Date validTo = calendarValidTo.getTime();

		// TODO: Collections.
		MongoCollection<Document> mongoCollectionCoupons = mongoConnection.getMongoCollectionCoupons();
		MongoCollection<Document> mongoCollectionShops = mongoConnection.getMongoCollectionShops();
		MongoCollection<Document> mongoCollectionUsers = mongoConnection.getMongoCollectionUsers();

		// TODO: Documents.
		Document couponDocument = new Document("ID", random.nextLong())
				.append("shop", "Kafeterija")
				.append("product", "Produzeni s mlekom")
				.append("discountedPrice", 199.99f)
				.append("originalPrice", 249.99f)
				.append("validFrom", validFrom)
				.append("validTo", validTo);

		Document shopDocument = new Document("ID", UUID.randomUUID().toString())
				.append("name", "Kafeterija");

		Document userDocument = new Document("ID", "dzimiks")
				.append("firstName", "Vanja")
				.append("lastName", "Paunovic")
				.append("privilegeLevel", Privilege.ADMINISTRATOR.toString())
				.append("username", "dzimiks")
				.append("password", "dzimidzimiks");

		// TODO: Inserts.
		mongoConnection.insertDocument(mongoCollectionCoupons, couponDocument);
		mongoConnection.insertDocument(mongoCollectionShops, shopDocument);
		mongoConnection.insertDocument(mongoCollectionUsers, userDocument);
	}
}
