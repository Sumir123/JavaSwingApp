package app.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CrudOperation {

	static Connection connection = null;
	static PreparedStatement pstmt = null; // to pass parameter to query
	static Statement stmt = null; //

	public static void main(String[] args) {

		connection = new DbConnection().getDbConnection();
		System.out.print(" Connection is " + connection);
		insert();
		// update();
		// delete();
		// selectAll();
	}

	static void selectAll() {
		try {
			stmt = connection.createStatement();
			String query = "Select id, Username ,Password from Users";
			ResultSet result = stmt.executeQuery(query);
			while (result.next()) {
				String newLine = System.getProperty("line.separator");
				System.out.println(newLine);
				System.out.println(result.getInt(1));
				System.out.println(result.getString(2));
				System.out.println(result.getString(3));
			}
			stmt.close();
			result.close();
			connection.close();
		} catch (Exception e) {

		}

	}

	static void insert() {
		try {
			pstmt = connection.prepareStatement("INSERT INTO Users(id, USERNAME, PASSWORD) VALUES ( ?, ?, ?)");
			pstmt.setString(2, "");
			pstmt.setString(3, "");
			pstmt.setString(1, null);
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				System.out.println("User added");
			} else {
				System.out.println("User Not added.ERROR");
			}
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	static void update() {
		try {
			pstmt = connection.prepareStatement("UPDATE Users SET Username =?, password = ? WHERE id = 1;");
			pstmt.setString(1, "Ritish");
			pstmt.setString(2, "qwewwsd");
			int row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("User successfully Updated");
			} else {
				System.out.println("User Update ERROR!!!");
			}
			pstmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void delete() {
		try {
			pstmt = connection.prepareStatement("DELETE FROM Users WHERE Username=?;");
			pstmt.setString(1, "Ritish");
			int row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("User successfully Deleted");
			} else {
				System.out.println("User Deletion ERROR!!!");
			}
			pstmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}