package Commands;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import API.User;
import API.UserType;
import Util.Config;
import Util.ErrorBuilder;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class cmdUser implements Command{

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event)
			throws FileNotFoundException, UnsupportedEncodingException {
		
		if(args.length == 0) {
			ErrorBuilder error = new ErrorBuilder(event, "The Command Usage is wrong.", "user <Name/ID>");
			error.sendError();
			return;
		}else if(args.length > 1) {
			ErrorBuilder error = new ErrorBuilder(event, "You give the Bot too much Arguments.", "user <Name/ID>");
			error.sendError();
			return;
		}else{
			if(isNumeric(args[0])) {
				User u = null;
				try {
					u = new User(UserType.ID, args[0]);
				} catch (Exception e) {
					ErrorBuilder error = new ErrorBuilder(event, "This User don't exist.", "user <Name/ID>");
					error.sendError();
					return;
				}
				EmbedBuilder em = new EmbedBuilder();
				em.setThumbnail("https://a."+Config.getString("web")+"/"+u.getID());
				em.setTitle("Profile of "+u.getName(), "https://"+Config.getString("web")+"/u/"+u.getID());
				em.addField("Username:", u.getName()+"("+u.getID()+")", false);
				em.addField("Latest Activity:", u.getLatest_Activity(), false);
				em.addField("Registered on:", u.getRegistered_On(), false);
				String s = new StringBuilder()
						.append(u.getPrivileges())
						.toString();
				em.addField("Privileges:", s, false);
				event.getTextChannel().sendMessage(em.build()).queue();;
			}else {
				User u = null;
				try {
					u = new User(UserType.NAME, args[0]);
				} catch (Exception e) {
					ErrorBuilder error = new ErrorBuilder(event, "This User don't exist.", "user <Name/ID>");
					error.sendError();
					return;
				}
				EmbedBuilder em = new EmbedBuilder();
				em.setThumbnail("https://a."+Config.getString("web")+"/"+u.getID());
				em.setTitle("Profile of "+u.getName(), "https://"+Config.getString("web")+"/u/"+u.getID());
				em.addField("Username:", u.getName()+"("+u.getID()+")", false);
				em.addField("Latest Activity:", u.getLatest_Activity(), false);
				em.addField("Registered on:", u.getRegistered_On(), false);
				String s = new StringBuilder()
						.append(u.getPrivileges())
						.toString();
				em.addField("Privileges:", s, false);
				event.getTextChannel().sendMessage(em.build()).queue();;
			}
		}
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

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		
	}

}
