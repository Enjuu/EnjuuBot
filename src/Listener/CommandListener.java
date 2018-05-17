package Listener;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import Core.commandHandler;

public class CommandListener extends ListenerAdapter {

    @SuppressWarnings({ "deprecation" })
	@Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getMessage().getContent().contains("eb:") && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()) {
            try {
				commandHandler.handleCommand(commandHandler.parser.parse(event.getMessage().getContent(), event));
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

    }
}
