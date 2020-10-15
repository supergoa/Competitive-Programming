//gotta stack trick
//here
public class CLASSNAME implements Runnable {
    
    public static void main(String[] args) {
        new Thread(null, new CLASSNAME(), "lol", (1L<<30)).start();
    }
    
//and then replace your

 public static void main(String[] args) { 

//with 

public void run() {