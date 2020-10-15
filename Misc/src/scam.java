import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class scam {
	public static void main(String[] args) throws AWTException, InterruptedException {
		new Thread().sleep(3000);
		String text1 = "green:Huge Payouts! (100k - 695M) 55x2 - Super Goa [01:37]";
		String text2 = "red:Min bet: 100k";
		String text3 = "red:Trade Declined [01:04]";
		StringSelection stringSelection = new StringSelection(text1);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);

		Robot robot = new Robot();
		sendKeys(robot, text1);
		//robot.keyPress(KeyEvent.VK_CONTROL);
		//robot.keyPress(KeyEvent.VK_V);
		//robot.keyRelease(KeyEvent.VK_V);
		//robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	static void sendKeys(Robot robot, String keys) {
	    for (char c : keys.toCharArray()) {
	    	int keyCode = 0;
	    	if(c==':') {
	    		//System.out.println("aye");
	    		robot.keyPress(KeyEvent.VK_SHIFT);
	    		robot.keyPress(KeyEvent.VK_SEMICOLON);
	    		robot.keyRelease(KeyEvent.VK_SEMICOLON);
	    		robot.keyRelease(KeyEvent.VK_SHIFT);
	    		continue;
	    	}
	    	if(c=='!') {
	    		//System.out.println("aye");
	    		robot.keyPress(KeyEvent.VK_SHIFT);
	    		robot.keyPress(KeyEvent.VK_1);
	    		robot.keyRelease(KeyEvent.VK_1);
	    		robot.keyRelease(KeyEvent.VK_SHIFT);
	    		continue;
	    	}
	    	if(c=='[') {
	    		//System.out.println("aye");
	    		//robot.keyPress(KeyEvent.VK_SHIFT);
	    		robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
	    		robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
	    		//robot.keyRelease(KeyEvent.VK_SHIFT);
	    		continue;
	    	}
	    	if(c==']') {
	    		//System.out.println("aye");
	    		robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
	    		robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
	    		continue;
	    	}
	    	if(c=='(') {
	    		robot.keyPress(KeyEvent.VK_SHIFT);
	    		robot.keyPress(KeyEvent.VK_9);
	    		robot.keyRelease(KeyEvent.VK_9);
	    		robot.keyRelease(KeyEvent.VK_SHIFT);
	    		continue;
	    	}
	    	if(c==')') {
	    		robot.keyPress(KeyEvent.VK_SHIFT);
	    		robot.keyPress(KeyEvent.VK_0);
	    		robot.keyRelease(KeyEvent.VK_0);
	    		robot.keyRelease(KeyEvent.VK_SHIFT);
	    		continue;
	    	}
	    	else keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
	        if (KeyEvent.CHAR_UNDEFINED == keyCode) {
	            throw new RuntimeException(
	                "Key code not found for character '" + c + "'");
	        }
	        robot.keyPress(keyCode);
	        //robot.delay(100);
	        robot.keyRelease(keyCode);
	       // robot.delay(100);
	    }
	}
}