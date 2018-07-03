package Commands;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import API.User;
import API.UserType;
import API.isOnline;
import Util.Config;
import Util.ErrorBuilder;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class cmdIsOnline implements Command{

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event)
			throws FileNotFoundException, UnsupportedEncodingException {
		if(args.length == 0) {
			ErrorBuilder error = new ErrorBuilder(event, "The Command Usage is wrong.", "isonline <Name/ID>");
			error.sendError();
			return;
		}else if(args.length > 1) {
			ErrorBuilder error = new ErrorBuilder(event, "You give the Bot too much Arguments.", "isonline <Name/ID>");
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
				if(isOnline.check(u)) {
					EmbedBuilder e = new EmbedBuilder();
					e.setColor(Color.GREEN);
					e.setTitle("The Player "+u.getName()+ " is");
					e.appendDescription("Online");
					e.addBlankField(false);
					e.addField("Links", "[Profile](https://"+Config.getString("web")+"/u/"+u.getID()+")", false);
					event.getTextChannel().sendMessage(e.build()).queue();
				
				}else {
					EmbedBuilder e = new EmbedBuilder();
					e.setColor(Color.RED);
					e.setTitle("The Player "+u.getName()+ " is");
					e.appendDescription("Offline");
					e.addBlankField(false);
					e.addField("Links", "[Profile](https://"+Config.getString("web")+"/u/"+u.getID()+")", false);
					event.getTextChannel().sendMessage(e.build()).queue();
				}
			}else {
				User u = null;
				try {
					u = new User(UserType.NAME, args[0]);
				}catch(Exception e) {
					ErrorBuilder error = new ErrorBuilder(event, "This User don't exist.", "best <Name/ID>");
					error.sendError();
					return;
				}
				if(isOnline.check(u)) {
					EmbedBuilder e = new EmbedBuilder();
					e.setColor(Color.GREEN);
					e.setTitle("The Player "+u.getName()+ " is");
					e.appendDescription("Online");
					e.addBlankField(false);
					e.addField("Links", "[Profile](https://"+Config.getString("web")+"/u/"+u.getID()+")", false);
					event.getTextChannel().sendMessage(e.build()).queue();
				
				}else {
					EmbedBuilder e = new EmbedBuilder();
					e.setColor(Color.RED);
					e.setTitle("The Player "+u.getName()+ " is");
					e.appendDescription("Offline");
					e.addBlankField(false);
					e.addField("Links", "[Profile](https://"+Config.getString("web")+"/u/"+u.getID()+")", false);
					event.getTextChannel().sendMessage(e.build()).queue();
				
				}
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
