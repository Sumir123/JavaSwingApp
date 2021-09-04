package app.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDao {
    Connection connection = null;

    public boolean checkValidUser(String username, String password) {
        boolean isValidUser = false;
        try {
            connection = new DbConnection().getDbConnection();
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT COUNT(1) FROM USERS WHERE Username=? and Password=?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet result = pstmt.executeQuery();
            int count = 0;
            if (result.next()) {
                count = result.getInt(1);
            }
            if (count > 0) {
                isValidUser = true;
            }
            pstmt.close();
            result.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValidUser;
    }

    public boolean addUser(String username, String password) {
        boolean isUserAdded = false;
        try {
            connection = new DbConnection().getDbConnection();
            PreparedStatement pstmt = connection
                    .prepareStatement("INSERT INTO Users(id, USERNAME, PASSWORD) VALUES ( ?, ?, ?)");
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(1, null);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                isUserAdded = true;
            }
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUserAdded;
    }

    public boolean addStudents(String fName, String lName, String phone, String address, String gender, String hooby,
            String course, String remarks) {
        boolean isUserAdded = false;
        try {
            connection = new DbConnection().getDbConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO Students(FirstName, LastName,Phone, Address, Gender, Hobbies, Course,Remarks) VALUES ( ?,?,?,?,?, ?, ?,?)");
            pstmt.setString(1, fName);
            pstmt.setString(2, lName);
            pstmt.setString(3, phone);
            pstmt.setString(4, address);
            pstmt.setString(5, gender);
            pstmt.setString(6, hooby);
            pstmt.setString(7, course);
            pstmt.setString(8, remarks);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                isUserAdded = true;
            }
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUserAdded;
    }

    public boolean updateStudents(int studentId, String fName, String lName, String phone, String address,
            String gender, String hooby, String course, String remarks) {
        boolean isUserUpdated = false;
        try {
            connection = new DbConnection().getDbConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE Students SET FirstName=?, LastName=?,Phone=?, Address=?, Gender=?, Hobbies=?, Course=?,Remarks=? WHERE STUDENTID =?");
            pstmt.setString(1, fName);
            pstmt.setString(2, lName);
            pstmt.setString(3, phone);
            pstmt.setString(4, address);
            pstmt.setString(5, gender);
            pstmt.setString(6, hooby);
            pstmt.setString(7, course);
            pstmt.setString(8, remarks);
            pstmt.setInt(9, studentId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                isUserUpdated = true;
            }
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUserUpdated;
    }

    public boolean deleteStudent(int StudentId) {
        boolean isDeleted = false;
        connection = new DbConnection().getDbConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Students WHERE StudentId=?;");
            pstmt.setInt(1, StudentId);
            int row = pstmt.executeUpdate();
            if (row > 0) {
                isDeleted = true;
            }
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
}
