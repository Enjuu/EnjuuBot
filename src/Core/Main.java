package Core;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.security.auth.login.LoginException;

import org.json.simple.parser.ParseException;

import Commands.cmdIsOnlineNew;
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
			Config.readConfig();
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		builder.setToken(Config.getString("token"));
		builder.setGame(Game.playing(Config.getString("status")));
		builder.setAutoReconnect(true);
		builder.setStatus(OnlineStatus.ONLINE);
		
		
		builder.addEventListener(new CommandListener());
		builder.addEventListener(new readyListener());
		
		commandHandler.commands.put("isonline", new cmdIsOnlineNew());
		commandHandler.commands.put("user", new cmdUserNew());
		
		try {
			@SuppressWarnings("unused")
			JDA jda = builder.buildBlocking();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RateLimitedException e) {
			
		}
		
	

	
	}
	

}
