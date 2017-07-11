/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn;

/**
 *
 * @author CARLOS OSORIO
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class FrontEnd {
	public static void main(String[] args) throws IOException {
		
		int portnumberBE=4444;
                int portnumberCL=4445;
                
                ColaBE cola1=new ColaBE();
                ColaCL cola2=new ColaCL();
		
		ServerSocket serverSocket = new ServerSocket(portnumberCL);
        Socket clientSocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream()));
		
               
        
        ServerSocket serverSocket1 = new ServerSocket(portnumberBE);
        Socket clientSocket1 = serverSocket.accept();
        PrintWriter out1 = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in1 = new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream()));
        
        String inputLine, outputLine, outputLine1;
        
        outputLine = mensajeBE(null);
        
        out.println(outputLine);
        String ip=ipretorno();
        
        
        outputLine1 = mensajeCL(null);
        
        out.println(outputLine1);
               
        if(outputLine=="iamalive"){
        	System.out.println("El servidor con la ip " + ip + " esta vivo");
        	escribirarchivo(ip);
                cola1.eliminarcola();
        	
        }
        
        else{
        	
        	System.out.println("El servidor con la ip " + ip + " esta inactivo u ocupado");
        	
        	cola1.eliminarcola();
        	
        }
        
		
	}

	
	
	public static String ipretorno() throws UnknownHostException{
		
		InetAddress address = InetAddress.getLocalHost();
    	
        String sHostName;
        sHostName = address.getHostName();
        
        // Cogemos la IP 
        byte[] bIPAddress = address.getAddress();
         
        // IP en formato String
        String sIPAddress = "";
         
        for (int x=0; x<bIPAddress.length; x++) {
          if (x > 0) {
            // A todos los numeros les anteponemos
            // un punto menos al primero    
            sIPAddress += ".";
          }
          // Jugamos con los bytes y cambiamos el bit del signo
          sIPAddress += bIPAddress[x] & 255;	   
        }
		
		return sIPAddress;
	}
	
	
	
	public static String mensajeBE(String theInput){
            
            ColaBE colabe=new ColaBE();
		
	    final int WAITING = 0;
	    int state = WAITING;
		
		if(theInput.equalsIgnoreCase("iamalive")){
			
			state = WAITING;
		} 
                else{
			return theInput="iambusy";
                }
                
                colabe.agregarcola(theInput);
                
		return theInput;
	}
	
        
        public static String mensajeCL(String theInput){
            
                      
            ColaCL colacl=new ColaCL();
		
	    final int WAITING = 0;
	    int state = WAITING;
		
		if(theInput.equalsIgnoreCase("Hola")){
			
			state = WAITING;
		} 
                else{
			return theInput="Adios";
                }
                
                colacl.agregarcola(theInput);
                
		return theInput;
	}
	
	
	public static void escribirarchivo(String ip){
	
      
        try {
            File file = new File("C:\\Users\\USRBET\\Desktop\\servidoresBE.txt");
            FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
          
            buffer.append(ip+"\n");
            buffer.newLine();

            buffer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	public static boolean comparaarchivo(String ip) throws FileNotFoundException, IOException {
		
		FileReader f = new FileReader("C:\\Users\\USRBET\\Desktop\\servidoresBE.txt");
        BufferedReader b = new BufferedReader(f);
		
        if(b.readLine().contains(ip))
        {
        	return true;
        }
        else
        	
        
		return true;
	}
	
}
