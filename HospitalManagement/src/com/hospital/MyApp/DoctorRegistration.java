package com.hospital.MyApp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.hospital.beanClases.Doctor;


public class DoctorRegistration {
	private Scanner sc;

	public void DoctorPatient() throws SQLException, ClassNotFoundException {
		sc = new Scanner(System.in);
		Doctor doctor = new Doctor();
	System.out.println("*****Enter patient details*****");
	System.out.println("Enter Doctor Name: ");
	doctor.setName(sc.next());
	System.out.println("Enter Doctor Place: ");
	doctor.setPlace(sc.next());
	System.out.println("Enter Doctor Qualification: ");
	doctor.setQualification(sc.next());
	System.out.println("Enter Doctor Email: ");
	doctor.setEmail(sc.next());	
	System.out.println("Enter Doctor Password: ");
	doctor.setPassword(sc.next());
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagement","root","root");    
	java.sql.PreparedStatement stmt=con.prepareStatement("insert into doctor values(0,?,?,?,?)");  
	//saving to doctors table
	stmt.setString(1,doctor.getName());
	stmt.setString(2,doctor.getPlace());
	stmt.setString(3,doctor.getQualification());
	stmt.setString(4,doctor.getEmail());
	stmt.executeUpdate();
	  
	//saving to patient table - email must me unique
	java.sql.PreparedStatement stmt1=con.prepareStatement("insert into users values(0,?,?,?)");  
	stmt1.setString(1,doctor.getEmail());
	stmt1.setString(2,doctor.getPassword());
	stmt1.setString(3,"doctor"); 
	stmt1.executeUpdate();  
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}  
	System.out.println("*****DOCTOR REGISTRATION COMPLETED*****");
	Home home = new Home();
	home.ShowAdminMenu();
	}
}
