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
			PrintWriter out = new PrintWriter(sock.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

			/**
			 * creates new object of helper class with all methods
			 * passes all printwriter and bufferedReaders
			 */

			ftpHelper ftp = new ftpHelper(out, in, user);

			 
			System.out.println("Connection Established ");
			System.out.println(in.read()); //reads first line upon connection

			String input; //string for user commands
			boolean flag = true; //prevents user from logging in again

			while(true){
				System.out.print("Enter Command: ");
				input = user.readLine();
				words = input.split(" "); //splits user command by spaces and places them in array
				int numWords = words.length;

				if(input.equals("quit")){
					ftp.QUIT(); 
                        		break;
				} else if(input.equals("login") && flag && (numWords ==1)){
					ftp.LOGIN();
					flag = false;
				} else if(input.equals("ls") && (numWords == 1)){
					ftp.LIST();	
				} else if(input.equals("cd") && (numWords == 2)){
					ftp.CD(words[1]);
				} else if(input.equals("delete") && (numWords == 2)){
					ftp.DELETE(words[1]);
				} else if(input.equals("get") && (numWords == 2)){
					ftp.GET(words[1]);
				} else if(input.equals("put") && (numWords == 2)){
					ftp.PUT(words[1]);
				} else if(!flag && input.startsWith("login") && numWords == 1){
					out.println("USER");
					out.flush();
					System.err.println(in.readLine());
				} else {
					System.out.println("Command not supported");
				}
			}
			/**
			 * closes all sockets and printwriters/bufferedreaders
			 */
			sock.close();
			user.close();
			out.close();
		} catch(Exception e){
			System.out.println(e);
		}

	}

}
