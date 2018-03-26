package net.genpt.ppse.logintoTAMS;

import org.sikuli.script.App;
import org.sikuli.script.Key;
import org.sikuli.script.Region;
import net.genpt.ppse.sikuli.utility.SikuliUtility;

public class LoginToTAMSII extends SikuliUtility{
	
	public String loginToTAMS() throws Exception{
		System.out.println("I amsadfsafsdafsadffsadf");  
		String[] command = {"cmd.exe", "/C", "Start", ""};        
		Runtime.getRuntime().exec( "c:\\windows\\system32\\net.exe use w: \\\\10.10.10.30\\Client /User:genpt\\skinnynt password /P:yes");
		Thread.sleep(1000);
		Runtime.getRuntime().exec(command);
		App.focus("cmd");
		Region R = App.focusedWindow();
		Thread.sleep(1000);
		R.type("W:"+Key.ENTER);
		Thread.sleep(1000);
		R.type("start.bat"+Key.ENTER);
		App TAMS;
		int waitInc = 0;
		do {
          TAMS = App.focus("TAMS - Login");
          if (TAMS!=null)
                break;
          System.out.println(TAMS);
          Thread.sleep(7000);
          waitInc++;
          System.out.println("Loop..");           
		}while(waitInc < 30);
		
		Thread.sleep(20000);
		type("Enumber","1");
		waitTypeValEnter("EPassword1","tams123");
		Thread.sleep(20000);
		waitTypeValEnter("CustNum","0");
		Thread.sleep(5000);
		click("No");
		pressKey("F9");
		System.out.println("I am in TAMSII Login");
		Thread.sleep(20000);
		String url;
		url = getURL("Browser_URL3");
		return url;
	}

}
