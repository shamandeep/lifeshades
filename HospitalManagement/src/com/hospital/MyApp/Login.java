package com.hospital.MyApp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.hospital.beanClases.Patient;

public class Login {
	private Scanner sc;
	String email;
	String password;

	public void Login() throws ClassNotFoundException, SQLException {
		sc = new Scanner(System.in);
		Home home = new Home();
	System.out.println("*****Enter Login details*****");
	System.out.print("Enter Email: ");
	email = sc.next();
	System.out.print("Enter Password: ");
	password = sc.next();
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagement","root","root");
	java.sql.Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
	java.sql.ResultSet rs=stmt.executeQuery("select * from users where email = '"+email+"' and password = '"+password+"'");  
	if (rs.next()) {
		String role = rs.getString(4);
		if(role.equals("admin")) {
			System.out.println(role);
			home.ShowAdminMenu();
		}
		else if(role.equals("patient")) {
			Patient patient = new Patient();
			patient.setEmail(rs.getString(2));
			home.ShowPatinetMenu(patient);
		}
		System.out.println(role);
	}
	else {
		System.out.println("Invalid credentials");
	}
	}
	
}
