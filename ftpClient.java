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

	}

}
