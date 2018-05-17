package Core;

import java.io.IOException;

import javax.security.auth.login.LoginException;

import org.json.simple.parser.ParseException;

import Commands.cmdBest;
import Commands.cmdHelp;
import Commands.cmdIsOnlineNew;
import Commands.cmdRecent;
import Commands.cmdUserNew;
import Listener.CommandListener;
import Listener.readyListener;
import Util.Config;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class Main {
	
	public static JDABuilder builder = new JDABuilder(AccountType.BOT);
	
	public static void main (String[] args) {
		Config.createConfig();
		try {
			Config.loadConfig();
		} catch (IOException | ParseException e1) {
			
			e1.printStackTrace();
		}
		builder.setToken(Config.getString("token"));
		builder.setGame(Game.playing(Config.getString("status")));
		builder.setAutoReconnect(true);
		builder.setStatus(OnlineStatus.ONLINE);
		
		
		builder.addEventListener(new CommandListener());
		builder.addEventListener(new readyListener());
		
		commandHandler.commands.put("eb:isonline", new cmdIsOnlineNew());
		commandHandler.commands.put("eb:recent", new cmdRecent());
		commandHandler.commands.put("eb:user", new cmdUserNew());
		commandHandler.commands.put("eb:best", new cmdBest());
		commandHandler.commands.put("eb:help", new cmdHelp());
		
		try {
			@SuppressWarnings("unused")
			JDA jda = builder.buildBlocking();
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (RateLimitedException e) {
			e.printStackTrace();
		}
		
	

	
	}
	

}
