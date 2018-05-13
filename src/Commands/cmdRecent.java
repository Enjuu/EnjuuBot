package Commands;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import Util.Config;
import Util.Static;
import Util.User;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class cmdRecent implements Command{

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event)
			throws FileNotFoundException, UnsupportedEncodingException {
		if(args.length == 0) {
			EmbedBuilder error = Static.CREATE_NORMAL_ERROR("404", "Wrong Arguments", "(ಥ﹃ಥ)", "-recent <Name/ID>");
			event.getTextChannel().sendMessage(error.build()).queue();;
		}else if(args.length > 1) {
			EmbedBuilder error = Static.CREATE_NORMAL_ERROR("404", "Too much Arguments?", "ಠ_ಠ", "-recent <Name/ID>");
			event.getTextChannel().sendMessage(error.build()).queue();;
		}else{
			try{
				
				// -------- IS A ID ---------
				String getter = null;
		        URL u = null;
				try {
					u = new URL(Config.getString("apiprotocol")+"://"+Config.getString("api")+"/api/v1/users/scores/recent?id="+args[0]);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					
				}
				
				//Loading
				 
				
				
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
		           
		         EmbedBuilder ebload = new EmbedBuilder();
				 ebload.setColor(Static.CREATE_RANDOM_COLOR());
				 ebload.setTitle("Loading User...");
				 ebload.setDescription("Some weird loading text!");
				 
				 event.getTextChannel().sendMessage(ebload.build()).queue( message -> message.delete().queueAfter(3, TimeUnit.SECONDS) );
		            	
		            	File file = new File("tmp/rc.json");
		            		  
		            		   file.delete();
		            		   
		            		
		            	   
		            	PrintWriter writer = new PrintWriter("tmp/rc.json", "UTF-8");
		            	writer.println(getter);
		            	writer.close();
		            	
		            	File file2 = new File("tmp/rc.json");
		            	String content = FileUtils.readFileToString(file2, "utf-8");
		            	
		            	Long id = null;
		            	Long score = null;
		            	Long max_combo = null;
		            	@SuppressWarnings("unused")
						Boolean full_combo = null;
		            	Long pp = null;
						
						String song_name = null;
		            	
						JSONObject obj = new JSONObject(content);
						JSONArray arr = obj.getJSONArray("scores");
						for (int i = 0; i < arr.length(); i++)
						{
							if(i == 0) {
								id = arr.getJSONObject(i).getLong("id");
							    score = arr.getJSONObject(i).getLong("score");
								max_combo = arr.getJSONObject(i).getLong("max_combo");
								full_combo = arr.getJSONObject(i).getBoolean("full_combo");
								pp = arr.getJSONObject(i).getLong("pp");
								song_name = arr.getJSONObject(i).getJSONObject("beatmap").getString("song_name");
								@SuppressWarnings("unused")
								JSONObject scoreEntry = arr.getJSONObject(i);
								
							}else{
								
							}
						   
						}
						
						try {
			                EmbedBuilder eb = new EmbedBuilder();
			                eb.setAuthor(User.ID_TO_USER(Long.parseLong(args[0])), Config.getString("webprotocol")+"://"+Config.getString("web")+"/u/"+args[0], Config.getString("webprotocol")+"://a."+Config.getString("web")+"/"+args[0]);
			                eb.setColor(Static.CREATE_RANDOM_COLOR());
			                
			                //Display
		    				eb.addField("Latest "+Config.getString("name")+ " Score of " + User.ID_TO_USER(Long.parseLong(args[0])), "", false);
		    				eb.addBlankField(true);
		    				eb.addField("Beatmap:", song_name + "("+id.toString()+")", false);
		    				eb.addField("Score:", score.toString(), false);
		    				eb.addField("Max Combo:", max_combo.toString(), false);
		    				eb.addField("PP:", pp.toString(), false);
		    				eb.addBlankField(true);
		    				eb.addField("Map Link:", Config.getString("webprotocol")+"://"+Config.getString("web")+"/b/"+id, false);
		    				event.getTextChannel().sendMessage(eb.build()).queue();;
			                }catch (Exception e4){
			                	
			                	//Error Compiler
			                	
			                	EmbedBuilder eb = new EmbedBuilder();
			    				eb.setColor(Color.red);
			    				eb.setDescription("404 UIDNF - Trust me that UserID don't exist. (＞ｍ＜)");
			    				eb.addBlankField(true);
			    				eb.addField("-recent <Name/ID>", "", false);
			    				event.getTextChannel().sendMessage(eb.build()).queue();;
			                }
						
						
				
			}catch (Exception e) {
				
				//Loading
				 EmbedBuilder ebload = new EmbedBuilder();
				 ebload.setColor(Static.CREATE_RANDOM_COLOR());
				 ebload.setTitle("Loading User...");
				 ebload.setDescription("Some weird loading text!");
				 
				 event.getTextChannel().sendMessage(ebload.build()).queue( message -> message.delete().queueAfter(3, TimeUnit.SECONDS) );
				
				
				// -------- IS A Username ---------
					String getter = null;
			        URL u = null;
					try {
						u = new URL(Config.getString("apiprotocol")+"://"+Config.getString("api")+"/api/v1/users/scores/recent?name="+args[0]);
					} catch (MalformedURLException e12) {
						// TODO Auto-generated catch bl
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
			        }catch (Exception e4){
			        	
			        }
			        		File file3 = new File("tmp/rc.json");
			        		try {
								file3.createNewFile();
							} catch (IOException e2) {
								
							}
			            	PrintWriter writer = new PrintWriter("tmp/rc.json", "UTF-8");
			            	writer.println(getter);
			            	writer.close();
			            	
			            	File file2 = new File("tmp/rc.json");
			            	
			            	String content = null;
							try {
								content = FileUtils.readFileToString(file2, "utf-8");
							} catch (IOException e1) {
								
							}
			            	
			            	Long id = null;
			            	Long score = null;
			            	Long max_combo = null;
			            	@SuppressWarnings("unused")
							Boolean full_combo = null;
			            	Long pp = null;
							
							String song_name = null;
			            	
							JSONObject obj = new JSONObject(content);
							JSONArray arr = null;
							try {
								arr = obj.getJSONArray("scores");
							}catch (Exception e5) {
								EmbedBuilder eb = new EmbedBuilder();
			    				eb.setColor(Color.red);
			    				eb.setDescription("404 UIDNF - Trust me that Name/ID don't exist. (＞ｍ＜)");
			    				eb.addBlankField(true);
			    				eb.addField("-recent <name/id>", "", false);
			    				event.getTextChannel().sendMessage(eb.build()).queue();;
							}
							for (int i = 0; i < arr.length(); i++)
							{
								if(i == 0) {
									id = arr.getJSONObject(i).getLong("id");
								    score = arr.getJSONObject(i).getLong("score");
									max_combo = arr.getJSONObject(i).getLong("max_combo");
									full_combo = arr.getJSONObject(i).getBoolean("full_combo");
									pp = arr.getJSONObject(i).getLong("pp");
									song_name = arr.getJSONObject(i).getJSONObject("beatmap").getString("song_name");
									@SuppressWarnings("unused")
									JSONObject scoreEntry = arr.getJSONObject(i);
									
								}else{
									
								}
							   
							}
							
							try {
				                EmbedBuilder eb = new EmbedBuilder();
				                eb.setAuthor(args[0], Config.getString("webprotocol")+"://"+Config.getString("web")+"/u/"+User.USER_TO_ID(args[0]), Config.getString("webprotocol")+"://a."+Config.getString("web")+"/"+User.USER_TO_ID(args[0]));
				                eb.setColor(Static.CREATE_RANDOM_COLOR());
				                
				                //Display
			    				eb.addField("Latest "+Config.getString("name")+ " Score of " + args[0], "", false);
			    				eb.addBlankField(true);
			    				eb.addField("Beatmap:", song_name + "("+id.toString()+")", false);
			    				eb.addField("Score:", score.toString(), false);
			    				eb.addField("Max Combo:", max_combo.toString(), false);
			    				eb.addField("PP:", pp.toString(), false);
			    				eb.addBlankField(true);
			    				eb.addField("Map Link:", Config.getString("webprotocol")+"://"+Config.getString("web")+"/b/"+id, false);
			    				event.getTextChannel().sendMessage(eb.build()).queue();;
				                }catch (Exception e4){
				                	
				                	//Error Compiler
				                	
				                	EmbedBuilder eb = new EmbedBuilder();
				    				eb.setColor(Color.red);
				    				eb.setDescription("404 UIDNF - Trust me that Name/ID don't exist. (＞ｍ＜)");
				    				eb.addBlankField(true);
				    				eb.addField("-recent <name/id>", "", false);
				    				event.getTextChannel().sendMessage(eb.build()).queue();;
				                }
							
							
					
			}
			
		}
		
	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		
		Static.ReturnMessage(event);
		
	}

	@Override
	public String help() {
		
		return null;
	}

}
