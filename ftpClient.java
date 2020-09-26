//package com.mycompany.cntproject1;

import java.net.*;
import java.io.InputStreamReader;
import java.io.*;

public class ftpClient {
	
	public static void main(String[] args){
		
		Socket sock;
		String host;
		String words[] = new String [30]; //array to store command arguments entered by user
		
		if(args.length != 1){
			System.out.println("Invalid argument");
			System.exit(1);
		}	

		host = args[0];

		try{
			sock = new Socket(host, 21);
			PrintWriter out = new PrintWriter(sock.getOutputStream);
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));


		} catch(Exception e){
			System.out.println(e);
		}

	}

}
