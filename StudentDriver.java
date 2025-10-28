package studentmanagement1.Driver;

import java.util.List;
import java.util.Scanner;

import studentmanagement1.entity.Student;
import studentmanagement1.service.StudentService;

public class StudentDriver {

	public static void main(String[] args) {
		StudentService service = new StudentService();
		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome to Student menu...");
		boolean flag = true;
		while (flag) {

			System.out.println("Enter 1 to save student");
			System.out.println("Enter 2 to update student");
			System.out.println("Enter 3 to fetch student");
			System.out.println("Enter 4 to delete student");
			System.out.println("Enter 5 to close");
			System.out.println();

			System.out.println("Enter your choice:");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				int save = service.save();
				if (save != 0) {
					System.out.println("Data Saved..👍");
				}
				break;

			case 2:
				int update = service.update();
				if (update != 0) {
					System.out.println("Data Updated..❤️");
				}
				break;

			case 3:
				List<Student> list = service.fetchall();
				if (list != null) {
					for (Student student : list) {
						System.out.println(student.getId() + " " + student.getName() + " " + student.getAge());
						System.out.println("=======================");
					}
				}
				break;

			case 4:
				int delete = service.delete();
				if (delete != 0) {
					System.out.println("Data Deleted..😇");
				}
				break;

			case 5:
				service.exit();
				System.out.println("Connection is closed");
				flag = false;
				break;
			}
		}
	}

}
