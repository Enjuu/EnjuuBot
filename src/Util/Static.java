package Util;

import java.awt.Color;
import java.util.Random;

import net.dv8tion.jda.core.EmbedBuilder;

public class Static {
	
	
	
	public static String ver = "0.148 ALPHA/ERRORPRINTENABLED ";
	
	public static EmbedBuilder CREATE_NORMAL_ERROR(String errorcode, String Text, String kamoji,String Syntax) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setColor(Color.red);
		eb.setDescription(errorcode+" - "+ Text+ " "+kamoji);
		eb.addBlankField(true);
		eb.addField(Syntax, "", false);
		return eb;
		
	}
	
	public static EmbedBuilder CREATE_STACKTEXT_ERROR(String error) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setColor(Color.red);
		eb.setDescription("An Error expired!");
		eb.addBlankField(true);
		eb.addField(error, "Please Send this an Admin or Developer", false);
		return eb;
		
	}
	
	public static int CREATE_RANDOM_INT(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public static Color CREATE_RANDOM_COLOR() {
		
		 int rand = Static.CREATE_RANDOM_INT(1, 5);
         if(rand == 1) {
         	return Color.CYAN;
         }else if(rand == 2) {
         	return Color.MAGENTA;
         }else if(rand == 3) {
         	return Color.YELLOW;
         }else if(rand == 4) {
         	return Color.GREEN;
         }else{
         	//5
         	return Color.BLUE;
         }
		
	}
	
	
	
	
    
    
}



