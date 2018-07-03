package Commands;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import Util.Config;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class cmdInvite implements Command{

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event)
			throws FileNotFoundException, UnsupportedEncodingException {
		EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.BLACK);
        eb.addBlankField(false);
		eb.appendDescription("Invite this Bot to your Server!");
		eb.addField("Link:", "https://discordapp.com/api/oauth2/authorize?client_id=" + Config.getString("clientid")+ "&permissions=0&scope=bot", false);
		event.getTextChannel().sendMessage(eb.build()).queue();;
		
	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		
	}

}
