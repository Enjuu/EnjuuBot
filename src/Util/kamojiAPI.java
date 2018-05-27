package Util;


import java.util.ArrayList;

public class kamojiAPI {
	
	public static ArrayList<String> joy = new ArrayList<>();
	public static String[] listjoy;
	
	public static ArrayList<String> love = new ArrayList<>();
	public static String[] listlove;
	
	public static enum kamojitype {
		JOY,
		LOVE,
		
	}
	
	public static void init() {
		joy.clear();
		joy.add("(* ^ ω ^)");
		joy.add("٩(◕‿◕｡)۶");
		joy.add("☆*:.｡.o(≧▽≦)o.｡.:*☆");
		joy.add("(´｡• ω •｡`)");
		joy.add("٩(◕‿◕｡)۶");
		joy.add("(o_ _)ﾉ彡☆");
		joy.add("(≧◡≦)");
		joy.add("╰(▔∀▔)╯");
		joy.add("(*≧ω≦*)");
		joy.add("＼(≧▽≦)／");
		joy.add("ヽ(o＾▽＾o)ノ");
		joy.add("☆ ～('▽^人)");
		joy.add("o(≧▽≦)o");
		joy.add("٩(◕‿◕)۶");
		
		System.out.println("[KamojiAPI - Beta]: Loaded Module Joy");
		
		listjoy = joy.toArray(new String[0]);
		
		
		love.clear();
		love.add("(ﾉ´ з `)ノ");
		love.add("(♡μ_μ)");
		love.add("☆⌒ヽ(*'､^*)chu");
		love.add("(￣ε￣＠)");
		love.add("(*♡∀♡)");
		love.add("(｡・//ε//・｡)");
		love.add("(/▽＼*)｡o○♡");
		love.add("(ღ˘⌣˘ღ)");
		love.add("(♡°▽°♡)");
		love.add("( ´ ▽ ` ).｡ｏ♡");
		love.add("(♡˙︶˙♡)");
		love.add("(≧◡≦) ♡");
		love.add("σ(≧ε≦σ) ♡");
		
		System.out.println("[KamojiAPI - Beta]: Loaded Module Love");
		
		listlove = love.toArray(new String[0]);
	}
	
	public static String get(kamojitype type) {
		switch(type) {
		
		case JOY:
			return (listjoy[(int) (Math.random() * listjoy.length)]);
		case LOVE:
	        return (listlove[(int) (Math.random() * listlove.length)]);
		}
		return null;
	}

}
