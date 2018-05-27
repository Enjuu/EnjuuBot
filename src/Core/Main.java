package Core;


import javax.security.auth.login.LoginException;

import Listener.arlistener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class Main {
	
	public static JDABuilder builder = new JDABuilder(AccountType.BOT);
	
	public static void main (String[] args) {
		builder.setToken("MzcxNzA5NzAzOTkxOTg0MTI4.DWICcQ.kbSeYiuPWNu1tI-jJJkkUiJZFfM");
		builder.setGame(Game.playing("I Like Potatoes"));
		builder.setAutoReconnect(true);
		builder.setStatus(OnlineStatus.ONLINE);
		
		builder.addEventListener(new arlistener());
		
		
		
	
		
		
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
