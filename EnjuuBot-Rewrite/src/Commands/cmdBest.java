package Commands;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import API.Score;
import API.User;
import API.UserType;
import Util.Config;
import Util.ErrorBuilder;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class cmdBest implements Command{

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event)
			throws FileNotFoundException, UnsupportedEncodingException {
		if(args.length == 0) {
			ErrorBuilder error = new ErrorBuilder(event, "The Command Usage is wrong.", "best <Name/ID>");
			error.sendError();
			return;
		}else if(args.length > 1) {
			ErrorBuilder error = new ErrorBuilder(event, "You give the Bot too much Arguments.", "best <Name/ID>");
			error.sendError();
			return;
		}else{
			if(isNumeric(args[0])) {
				User u = null;
				try {
					u = new User(UserType.ID, args[0]);
				}catch(Exception e) {
					ErrorBuilder error = new ErrorBuilder(event, "This User don't exist.", "best <Name/ID>");
					error.sendError();
					return;
				}
				Score s = null;
				try {
					s = u.getBestScore();
				} catch (Exception e) {
					ErrorBuilder error = new ErrorBuilder(event, "This User don't have a best Score.", "best <Name/ID>");
					error.sendError();
					return;
				}
				
				EmbedBuilder eb = new EmbedBuilder();
                
                eb.setColor(Color.BLACK);
                eb.setAuthor(u.getName() + " scored on " + s.getsong_name(), "https://osu.ppy.sh/b/"+ s.getbeatmap_id(),"https://a."+Config.getString("web")+"/"+u.getName());
                eb.setThumbnail("https://b.ppy.sh/thumb/"+s.getbeatmap_id()+".jpg");
                eb.appendDescription("Scored a "+ s.getRank()+ " with a combo of "+s.getMax_Combo()+"/"+s.getmap_max_combo()+". He/She got "+s.getPP()+"pp and a Score of "+s.getScore());
                eb.addField("Links:", "[Beatmap](https://osu.ppy.sh/beatmapsets/"+s.getbeatmapset_id()+") [Leaderboard](https://"+Config.getString("web")+"/b/"+s.getbeatmap_id()+") [Profile](https://"+Config.getString("web")+"/u/"+u.getID()+")", false);
               event.getTextChannel().sendMessage(eb.build()).queue();
			}else {
					User u = null;
					try {
						u = new User(UserType.NAME, args[0]);
					}catch(Exception e) {
						ErrorBuilder error = new ErrorBuilder(event, "This User don't exist.", "best <Name/ID>");
						error.sendError();
						return;
					}
					Score s = null;
					try {
						s = u.getBestScore();
					} catch (Exception e) {
						ErrorBuilder error = new ErrorBuilder(event, "This User don't have a best Score.", "best <Name/ID>");
						error.sendError();
						return;
					}
					
					EmbedBuilder eb = new EmbedBuilder();
	                eb.setColor(Color.BLACK);
	                eb.setAuthor(u.getName() + " scored on " + s.getsong_name(), "https://osu.ppy.sh/b/"+ s.getbeatmap_id(),"https://a."+Config.getString("web")+"/"+u.getID());
	                eb.setThumbnail("https://b.ppy.sh/thumb/"+s.getbeatmapset_id()+".jpg");
	                eb.appendDescription("Scored a "+ s.getRank()+ " with a combo of "+s.getMax_Combo()+"/"+s.getmap_max_combo()+". He/She got "+s.getPP()+"pp and a Score of "+s.getScore());
	                eb.addField("Links:", "[Beatmap](https://osu.ppy.sh/beatmapsets/"+s.getbeatmapset_id()+") [Leaderboard](https://"+Config.getString("web")+"/b/"+s.getbeatmap_id()+") [Profile](https://"+Config.getString("web")+"/u/"+u.getID()+")", false);
	                event.getTextChannel().sendMessage(eb.build()).queue();
			}
		}
		
	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		
	}
	
	private boolean isNumeric(String str) {
	    if (str == null) {
	        return false;
	    }
	    int sz = str.length();
	    for (int i = 0; i < sz; i++) {
	        if (Character.isDigit(str.charAt(i)) == false) {
	            return false;
	        }
	    }
	    return true;
	}

}
