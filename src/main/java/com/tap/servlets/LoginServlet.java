package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet
{
	

	String url="jdbc:mysql://localhost:3306/jee";
	String username="root";
	String pwd="root";

	private PrintWriter pw;
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet res=null;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		pw = resp.getWriter();
		System.out.println("Received Control ");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		System.out.println(email+password);
		
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url,username,pwd);
			
			String sql = "select * from `userr` where `email`=? and `password`=?";
			
			pstmt =connection.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			 res = pstmt.executeQuery();
			 System.out.println(res);
			 if(res.next())
			 {
				 
				 String name = res.getString("name");
				 email = res.getString("email");
				 String phoneNumber = res.getString("phoneNumber");
				 String address = res.getString("address");
				 
				 Cookie c1 = new Cookie("name", name);
				 Cookie c2 = new Cookie("email", email);
				 Cookie c3 = new Cookie("phoneNumber", phoneNumber);
				 Cookie c4 = new Cookie("password", password);
				
				 
				 resp.addCookie(c1);
				 resp.addCookie(c2);
				 resp.addCookie(c3);
				 resp.addCookie(c4);
				
			
				
				 
				 
				 pw.println("Hello ! "+name );
			 }
			 else
			 {
				 pw.println("Invalid Credentials");
			 }
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
		
		
	}
	
}
