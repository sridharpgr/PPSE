package net.genpt.ppse.sikuli.utility;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class SikuliUtility {
	
	private Screen screen = new Screen();
	Pattern pattern;
	Match match;
	
	 public void click(String imageName) throws Exception {
		 String imgPath =System.getProperty("user.dir")+"\\src\\main\\java\\net\\genpt\\ppse\\sikuli\\resources\\" + imageName + ".png";
	     pattern = new Pattern(imgPath);
	     if (screen.exists(pattern) != null){
	    	 match = screen.find(pattern);
	         screen.click(match);
	     }
	     else
	    	 System.out.println("Object: " + imageName + " Not available in screen");
	      Thread.sleep(1000);
	  }
	 
	 public void doubleClick(String imageName) throws Exception {
		 String imgPath =System.getProperty("user.dir")+"\\src\\main\\java\\net\\genpt\\ppse\\sikuli\\resources\\" + imageName + ".png";
	     pattern = new Pattern(imgPath);
	     if (screen.exists(pattern) != null){
	    	 match = screen.find(pattern);
	         screen.doubleClick(match);
	     }
	     else
	    	 System.out.println("Object: " + imageName + " Not available in screen");
	      Thread.sleep(1000);
	  }
	 
	 public void waitTypeValEnter(String imageName,String value) throws FindFailed, InterruptedException
     {
		 String imgPath =System.getProperty("user.dir")+"\\src\\main\\java\\net\\genpt\\ppse\\sikuli\\resources\\" + imageName + ".png";
		 pattern = new Pattern(imgPath);
		 screen.wait(pattern,5);
		 Thread.sleep(1000);
         screen.type(value);
         Thread.sleep(1000);
         screen.type(Key.ENTER);
     }
	 
	 public void type(String imageName,String value) throws Exception {
        String imgPath =System.getProperty("user.dir")+"\\src\\main\\java\\net\\genpt\\ppse\\sikuli\\resources\\" + imageName + ".png";
        pattern = new Pattern(imgPath);
        if (screen.exists(pattern) != null){
        	match = screen.find(pattern);
            screen.type(match,value);
            screen.type(Key.TAB);
        }
        else
            System.out.println("Object: " + imageName + " Not available in screen");
        Thread.sleep(1000);
	  }
	 
	 public String getURL(String browserURL) throws Exception {
		String url = null;
		try {
			click(browserURL);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		screen.type("c", Key.CTRL);
		Clipboard clippy = Toolkit.getDefaultToolkit().getSystemClipboard();
		try {
			url = clippy.getData(DataFlavor.stringFlavor).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ppse_browser_url: "+url);
		return url;
	 }
	 
	 public void pressKey(String key) throws FindFailed, InterruptedException
     {
		 Thread.sleep(2000);
		 if(key=="F9")
			 screen.type(Key.F9);
		 if(key=="enter")
			 screen.type(Key.ENTER);
     }
}
