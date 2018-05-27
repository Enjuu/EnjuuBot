package Listener;


import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.GuildController;

public class arlistener extends ListenerAdapter{

	
	public void onAR(GuildMemberJoinEvent event) {
		System.out.println("yeeeeees");
		GuildController g = new GuildController(event.getGuild());
		System.out.println(g.getGuild().getName());
		
		g.addRolesToMember(event.getMember(), g.getGuild().getRolesByName("Member", true));
		System.out.println(event.getMember().toString());
		
		
		
	}
}
