package com.aerospikejdbcexample.aerospikejdbcdemo;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

@SpringBootApplication
public class AerospikeJdbcDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AerospikeJdbcDemoApplication.class, args);
		try {
			String url = "jdbc:aerospike:localhost:3000/FinQueryAerospikeDemo";
			Connection connection = DriverManager.getConnection(url);
			String query = "select * from AccountBalance where custId='Mahi@1232435' and ";
			ResultSet resultSet = connection.createStatement().executeQuery(query);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int numColumns = rsmd.getColumnCount();
			JSONObject result = new JSONObject();
			JSONObject columnNames = new JSONObject();
			int ji=0;
			while (resultSet.next() && ji==0){
				for(int i=1;i<numColumns+2;i++){
					String column = "acctName";
					if(i<numColumns+1){
						column = rsmd.getColumnName(i);
					}
					System.out.println(columnNames.has(column));
					if(columnNames.has(column)){
						System.out.println("ovfnvjdvkndvoivf");
						System.out.println(columnNames.get("acctName"));
						System.out.println(columnNames.get("acctName").equals("Praneeth"));
						if(columnNames.get("acctName").equals("Praneeth")){
							result.put("acctId",columnNames.get("acctId"));
							result.put("acctType",columnNames.get("acctType"));
							result.put("acctBal",columnNames.get("acctBal"));
							ji=1;
							break;
						}
						else{
							System.out.println(columnNames);
							columnNames = new JSONObject();
							System.out.println(columnNames);
							columnNames.put(column,resultSet.getString(column));
						}
					}
					else{
						columnNames.put(column,resultSet.getString(column));
					}
				}
				System.out.println(result);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
