package Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class User {
	
	public static String ID_TO_USER(Long id) {
		
		String getter = null;
        URL u = null;
		try {
			u = new URL("https://enjuu.click/api/v1/users?id="+id);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
        try{
            URLConnection urlConnection = u.openConnection();
            urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0");
            
            try (InputStream stream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(stream);
                BufferedReader reader = new BufferedReader(inputStreamReader)) {

              StringBuilder result = new StringBuilder();
              String tmp;
              while ((tmp = reader.readLine()) != null) {
                result.append(tmp).append("\n");
              }
              getter = result.toString();
            }
        }catch (Exception e){
        	
        }
            JSONParser parser = new JSONParser();
            try {
            	
            	File file = new File("idtouser.json");
            		  
            		   file.delete();
            		   
            		
            	   
            	PrintWriter writer = new PrintWriter("idtouser.json", "UTF-8");
            	writer.println(getter);
            	writer.close();
            	
            	Object obj = parser.parse(new FileReader(
            			"idtouser.json"));
     
                JSONObject jsonObject = (JSONObject) obj;
                String username = (String) jsonObject.get("username");
                return username;
            }catch (Exception e){
            	
            }
		
		return null;
	}
	
	public static Long USER_TO_ID(String user){
		
		String getter = null;
        URL u = null;
		try {
			u = new URL("https://enjuu.click/api/v1/users?name="+user);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
        try{
            URLConnection urlConnection = u.openConnection();
            urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0");
            
            try (InputStream stream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(stream);
                BufferedReader reader = new BufferedReader(inputStreamReader)) {

              StringBuilder result = new StringBuilder();
              String tmp;
              while ((tmp = reader.readLine()) != null) {
                result.append(tmp).append("\n");
              }
              getter = result.toString();
            }
        }catch (Exception e){
        	
        }
            JSONParser parser = new JSONParser();
            try {
            	
            	File file = new File("usertoid.json");
            		  
            		   file.delete();
            		   
            		
            	   
            	PrintWriter writer = new PrintWriter("usertoid.json", "UTF-8");
            	writer.println(getter);
            	writer.close();
            	
            	Object obj = parser.parse(new FileReader(
            			"usertoid.json"));
     
                JSONObject jsonObject = (JSONObject) obj;
                Long id = (Long) jsonObject.get("id");
                return id;
            }catch (Exception e){
            	
            }
		
		return null;
	}

}
