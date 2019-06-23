package app.db;

import app.enums.Privilege;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 15:14
 */
public class Main {

	public static void main(String[] args) {
		MongoConnection mongoConnection = new MongoConnection();

		// TODO: Generates the database.
		generateDatabase(mongoConnection);

		// TODO: Drops the database.
//		dropDatabase(mongoConnection.getMongoDatabase());
	}

	private static void generateDatabase(MongoConnection mongoConnection) {
		Random random = new Random();

		// TODO: Collections.
		MongoCollection<Document> mongoCollectionCoupons = mongoConnection.getMongoCollectionCoupons();
		MongoCollection<Document> mongoCollectionShops = mongoConnection.getMongoCollectionShops();
		MongoCollection<Document> mongoCollectionUsers = mongoConnection.getMongoCollectionUsers();

		String[] shops = new String[]{"Kafeterija", "KFC", "Nivea", "Gigatron", "Win Win", "Kupoman", "Kupujem Prodajem"};
		String[] products = new String[]{
				"Bowl",
				"Shovel",
				"Milk",
				"Door",
				"Shoe lace",
				"Hair brush",
				"Camera",
				"Speakers",
				"Bread",
				"Perfume",
				"Produzeni s mlekom"
		};
		String[] firstNames = new String[]{"Vanja", "Milan", "Milos", "Branko", "Anastasija", "Milica", "Zivojin", "Nikola"};
		String[] lastNames = new String[]{"Paunovic", "Mitic", "Milunovic", "Copic", "Radonjic", "Pavlovic", "Markovic", "Aksentijevic"};
		String[] privileges = new String[]{Privilege.ADMINISTRATOR.toString(), Privilege.OPERATOR.toString()};

		for (int i = 0; i < 10; i++) {
			float originalPrice = getRandomFloat(200f, 700f);
			float discountedPrice = originalPrice - getRandomFloat(10f, 150f);

			int randomDay = ThreadLocalRandom.current().nextInt(2, 8);
			int randomMonthFrom = ThreadLocalRandom.current().nextInt(0, 12);
			int randomDayOfMonthFrom = ThreadLocalRandom.current().nextInt(1, 31);

			String randomUsername = Long.toHexString(Double.doubleToLongBits(Math.random()));
			String randomPassword = Long.toHexString(Double.doubleToLongBits(Math.random()));

			// TODO: Valid From.
			Calendar calendarValidFrom = Calendar.getInstance();
			calendarValidFrom.set(Calendar.YEAR, 2019);
			calendarValidFrom.set(Calendar.MONTH, randomMonthFrom);
			calendarValidFrom.set(Calendar.DAY_OF_MONTH, randomDayOfMonthFrom);
			Date validFrom = calendarValidFrom.getTime();

			// TODO: Valid To.
			Date validTo = addDays(validFrom, randomDay);

			// TODO: Documents.
			Document shopDocument = new Document("ID", UUID.randomUUID().toString())
					.append("name", shops[Math.abs(random.nextInt()) % shops.length]);

			Document couponDocument = new Document("ID", Math.abs(random.nextLong()))
					.append("shop", shopDocument)
					.append("product", products[Math.abs(random.nextInt()) % products.length])
					.append("discountedPrice", discountedPrice)
					.append("originalPrice", originalPrice)
					.append("validFrom", validFrom)
					.append("validTo", validTo);

			Document userDocument = new Document("ID", UUID.randomUUID().toString())
					.append("firstName", firstNames[Math.abs(random.nextInt()) % firstNames.length])
					.append("lastName", lastNames[Math.abs(random.nextInt()) % lastNames.length])
					.append("privilegeLevel", privileges[Math.abs(random.nextInt()) % privileges.length])
					.append("username", randomUsername)
					.append("password", randomPassword);

			// TODO: Inserts.
			mongoConnection.insertDocument(mongoCollectionCoupons, couponDocument);
			mongoConnection.insertDocument(mongoCollectionShops, shopDocument);
			mongoConnection.insertDocument(mongoCollectionUsers, userDocument);
		}
	}

	private static float getRandomFloat(float min, float max) {
		Random rand = new Random();
		return rand.nextFloat() * (max - min) + min;
	}

	private static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // Minus number would decrement the days
		return cal.getTime();
	}

	public static void dropDatabase(MongoDatabase database) {
		database.drop();
		System.out.println("Successfully deleted database with name " + database.getName());
	}
}
