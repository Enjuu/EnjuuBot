package Commands;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Util.Static;
import Util.User;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class isonline implements Command{

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event)
			throws FileNotFoundException, UnsupportedEncodingException {
		
		if(args.length == 0) {
			System.out.println("lol");
			EmbedBuilder error = Static.CREATE_NORMAL_ERROR("404", "Wrong Arguments", "(ಥ﹃ಥ)", "-isonline <UserID>");
			event.getTextChannel().sendMessage(error.build()).queue();;
		}else if(args.length > 1) {
			System.out.println("lol2");
			EmbedBuilder error = Static.CREATE_NORMAL_ERROR("404", "Too much Arguments?", "ಠ_ಠ", "-isonline <UserID>");
			event.getTextChannel().sendMessage(error.build()).queue();;
		}else {
			String getter = null;
	        URL u = null;
			try {
				u = new URL("https://c.enjuu.click/api/v1/isOnline?id="+args[0]);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
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
	        	EmbedBuilder eb300 = new EmbedBuilder();
	        	eb300.setColor(Color.red);
	        	eb300.setDescription("404 UIDNF - Trust me that Username don't exist. (＞ｍ＜)");
	        	eb300.addBlankField(true);
				eb300.addField("-userid <UserID>", "", false);
				event.getTextChannel().sendMessage(eb300.build()).queue();;
	        }
	            JSONParser parser = new JSONParser();
	            
	            	
	            	File file = new File("user.json");
	            		  
	            		   file.delete();
	            		   
	            		
	            	   
	            	PrintWriter writer = new PrintWriter("user.json", "UTF-8");
	            	writer.println(getter);
	            	writer.close();
	            	
	            	Object obj = null;
					try {
						obj = parser.parse(new FileReader(
								"user.json"));
					} catch (IOException e) {
						
						e.printStackTrace();
					} catch (ParseException e) {
						
						e.printStackTrace();
					}
	     
	                JSONObject jsonObject = (JSONObject) obj;
	                Boolean result = (Boolean) jsonObject.get("result");
	                String message = (String) jsonObject.get("message");
	                Long status = (Long) jsonObject.get("status");
	                
	                if(result == true) {
	                	EmbedBuilder eb300 = new EmbedBuilder();
	    	        	eb300.setColor(Color.GREEN);
	    	        	Long id = Long.parseLong(args[0]); 
	    	        	eb300.setDescription(User.ID_TO_USER(id)+"("+args[0]+")"+" is online! (＞ｍ＜)");
	    	        	eb300.addBlankField(true);
	    	        	eb300.addField("yay! Go and Stalk him. Other Objects:", "Message: "+message + " Status: "+status, false);
	    				event.getTextChannel().sendMessage(eb300.build()).queue();;
	                }else{
	                	EmbedBuilder eb300 = new EmbedBuilder();
	    	        	eb300.setColor(Color.RED);
	    	        	Long id = Long.parseLong(args[0]); 
	    	        	eb300.setDescription(User.ID_TO_USER(id)+"("+args[0]+")"+" is not Online! (＞ｍ＜)");
	    	        	eb300.addBlankField(true);
	    				eb300.addField(":c Other Objects:", "Message: "+message + " Status: "+status, false);
	    				event.getTextChannel().sendMessage(eb300.build()).queue();;
	                }
	                System.out.println(jsonObject.toJSONString());
		}
		
	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		
		
	}

	@Override
	public String help() {
		
		return null;
	}

}
