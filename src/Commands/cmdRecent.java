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
import Util.kamojiAPI;
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
			EmbedBuilder error = Static.CREATE_NORMAL_ERROR("Err", "Wrong Arguments", kamojiAPI.get(kamojiAPI.kamojitype.JOY), Config.getString("prefix")+"best <Name/ID>");
			event.getTextChannel().sendMessage(error.build()).queue();;
		}else if(args.length > 1) {
			EmbedBuilder error = Static.CREATE_NORMAL_ERROR("Err", "Too much Arguments?", kamojiAPI.get(kamojiAPI.kamojitype.JOY), Config.getString("prefix")+"best <Name/ID>");
			event.getTextChannel().sendMessage(error.build()).queue();;
		}else{
			try{
				
				// -------- IS A ID ---------
				String getter = null;
		        URL u = null;
				try {
					u = new URL(Config.getString("apiprotocol")+"://"+Config.getString("api")+"/api/v1/users/scores/recent?id="+args[0]);
				} catch (MalformedURLException e1) {
				
					
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
		            	
		            	//get Infos
		            	
		            	
		            	Long score = null;
		            	Long max_combo = null;
						Long pp = null;
						String rank = null;
		            	
						String song_name = null;
						Long bm_id = null;
		            	Long diff = null;
						Long max_combo_map = null;
						Long bmid = null;
		            	
						JSONObject obj = new JSONObject(content);
						JSONArray arr = obj.getJSONArray("scores");
						for (int i = 0; i < arr.length(); i++)
						{
							if(i == 0) {
								rank = arr.getJSONObject(i).getString("rank");
							    score = arr.getJSONObject(i).getLong("score");
								max_combo = arr.getJSONObject(i).getLong("max_combo");
								arr.getJSONObject(i).getBoolean("full_combo");
								pp = arr.getJSONObject(i).getLong("pp");
								song_name = arr.getJSONObject(i).getJSONObject("beatmap").getString("song_name");
								bm_id = arr.getJSONObject(i).getJSONObject("beatmap").getLong("beatmapset_id");
								bmid = arr.getJSONObject(i).getJSONObject("beatmap").getLong("beatmap_id");
								diff = arr.getJSONObject(i).getJSONObject("beatmap").getLong("difficulty");
								max_combo_map = arr.getJSONObject(i).getJSONObject("beatmap").getLong("max_combo");
								arr.getJSONObject(i);
								
							}else{
								
							}
						   
						}
						
						String endnumber = "";
						
						String number = String.valueOf(diff);
						for(int i = 0; i < number.length(); i++) {
						    int j = Character.digit(number.charAt(i), 10);
						    endnumber = endnumber + j;
						    if(i == 4) {
						    	return;
						    }
						}
						
						endnumber.toString();
			                EmbedBuilder eb = new EmbedBuilder();
			                
			                eb.setColor(Color.BLACK);
			                eb.setAuthor(User.ID_TO_USER(Long.parseLong(args[0]))+ " scored on " + song_name + " (" + endnumber.toString() + "★)", "https://osu.ppy.sh/b/"+ bmid, Config.getString("webprotocol")+"://a."+Config.getString("web")+"/"+args[0]);
			                
			                //Set BM Image
			               eb.setThumbnail("https://b.ppy.sh/thumb/"+bm_id+".jpg");
			               eb.appendDescription("Scored a "+ rank+ " with a combo of "+max_combo+"/"+max_combo_map+". He got "+pp+"pp and a Score of "+score);
			          
			               event.getTextChannel().sendMessage(eb.build()).queue();;
						
						
				
			}catch (Exception e) {
				
				// -------- IS A Username ---------
					String getter = null;
			        URL u = null;
					try {
						u = new URL(Config.getString("apiprotocol")+"://"+Config.getString("api")+"/api/v1/users/scores/recent?name="+args[0]);
					} catch (MalformedURLException e12) {
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
			            	
							Long score = null;
			            	Long max_combo = null;
							Long pp = null;
							String rank = null;
			            	
							String song_name = null;
							Long bm_id = null;
			            	Long diff = null;
							Long max_combo_map = null;
							Long bmid = null;
			            	
							JSONObject obj = new JSONObject(content);
							JSONArray arr = obj.getJSONArray("scores");
							for (int i = 0; i < arr.length(); i++)
							{
								if(i == 0) {
									rank = arr.getJSONObject(i).getString("rank");
								    score = arr.getJSONObject(i).getLong("score");
									max_combo = arr.getJSONObject(i).getLong("max_combo");
									arr.getJSONObject(i).getBoolean("full_combo");
									pp = arr.getJSONObject(i).getLong("pp");
									song_name = arr.getJSONObject(i).getJSONObject("beatmap").getString("song_name");
									bm_id = arr.getJSONObject(i).getJSONObject("beatmap").getLong("beatmapset_id");
									bmid = arr.getJSONObject(i).getJSONObject("beatmap").getLong("beatmap_id");
									diff = arr.getJSONObject(i).getJSONObject("beatmap").getLong("difficulty");
									max_combo_map = arr.getJSONObject(i).getJSONObject("beatmap").getLong("max_combo");
									arr.getJSONObject(i);
									
								}else{
									
								}
							   
							}
							
							String endnumber = "";
							
							String number = String.valueOf(diff);
							for(int i = 0; i < number.length(); i++) {
							    int j = Character.digit(number.charAt(i), 10);
							    endnumber = endnumber + j;
							    if(i == 4) {
							    	return;
							    }
							}
							
							endnumber.toString();
				                EmbedBuilder eb = new EmbedBuilder();
				                
				                eb.setColor(Color.BLACK);
				                eb.setAuthor(args[0] + " scored on " + song_name + " (" + endnumber.toString() + "★)", "https://osu.ppy.sh/b/"+ bmid, Config.getString("webprotocol")+"://a."+Config.getString("web")+"/"+User.USER_TO_ID(args[0]));
				                
				                //Set BM Image
				               eb.setThumbnail("https://b.ppy.sh/thumb/"+bm_id+".jpg");
				               eb.appendDescription("Scored a "+ rank+ " with a combo of "+max_combo+"/"+max_combo_map+". They got "+pp+"pp and a Score of "+score);
				          
				               event.getTextChannel().sendMessage(eb.build()).queue();;
				   
							
							
					
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
