import java.net.*;
import java.io.*;


public class ftpHelper {
    
    private PrintWriter out;
    private BufferedReader in;
    private PrintWriter out2;
    private BufferedReader in2;
    private Socket sock2;
    private BufferedReader user;
    String portData[] =  new String[30];

     
   ftpHelper(PrintWriter out, BufferedReader in, BufferedReader user){
        this.out = out;
        this.in = in;
        this.user = user;
       
    }
    

    public boolean LOGIN(){

        try {
        System.out.print("Enter username: ");
        String userName = user.readLine();
        out.println("USER " + userName);
        out.flush();
        System.out.print(in.readLine() + ": ");
        String password = user.readLine();

        //System.out.println(in.readLine());
        out.println("PASS " + password);
        out.flush();
        System.out.println(in.readLine());
             } catch (Exception e) {
            System.out.println(e);
        }
        return false;
     }


}
