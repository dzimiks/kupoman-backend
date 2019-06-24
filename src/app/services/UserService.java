package app.services;

import app.db.MongoConnection;
import app.enums.Privilege;
import app.models.User;
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
public class UserService {

	private MongoConnection mongoConnection;
	private MongoCollection<Document> mongoCollection;
	private MongoCursor<Document> cursor;

	public UserService() {
		this.mongoConnection = new MongoConnection();
		this.mongoCollection = mongoConnection.getMongoCollectionUsers();
		this.cursor = mongoCollection.find().iterator();
	}

	public User addUser(User user) {
		Document userDocument = new Document("ID", user.getID())
				.append("firstName", user.getFirstName())
				.append("lastName", user.getLastName())
				.append("privilegeLevel", user.getPrivilegeLevel())
				.append("username", user.getUsername())
				.append("password", user.getPassword());

		mongoCollection.insertOne(userDocument);
		System.out.println("Inserted document with ID " + userDocument.get("ID") + " to collection with name " + mongoCollection.getNamespace());
		return user;
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<>();

		try {
			while (cursor.hasNext()) {
				Document document = cursor.next();
				User user = new User(
						document.getString("ID"),
						document.getString("firstName"),
						document.getString("lastName"),
						Privilege.valueOf(document.getString("privilegeLevel")),
						document.getString("username"),
						document.getString("password")
				);
				users.add(user);
			}
		} finally {
			cursor.close();
		}

		return users;
	}

	public User getUserAuth(String username, String password) {
		Document document = mongoCollection.find(
				Filters.and(
						Filters.eq("username", username),
						Filters.eq("password", password)
				)
		).first();

		User user = null;

		if (document != null) {
			user = new User(
					document.getString("ID"),
					document.getString("firstName"),
					document.getString("lastName"),
					Privilege.valueOf(document.getString("privilegeLevel")),
					document.getString("username"),
					document.getString("password")
			);
		}

		return user;
	}

	public void deleteUser(String id) {
		mongoCollection.deleteOne(Filters.eq("ID", id));
		System.out.println("Successfully deleted document with ID " + id);
	}
}
