package Commands;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import Util.Config;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class cmdHelp implements Command{

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event)
			throws FileNotFoundException, UnsupportedEncodingException {
		EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor(Config.getString("name") + "Bot Help");
        eb.setColor(Color.BLACK);
        eb.addBlankField(true);
		eb.addField(Config.getString("prefix")+"recent (Name/ID)", "Shows the last play of the Player." , false);
		eb.addField(Config.getString("prefix")+"best (Name/ID)", "Shows the best play of the Player." , false);
		eb.addField(Config.getString("prefix")+"isonline (Name/ID)", "Checks if the Player is Online." , false);
		eb.addField(Config.getString("prefix")+"user (Name/ID)", "Shows the profile of the Player." , false);
		eb.addField(Config.getString("prefix")+"help (Name/ID)", "Show all Commands of the Bot." , false);
		eb.addField(Config.getString("prefix")+"invite (Name/ID)", "Invite this bot to your Server" , false);
		event.getTextChannel().sendMessage(eb.build()).queue();
	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		
		
	}

}
