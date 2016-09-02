package com.weekfiveproject;

import java.sql.*;
import java.util.ArrayList;

public class DAO 
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/?user=root&autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "root";

	static Connection CONN = null;
	static Statement STMT = null;
	static PreparedStatement PREP_STMT = null;
	static ResultSet RES_SET = null;
	
	public static final ArrayList<Bakery> ourBakery = new ArrayList<>();
	
	public static void connToDB() 
	{

		try 
		{

			Class.forName(JDBC_DRIVER);

			System.out.println("Trying to connect to the DB...");
			CONN = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected to DB.");

		} 
		
		catch (SQLException | ClassNotFoundException e) 
		{
			System.out.println("Connection failed");
			e.printStackTrace();
		}

	}
	
	public static void ViewDB() 
	{

		connToDB();

		try 
		{

			STMT = CONN.createStatement();
			RES_SET = STMT.executeQuery("SELECT * FROM bakery.bakery;");

			while (RES_SET.next())
			{

				Bakery itemInDB = new Bakery();

				itemInDB.setBakeryID((RES_SET.getString("bakery_id")));
				itemInDB.setType(RES_SET.getString("type"));
				itemInDB.setCalories(RES_SET.getString("calories"));
				itemInDB.setPrice(RES_SET.getString("price"));
				itemInDB.setTopping(RES_SET.getString("topping"));

				ourBakery.add(itemInDB);

			}

			for (Bakery bakery : ourBakery) 
			{
				System.out.println(bakery);
			}

		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	} // view
	
	private static String insertToDB = "INSERT INTO `bakery`.`bakery`" + "(type, calories, price, topping)"
			+ " VALUES " + "(?, ?, ?, ?)";

	public static void writeToDB(Bakery bakery)
	{

		Bakery itemToAdd = new Bakery();

		itemToAdd = bakery;

		try 
		{

			connToDB();

			PREP_STMT = CONN.prepareStatement(insertToDB);

			PREP_STMT.setString(1, itemToAdd.getType());
			PREP_STMT.setString(2, itemToAdd.getCalories());
			PREP_STMT.setString(3, itemToAdd.getPrice());
			PREP_STMT.setString(4, itemToAdd.getTopping());

			System.out.println(PREP_STMT);

			PREP_STMT.executeUpdate();

		} 
		
		catch (SQLException e) 
		
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // write
	
	private static String delFromDB = " DELETE FROM `bakery`.`bakery`" + " WHERE " + "bakery_id" + "= ?"; 
	
	public static void deleteFromDB(Bakery bakery)
	{
		Bakery itemToDelete = new Bakery();
		
		itemToDelete = bakery;
		
		connToDB();

		try 
		{
			
			PREP_STMT = CONN.prepareStatement(delFromDB);
			PREP_STMT.setString(1, itemToDelete.getBakeryID());
			PREP_STMT.executeUpdate();
			
		} 
		
		catch (SQLException e) 
		
		{
			e.printStackTrace();
		}

	} // delete
	
	private static String updateDB = "UPDATE `bakery`.`bakery`"
			+ "SET"
			+ " bakery_id= ?, type= ?, calories= ?, price= ?, topping= ?"
			+ " WHERE "
			+ "`bakery_id`"
			+ "= ?";
	
	public static void updateDB() 
	{

		Bakery itemToUpdate = new Bakery();
		
		connToDB();
		
		try 
		{
			PREP_STMT = CONN.prepareStatement(updateDB);
			
			PREP_STMT.setString(1, itemToUpdate.getBakeryID());
			PREP_STMT.setString(2, itemToUpdate.getType());
			PREP_STMT.setString(3, itemToUpdate.getCalories());
			PREP_STMT.setString(4, itemToUpdate.getPrice());
			PREP_STMT.setString(5, itemToUpdate.getTopping());
			PREP_STMT.setString(6, itemToUpdate.getBakeryID());
			
			PREP_STMT.executeUpdate();
			
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}//update
	

}
