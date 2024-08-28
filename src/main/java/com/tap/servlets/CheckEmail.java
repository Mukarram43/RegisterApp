package com.tap.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckEmail extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		String phoneNumber = req.getParameter("phoneNumber");
		String password = req.getParameter("password");
		String cpassword = req.getParameter("cpassword");
		String address = req.getParameter("address");
		
		if (email.length()>=10) 
		{
			//servlet RegisterServlet
			
			HttpSession session = req.getSession();
			session.setAttribute("name", name);
			session.setAttribute("email", email);
			session.setAttribute("phoneNumber", phoneNumber);
			session.setAttribute("password", password);
			session.setAttribute("cpassword", cpassword);
			session.setAttribute("address", address);
			
			resp.sendRedirect("register");
		} 
		else 
		{
			resp.getWriter().println(name + "  Please enter valid Email ");
		}
		
		
	}
}
