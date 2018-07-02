package Util;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ErrorBuilder {
	
	private EmbedBuilder embed;
	private MessageReceivedEvent event;
	private String des;
	private String cmd;
	
	public ErrorBuilder(MessageReceivedEvent event, String description, String command) {
		this.event = event;
		des = description;
		cmd = command;
		embed = new EmbedBuilder();
	}
	
	public void sendError() {
		embed.setDescription("A Error occurred");
		embed.setColor(Color.RED);
		embed.addBlankField(true);
		embed.addField(Config.getString("prefix")+cmd, des, false);
		event.getTextChannel().sendMessage(embed.build()).queue();;
	}

}
