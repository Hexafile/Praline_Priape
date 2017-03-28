package fr.iutinfo.skeleton.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/Upload")
public class Upload extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.getWriter().println("TEST TEST TEST TEST");
	}

}
