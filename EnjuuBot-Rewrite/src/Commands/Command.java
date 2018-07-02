package Commands;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface Command {

    boolean called(String[] args, MessageReceivedEvent event);
    void action(String[] args, MessageReceivedEvent event) throws FileNotFoundException, UnsupportedEncodingException;
    void executed(boolean success, MessageReceivedEvent event);

}