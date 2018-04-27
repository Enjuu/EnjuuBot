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
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Util.Config;
import Util.Static;
import Util.User;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class cmdUserNew implements Command{

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event)
			throws FileNotFoundException, UnsupportedEncodingException {
		if(args.length == 0) {
			EmbedBuilder error = Static.CREATE_NORMAL_ERROR("404", "Wrong Arguments", "(ಥ﹃ಥ)", "-user <Name/ID>");
			event.getTextChannel().sendMessage(error.build()).queue();;
		}else if(args.length > 1) {
			EmbedBuilder error = Static.CREATE_NORMAL_ERROR("404", "Too much Arguments?", "ಠ_ಠ", "-user <Name/ID> (Tipp: Replace Spaces with %20)");
			event.getTextChannel().sendMessage(error.build()).queue();;
		}else{
			try{
				Long p = Long.parseLong(args[0]);
				// -------- IS A ID ---------
				try{
					 //Loading
					 EmbedBuilder ebload = new EmbedBuilder();
					 ebload.setColor(Static.CREATE_RANDOM_COLOR());
					 ebload.setTitle("Loading User...");
					 ebload.setDescription("Some weird loading text!");
					 
					 event.getTextChannel().sendMessage(ebload.build()).queue( message -> message.delete().queueAfter(3, TimeUnit.SECONDS) );
					 	String getter = null;
				        URL u = new URL(Config.getString("webprotocol")+"://"+Config.getString("web")+"/api/v1/users?id="+args[0]);
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
				        	eb300.setDescription("404 UIDNF - Trust me that UserID don't exist. (＞ｍ＜)");
				        	eb300.addBlankField(true);
		    				eb300.addField("-userid <UserID>", "", false);
		    				event.getTextChannel().sendMessage(eb300.build()).queue();;
				        }
				            JSONParser parser = new JSONParser();
				            try {
				            	
				            	File file = new File("tmp/user.json");
				            		  
				            		   file.delete();
				            		   
				            		
				            	   
				            	PrintWriter writer = new PrintWriter("tmp/user.json", "UTF-8");
				            	writer.println(getter);
				            	writer.close();
				            	
				            	Object obj = parser.parse(new FileReader(
				            			"tmp/user.json"));
				     
				                JSONObject jsonObject = (JSONObject) obj;
				                
				                Long code = (Long) jsonObject.get("code");
				                if(code == 404) {
				                	EmbedBuilder eb = new EmbedBuilder();
				    				eb.setColor(Color.red);
				    				eb.setDescription("404 UIDNF - Trust me that UserID don't exist. (＞ｍ＜)");
				    				eb.addBlankField(true);
				    				eb.addField("-userid <UserID>", "", false);
				    				event.getTextChannel().sendMessage(eb.build()).queue();;
				                }else{
				                		String username = (String) jsonObject.get("username");
						                Long id = (Long) jsonObject.get("id");
						                String aka = (String) jsonObject.get("username_aka");
						                String country = (String) jsonObject.get("country");
						                String registered_on = (String) jsonObject.get("registered_on");
						                String latest_activity = (String) jsonObject.get("latest_activity");
						                Long privileges = (Long) jsonObject.get("privileges");
						                
						                // Embed Builder for User
						                try {
						                EmbedBuilder eb = new EmbedBuilder();
						                eb.setAuthor(username, Config.getString("webprotocol")+"://"+Config.getString("web")+"/u/"+id, Config.getString("webprotocol")+"://a."+Config.getString("web")+"/"+id);
						                //Color
						                
						                eb.setColor(Static.CREATE_RANDOM_COLOR());
						                
						                //Display
					    				eb.addField(Config.getString("name")+" User Profile", "", false);
					    				eb.addBlankField(true);
					    				System.out.println(aka + "2");
					    				eb.addField("Username:", username + "("+args[0]+")", false);
					    				eb.addField("Country:", country, false);
					    				eb.addField("Registered on:", registered_on, false);
					    				eb.addField("Latest activity:", latest_activity, false);
					    				eb.addBlankField(true);
					    				eb.addField("Privileges(for Developer):", privileges.toString(), false);
					    				eb.addBlankField(true);
					    				eb.addField("Profile:", Config.getString("webprotocol")+"://"+Config.getString("web")+"/u/"+id, false);
					    				event.getTextChannel().sendMessage(eb.build()).queue();;
						                }catch (Exception e3){
						                	
						                	//Error Compiler
						                	
						                	EmbedBuilder eb = new EmbedBuilder();
						    				eb.setColor(Color.red);
						    				eb.setDescription("404 UIDNF - Trust me that UserID don't exist. (＞ｍ＜)");
						    				eb.addBlankField(true);
						    				eb.addField("-userid <UserID>", "", false);
						    				event.getTextChannel().sendMessage(eb.build()).queue();;
						                }
					    				
				                }
				                
				     
				            } catch (Exception e3) {
				                e3.printStackTrace();
				            }
				          
				           
				        
				        
				        
				    }
				    catch (Exception e3)
				    {
				        System.out.println(e3.getMessage());
				    }
				
			}catch (Exception e) {
				
				// ---------- IS A USERNAME --------
				
				try{
					 //Loading
					 EmbedBuilder ebload = new EmbedBuilder();
					 ebload.setColor(Static.CREATE_RANDOM_COLOR());
					 ebload.setTitle("Loading User...");
					 ebload.setDescription("Some weird loading text!");
					 
					 event.getTextChannel().sendMessage(ebload.build()).queue( message -> message.delete().queueAfter(3, TimeUnit.SECONDS) );
					 	String getter = null;
				        URL u = new URL(Config.getString("apiprotocol")+"://"+Config.getString("api")+"/api/v1/users?name="+args[0]);
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
				        }catch (Exception e5){
				        	EmbedBuilder eb300 = new EmbedBuilder();
				        	eb300.setColor(Color.red);
				        	eb300.setDescription("404 UIDNF - Trust me that UserName don't exist. (＞ｍ＜)");
				        	eb300.addBlankField(true);
		    				eb300.addField("-user <Username>", "", false);
		    				event.getTextChannel().sendMessage(eb300.build()).queue();;
				        }
				            JSONParser parser = new JSONParser();
				            try {
				            	
				            	File file = new File("user.json");
				            		  
				            		   file.delete();
				            		   
				            		
				            	   
				            	PrintWriter writer = new PrintWriter("tmp/user.json", "UTF-8");
				            	writer.println(getter);
				            	writer.close();
				            	
				            	Object obj = parser.parse(new FileReader(
				            			"tmp/user.json"));
				     
				                JSONObject jsonObject = (JSONObject) obj;
				                
				                Long code = (Long) jsonObject.get("code");
				                if(code == 404) {
				                	EmbedBuilder eb = new EmbedBuilder();
				    				eb.setColor(Color.red);
				    				eb.setDescription("404 UIDNF - Trust me that UserID don't exist. (＞ｍ＜)");
				    				eb.addBlankField(true);
				    				eb.addField("-user <UserID>", "", false);
				    				event.getTextChannel().sendMessage(eb.build()).queue();;
				                }else{
				                		String username = (String) jsonObject.get("username");
						                Long id = (Long) jsonObject.get("id");
						                String aka = (String) jsonObject.get("username_aka");
						                String country = (String) jsonObject.get("country");
						                String registered_on = (String) jsonObject.get("registered_on");
						                String latest_activity = (String) jsonObject.get("latest_activity");
						                Long privileges = (Long) jsonObject.get("privileges");
						                
						                // Embed Builder for User
						                try {
						                EmbedBuilder eb = new EmbedBuilder();
						                eb.setAuthor(username, Config.getString("webprotocol")+"://"+Config.getString("web")+"/u/"+id, Config.getString("webprotocol")+"://a."+Config.getString("web")+"/"+id);
						                //Color
						                
						                eb.setColor(Static.CREATE_RANDOM_COLOR());
						                
						                //Display
					    				eb.addField(Config.getString("name")+" User Profile", "", false);
					    				eb.addBlankField(true);
					    				System.out.println(aka + "2");
					    				eb.addField("Username:", username + "("+id+")", false);
					    				eb.addField("Country:", country, false);
					    				eb.addField("Registered on:", registered_on, false);
					    				eb.addField("Latest activity:", latest_activity, false);
					    				eb.addBlankField(true);
					    				eb.addField("Privileges(for Developer):", privileges.toString(), false);
					    				eb.addBlankField(true);
					    				eb.addField("Profile:", Config.getString("webprotocol")+"://"+Config.getString("web")+"/u/"+id, false);
					    				event.getTextChannel().sendMessage(eb.build()).queue();;
						                }catch (Exception e4){
						                	
						                	//Error Compiler
						                	
						                	EmbedBuilder eb = new EmbedBuilder();
						    				eb.setColor(Color.red);
						    				eb.setDescription("404 UIDNF - Trust me that UserID don't exist. (＞ｍ＜)");
						    				eb.addBlankField(true);
						    				eb.addField("-user <UserID>", "", false);
						    				event.getTextChannel().sendMessage(eb.build()).queue();;
						                }
					    				
				                }
				                
				     
				            } catch (Exception e3) {
				                e.printStackTrace();
				            }
				          
				           
				        
				        
				        
				    }
				    catch (Exception e3)
				    {
				        System.out.println(e.getMessage());
				    }
			}
			
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
