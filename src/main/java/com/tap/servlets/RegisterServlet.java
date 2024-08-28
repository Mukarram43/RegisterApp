package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet
{
	String url="jdbc:mysql://localhost:3306/jee";
	String username="root";
	String pwd="root";
	private PrintWriter pw;
	private Connection connection;
	private PreparedStatement pstmt;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		HttpSession session = req.getSession();
		String name = (String) session.getAttribute("name");
		String email = (String) session.getAttribute("email");
		String password = (String) session.getAttribute("password");
		String cpassword = (String) session.getAttribute("cpassword");
		String address = (String) session.getAttribute("address");
		String phoneNumber1 = (String) session.getAttribute("phoneNumber");
		
		Long phoneNumber = Long.parseLong(phoneNumber1);
		System.out.println(" Output from register "+name + " " + email +" "+phoneNumber
				+" "+password+" "+cpassword+" "+address);
		
		
		
		
		
			
		
			pw = resp.getWriter();
			String sql = "insert into `userr` (`name`,`email`,`phonenumber`,`password`,`address`)"
					+ "value(?,?,?,?,?)";
			try 
			{
				
				
			
				
					if(password.equals(cpassword))
					{
						Class.forName("com.mysql.cj.jdbc.Driver");
						 connection = DriverManager.getConnection(url,username,pwd);
						  pstmt = connection.prepareStatement(sql);
						 
							pstmt.setString(1, name);
							pstmt.setString(2, email);
							pstmt.setLong(3, phoneNumber);
							pstmt.setString(4,password );
							pstmt.setString(5, address);
							
							int x = pstmt.executeUpdate();
							
							if(x!=0)
							{
								pw.println(name + " has registered");
							}
							else
							{
								pw.println(name + " was registered");
							}
					}
					else
					{
						pw.println(name +"  Password and Confirm Password Does not match! Please try again");
					}
				
			} 
			catch (Exception e) 
			{
				pw.println("Some Exception");
				e.printStackTrace();
			}
				
		
		
	}
}
