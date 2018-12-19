package com.wi4solutions.service;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.jsonwebtoken.io.IOException;

public class AsteriskManagerService{
	

    public AsteriskManagerService() throws IOException
    {
    }

    public static String run() throws java.io.IOException 
    {	
//    	String[] cmd = {"watch -n 1 asterisk -vvvvvrx 'core show channels verbose' "};
    	String[] cmd = {"tail","-200f","/home/nsanchez/AlodigaMantis.pem"};
        Process pb = Runtime.getRuntime().exec(cmd);

        String line;
        BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));
        while ((line = input.readLine()) != null) {
            System.out.println(line);
        }
        input.close();
        return line;
    }

    public static void main(String[] args) throws Exception
    {
    	System.out.println(AsteriskManagerService.run());
    }
}
