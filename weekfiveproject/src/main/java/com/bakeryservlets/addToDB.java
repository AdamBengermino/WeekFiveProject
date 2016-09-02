package com.bakeryservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weekfiveproject.Bakery;
import com.weekfiveproject.DAO;

/**
 * Servlet implementation class addToDB
 */
@WebServlet("/addToDB")
public class addToDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addToDB() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Bakery addTodb = new Bakery(); 
		
		addTodb.setType(request.getParameter("type"));
		addTodb.setCalories(request.getParameter("calories"));
		addTodb.setPrice(request.getParameter("price"));
		addTodb.setTopping(request.getParameter("topping"));
		
		DAO.writeToDB(addTodb);
		
		request.getRequestDispatcher("readDB.jsp").forward(request, response);
	}

}
