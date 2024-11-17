package com.jspiders.project;

import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.jspiders.project.configuration.EmployeeConfig;
import com.jspiders.project.dao.EmployeeDao;

public class Driver {
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(EmployeeConfig.class);
		EmployeeDao employeeDao = ac.getBean("dao", EmployeeDao.class);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Types of Operation:");
		System.out.println("=====================");
		System.out.println(" 1.Save\n 2.fetch\n 3.Update\n 4.Remove\n 5.RemoveAll\n 6.Exit");
		System.out.println("Enter the choice of operation");
		int input = scanner.nextInt();
		switch (input) {
		case 1:
			employeeDao.save();
			break;
		case 2:
			employeeDao.fetch();
			break;
		case 3:
			employeeDao.update();
			break;
		case 4:
			employeeDao.remove();
			break;
		case 5:
			employeeDao.allEmployee();
			break;
		case 6:
			AnnotationConfigApplicationContext a = (AnnotationConfigApplicationContext) ac;
			a.close();
			scanner.close();
			System.exit(0);
			break;
		default:
			System.err.println("Invalid choice!!!");
		}

	}
}
