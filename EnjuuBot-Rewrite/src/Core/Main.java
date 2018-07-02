package Core;


import java.io.IOException;

import javax.security.auth.login.LoginException;

import org.json.simple.parser.ParseException;

import Commands.cmdBest;
import Commands.cmdLast;
import Listener.commandListener;
import Main.RippleAPI;
import Util.Config;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

public class Main {
	
	public static JDABuilder builder = new JDABuilder(AccountType.BOT);
	
	public static void main (String[] args) {
		Config.createConfig();
		try {
			Config.loadConfig();
		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
		}
		RippleAPI.setServer(Config.getString("api"));
		builder.setToken(Config.getString("token"));
		builder.setGame(Game.playing(Config.getString("status")));
		builder.setAutoReconnect(true);
		builder.setStatus(OnlineStatus.ONLINE);
		
		builder.addEventListener(new commandListener());
		
		commandHandler.commands.put(Config.getString("prefix")+"best", new cmdBest());
		commandHandler.commands.put(Config.getString("prefix")+"last", new cmdLast());
		
		try {
			@SuppressWarnings("unused")
			JDA jda = builder.buildBlocking();
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	

	
	}
	

}