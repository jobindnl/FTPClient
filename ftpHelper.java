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


    public void LIST(){
        String input;
        try {
            out.println("PASV"); //sets passive mode 
            out.flush();
            String portnum = in.readLine();
            System.out.println(portnum);
            int index = portnum.indexOf("(") + 1; //gets the index of the first digit of numbers 
            int indexLast = portnum.indexOf(")"); //gets the last index of the last digit 
            String newStr = portnum.substring(index, indexLast); //gets portion of the substring 
            portData = newStr.split(","); //splits it by comma's and puts it into an array
            
            int port1, port2;
            port1 = Integer.parseInt(portData[4]);
            port2 = Integer.parseInt(portData[5]);
            int newPort = (port1 * 256) + port2; //converts last ports into new port
            
            String hostNumberIP = portData[0] + "." + portData[1] + "." +portData[2] + "." + portData[3];
            
            sock2 = new Socket(hostNumberIP, newPort); //makes new socket connection for receiving data
            in2 = new BufferedReader(new InputStreamReader(sock2.getInputStream()));
            out.println("LIST");
            out.flush();

            in.readLine(); 
            in.readLine(); 

            while((input = in2.readLine()) != null) {
                System.out.println(input);
            } 
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    public void QUIT(){
        try {
            out.println("QUIT");
            out.flush();
            System.out.println(in.readLine());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void CD(String address){

        try {
            out.println("CWD " + address);
            out.flush();
            System.out.println(in.readLine());
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public void DELETE(String name){

            try {
                out.println("DELE " + name);
                out.flush();
                System.out.println(in.readLine());
            } catch (Exception e) {
                System.out.println(e);
            }

    }
    
}
