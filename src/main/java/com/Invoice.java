package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class Invoice 
{ //A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.cj.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", ""); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		
		public String insertService(String cus_nic, String month, Double unit_calculation) { 			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into items ('id','cus_nic','month','unit_calculation') values (?, ?, ?, ?, ?)";
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						 preparedStmt.setInt(1, 0); 
						 preparedStmt.setString(2, cus_nic); 
						 preparedStmt.setString(3, month); 
//						 preparedStmt.setDouble(4, unit_calculation); 
						 
							if (unit_calculation <= 0) {
								preparedStmt.setDouble(4, unit_calculation*0);
							}
							else if (unit_calculation <= 66) {
								preparedStmt.setDouble(4, unit_calculation*7.85);
							}
							else if (unit_calculation <= 99) {
								preparedStmt.setDouble(4, 66*7.85 + ((unit_calculation-66)*10));
							}
							else if (unit_calculation <= 132) {
								preparedStmt.setDouble(4, 66*7.85 + (33*10 + ((unit_calculation-99)*22.75)));
							}
							else if (unit_calculation <= 198) {
								preparedStmt.setDouble(4, 66*7.85 + (33*10 + (33*27.75 + ((unit_calculation-132)*32))));
							}
							else {
								preparedStmt.setDouble(4, 66*7.85 + (33*10 + (33*27.75 + (66*32 + ((unit_calculation-198)*45)))));
							}

						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						
						String newInvoice = readService(); 
						output = "{\"status\":\"success\",\"data\":\""+newInvoice+"\"}"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "{\"status\":\"error\", \"data\":\"Error while inserting the Invoice.\"}"; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
		public String readService() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the html table to be displayed
			output = "<table border='1'>"
					+ "<th> Name </th>"
					+ "<th> Address </th>"
					+ "<th>Customer NIC</th>"
					+ "<th>Month</th>" 
					+ "<th>Total Amount</th>"
					+ "<th>Action</th>"
					+ "<th>Action</th></tr>";
		
		 String query = "select p.address, p.name, i.id, i.cus_nic, i.month, i.unit_calculation from person p, invoice i where p.nic = i.cus_nic"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 {  
		 
			String id = Integer.toString(rs.getInt("id"));
			String name = rs.getString("p.name");
			String address = rs.getString("p.address");;				
			String cus_nic = rs.getString("i.cus_nic");
			String month = rs.getString("i.month");
			String unit_calculation = rs.getString("i.unit_calculation");
		 
		 
		 // Add into the html table
		 output += "<tr><td><input id='hidInvoiceIDUpdate' name='hidInvoiceIDUpdate' type='hidden' value='"+id+"'>"+name+"</td>"; 
		 output += "<td>" + address + "</td>"; 
		 output += "<td>" + cus_nic + "</td>"; 
		 output += "<td>" + month + "</td>"; 
		 output += "<td>" + unit_calculation + "</td>"; 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-secondary' data-Invoiceid='" + id + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btnRemove btn btn-danger' data-Invoiceid='" + id + "'></td></tr>"; 
		 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 
		catch (Exception e) 
		 { 
		 output = "Error while reading the Invoices."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

			
			
			public String updateService(String id, String cus_nic, String month, double unit_calculation){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE invoice SET cus_nic=?,month=?,unit_calculation=? WHERE id=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							preparedStmt.setString(1, cus_nic); 
							preparedStmt.setString(2, month); 
//							preparedStmt.setDouble(3, unit_calculation); 
							if (unit_calculation <= 0) {
								preparedStmt.setDouble(4, unit_calculation*0);
							}
							else if (unit_calculation <= 66) {
								preparedStmt.setDouble(4, unit_calculation*7.85);
							}
							else if (unit_calculation <= 99) {
								preparedStmt.setDouble(4, 66*7.85 + ((unit_calculation-66)*10));
							}
							else if (unit_calculation <= 132) {
								preparedStmt.setDouble(4, 66*7.85 + (33*10 + ((unit_calculation-99)*22.75)));
							}
							else if (unit_calculation <= 198) {
								preparedStmt.setDouble(4, 66*7.85 + (33*10 + (33*27.75 + ((unit_calculation-132)*32))));
							}
							else {
								preparedStmt.setDouble(4, 66*7.85 + (33*10 + (33*27.75 + (66*32 + ((unit_calculation-198)*45)))));
							}
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							String newInvoice = readService(); 
							output = "{\"status\":\"success\",\"data\":\""+newInvoice+"\"}"; 

					} 
					
					catch (Exception e){ 
						
						output = "{\"status\":\"error\",\"data\":\"Error while updating the Invoice.\"}"; 

						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deleteService(String id){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from invoice where id=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(id)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newInvoices = readService(); 
						 output = "{\"status\":\"success\",\"data\":\""+newInvoices+"\"}"; 

					} 
					
					catch (Exception e){ 
						output = "{\"status\":\"error\",\"data\":\"Error while deleting the Invoice.\"}";
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
			
			
			
			
} 
