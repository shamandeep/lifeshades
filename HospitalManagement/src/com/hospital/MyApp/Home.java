package com.hospital.MyApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.hospital.beanClases.Doctor;
import com.hospital.beanClases.Patient;


public class Home {

	private static Scanner sc;

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Home home = new Home();
		home.ShowHome();
	}
	
	public void ShowHome() throws ClassNotFoundException, SQLException {
		sc = new Scanner(System.in);
		while(true) {
		System.out.println("**********Select Option**********");
		System.out.println("1 - Login");
		System.out.println("2 - Patient Registration");
		System.out.print("Enter Option:");
		String option = sc.next();
		switch(Integer.parseInt(option)) {
		case 1:Login login = new Login();
		login.Login();
		break;
		case 2:PatientRegistration pc = new PatientRegistration();
		System.out.println(pc.RegisterPatient());
		break;
		default:System.out.println("Invalid option");
		break;
		}
		}
	}
	
	public void ShowAdminMenu() throws ClassNotFoundException, SQLException {
		sc = new Scanner(System.in);
		Home home = new Home();
		while(true) {
		System.out.println("**********WELCOME ADMIN**********");
		System.out.println("1 - Doctor Registration");
		System.out.println("2 - Logout");
		System.out.print("Enter Option:");
		String option = sc.next();
		switch(Integer.parseInt(option)) {
		case 1:DoctorRegistration doc = new DoctorRegistration();
		doc.DoctorPatient();
		System.out.println("*****DOCTOR REGISTRATION SUCCESSFULL");
		home.ShowAdminMenu();
		break;
		case 2:
		System.out.println("******LOGOUT SUCCESSFULL*****");
		home.ShowHome();
		break;
		default:System.out.println("Invalid option");
		break;
		}
		}
	}
	
	public void ShowPatinetMenu(Patient patient) throws ClassNotFoundException, SQLException {
		sc = new Scanner(System.in);
		Home home = new Home();
		while(true) {
		System.out.println("**********WELCOME "+patient.getEmail()+"**********");
		System.out.println("1 - Book Doctor");
		System.out.println("2 - Show Bookings");
		System.out.println("3 - Logout");
		System.out.print("Enter Option:");
		String option = sc.next();
		switch(Integer.parseInt(option)) {
		case 1:home.ShowBookDoctorMenu(patient);
		break;
		case 2:home.ShowPatientBooking(patient);
		break;
		case 3:
		System.out.println("******LOGOUT SUCCESSFULL*****");
		home.ShowHome();
		break;
		default:System.out.println("Invalid option");
		break;
		}
		}
	}
	
	public void ShowBookDoctorMenu(Patient patient) throws ClassNotFoundException, SQLException {
		sc = new Scanner(System.in);
		String diseas;
		Home home = new Home();
		while(true) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagement","root","root");
			java.sql.Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
			java.sql.ResultSet rs=stmt.executeQuery("select * from doctor");  
			int i =0;
			System.out.println("********** DOCTORS LIST **********");
			while (rs.next()) {
				i++;
				System.out.println(i+" - Dr "+rs.getString(2));
			}
		System.out.print("Select doctor:");
		String option = sc.next();
		System.out.print("Enter diseas:");
		diseas = sc.next();
		java.sql.PreparedStatement stmt1=con.prepareStatement("insert into booking values(0,?,?,?)");  
		stmt1.setString(1,String.valueOf(i));
		stmt1.setString(2,patient.getEmail());
		stmt1.setString(3,diseas); 
		stmt1.executeUpdate(); 
		System.out.println("******Booking Successfull******");
		home.ShowPatinetMenu(patient);
		}
	}

	public void ShowPatientBooking(Patient patient) throws ClassNotFoundException, SQLException {
		sc = new Scanner(System.in);
		Home home = new Home();
		while(true) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagement","root","root");
			java.sql.Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
			java.sql.ResultSet rs=stmt.executeQuery("select * from booking where user_email = '"+patient.getEmail()+"'");  
			int i =0;
			System.out.println("********** BOOKINGS **********");
			while (rs.next()) {
				i++;
				System.out.println(i+" - Dr Id"+rs.getString(2)+", Diseas:"+rs.getString(4));
			}
			System.out.println("1 - To go back");
			int j = sc.nextInt();
			if(j==1) {
			home.ShowPatinetMenu(patient);
			}
			else {
				System.out.println("***Enter a valid option***");
			}
		}
	}
}
