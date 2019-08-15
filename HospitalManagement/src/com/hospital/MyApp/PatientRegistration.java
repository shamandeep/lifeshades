package com.hospital.MyApp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.hospital.beanClases.Patient;


public class PatientRegistration {
	public Patient RegisterPatient() throws SQLException {
		Scanner sc = new Scanner(System.in);
		Patient patient = new Patient();
	System.out.println("*****Enter patient details*****");
	System.out.println("Enter patient Name: ");
	patient.setName(sc.next());
	System.out.println("Enter patient Place: ");
	patient.setPlace(sc.next());
	System.out.println("Enter patient Gender: ");
	patient.setGender(sc.next());
	System.out.println("Enter patient Age: ");
	patient.setAge(sc.nextInt());
	System.out.println("Enter patient Email: ");
	patient.setEmail(sc.next());
	System.out.println("Enter patient Password: ");
	patient.setPassword(sc.next());
	int i = 0,j = 0;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagement","root","root");    
	java.sql.PreparedStatement stmt=con.prepareStatement("insert into users values(0,?,?,?)");  
	//saving to users table
	stmt.setString(1,patient.getEmail());
	stmt.setString(2,patient.getPassword());
	stmt.setString(3,"patient"); 
	i = stmt.executeUpdate();
	  
	//saving to patient table - email must me unique
	java.sql.PreparedStatement stmt1=con.prepareStatement("insert into patient values(0,?,?,?,?,?)");  
	stmt1.setString(1,patient.getName());
	stmt1.setString(2,patient.getPlace());
	stmt1.setString(3,patient.getGender()); 
	stmt1.setInt(4,patient.getAge()); 
	stmt1.setString(5, patient.getEmail());
	j = stmt1.executeUpdate();  
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}  
	if (i==1 && j ==1) {
		System.out.println("**********Patient registration completed**********");
	}
	return patient;
	}

}
