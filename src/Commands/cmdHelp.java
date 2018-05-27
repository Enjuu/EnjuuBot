package Commands;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import Util.Config;
import Util.Static;
import Util.kamojiAPI;
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
		
		try {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setAuthor(Config.getString("name") + "Bot Help");
            //Color
            eb.setColor(Static.CREATE_RANDOM_COLOR());
            eb.addBlankField(true);
			eb.addField(Config.getString("prefix")+"recent (Name/ID)", "Shows the Latest Play of the Player" , false);
			eb.addField(Config.getString("prefix")+"best (Name/ID)", "Shows the Best Play of the Player" , false);
			eb.addField(Config.getString("prefix")+"isonline (Name/ID)", "Checks if the Player is Online" , false);
			eb.addField(Config.getString("prefix")+"user (Name/ID)", "Shows the User of the Player" , false);
			event.getTextChannel().sendMessage(eb.build()).queue();;
            }catch (Exception e4){
            	
            	//Error Compiler
            	
            	EmbedBuilder eb = new EmbedBuilder();
				eb.setColor(Color.red);
				eb.setDescription("What's Wrong? "+ kamojiAPI.get(kamojiAPI.kamojitype.JOY));
				eb.addBlankField(true);
				eb.addField("WTF?", "", false);
				event.getTextChannel().sendMessage(eb.build()).queue();;
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
