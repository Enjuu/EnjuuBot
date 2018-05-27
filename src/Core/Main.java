package Core;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
	
	
	public static void main (String[] args) {
		
		
		File folder = new File("plugins/");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	URL url = null;
				try {
					url = file.toURI().toURL();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}  
				URL[] urls = new URL[]{url};

				@SuppressWarnings("resource")
				ClassLoader cl = new URLClassLoader(urls);
				try {
					@SuppressWarnings({ "rawtypes", "unused" })
					Class cls = cl.loadClass("core.main");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
		    }
		}
		
	}
	

}
