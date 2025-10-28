package studentmanagement1.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import studentmanagement1.entity.Student;

public class StudentService {
	private static Scanner sc = new Scanner(System.in);
	private static String url = "jdbc:postgresql://localhost:5432/School";
	private static String user = "postgres";
	private static String pswd = "401";
	private static Connection connection;

	static {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, user, pswd);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Save Method
	public int save() {
		int res = 0;
		System.out.println("Enter the Student id:");
		int id = sc.nextInt();
		System.out.println("Enter the Student name:");
		String name = sc.next();
		System.out.println("Enter the Student age:");
		int age = sc.nextInt();

		String sql = "Insert into student values(?,?,?)";

		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.setString(2, name);
			pstm.setInt(3, age);

			res = pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// Update Method
	public int update() {
		int res = 0;
		System.out.println("Enter the id to be updated");
		int id = sc.nextInt();
		System.out.println("Enter the age to be updated");
		int age = sc.nextInt();

		String sql = "Update student set age=? where id=?";

		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, age);
			pstm.setInt(2, id);

			res = pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// Delete Method
	public int delete() {
		int res = 0;
		System.out.println("Enter the id to be deleted");
		int id = sc.nextInt();

		String sql = "Delete from student where id=?";

		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);

			res = pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// Fetch Method
	public List<Student> fetchall() {

		List<Student> list = new ArrayList<Student>();
		String sql = "Select *from student";

		try {
			Statement stm = connection.createStatement();

			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				list.add(new Student(rs.getInt(1), rs.getString(2), rs.getInt(3)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// Exit Method
	public void exit() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
