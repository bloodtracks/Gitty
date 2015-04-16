package edu.bsu.cs346;

public class ConnectToDatabase {
	private String url;
	 ConnectToDatabase(){
		 String serverName = "csor6.dhcp.bsu.edu";
			String portNumber = "1521";
			String sid = "or6db";
			 url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":"
					+ sid;
	}
	 
	 public String getUrl(){
		 return url;
	 }
}
