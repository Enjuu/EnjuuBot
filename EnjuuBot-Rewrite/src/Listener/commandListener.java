package Listener;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import Core.commandHandler;
import Util.Config;

public class commandListener extends ListenerAdapter {

	@Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getMessage().getContentRaw().contains(Config.getString("prefix")) && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()) {
            try {
				commandHandler.handleCommand(commandHandler.parser.parse(event.getMessage().getContentRaw(), event));
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
        }

    }
}