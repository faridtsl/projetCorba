package Client;

import org.omg.CORBA.*;


public class ClientServer
{
  public static void main(String args[])
  {
      try {
    	  if (args.length < 1) {
				System.out.println("Please use : java BankServer <banks port>");
    	  } else {
    		  String banksPort = args[0];
				
    		  // create and initialize the ORB
    		  String arg[] = {"-ORBInitialPort",banksPort,"-ORBInitialHost","localhost"};
	          ORB orb = ORB.init(arg, null);
	          
	          Client cli1 = new Client(orb,"NameService",1,"Client1",200);
	          Client cli2 = new Client(orb,"NameService",1,"Client2",100);
	          Client cli3 = new Client(orb,"NameService",2,"Client3",500);
	          Client cli4 = new Client(orb,"NameService",3,"Client4",300);
	          
	          // Create Account
	          
	          cli1.createAccount(150);
	          cli2.createAccount(100);
	          cli3.createAccount(400);
	          cli4.createAccount(200);
	          
	          System.out.println(cli1);
	          System.out.println(cli2);
	          System.out.println(cli3);
	          System.out.println(cli4);
	          
	          cli1.deposit(10);
	          cli2.withdraw(10);
	          cli3.deposit(50);
	          cli4.withdraw(50);
	          
	          System.out.println(cli1);
	          System.out.println(cli2);
	          System.out.println(cli3);
	          System.out.println(cli4);
	          
	          cli3.transfert(cli2, 100);
	          
	          System.out.println(cli1);
	          System.out.println(cli2);
	          System.out.println(cli3);
	          System.out.println(cli4);
	          
	          Thread.sleep(13000);
	          
	          System.out.println(cli1);
	          System.out.println(cli2);
	          System.out.println(cli3);
	          System.out.println(cli4);
	          
	          cli3.transfert(cli1, 100);
	          cli2.transfert(cli1, 50);
	          cli4.transfert(cli3, 20);
	          

	          System.out.println(cli1);
	          System.out.println(cli2);
	          System.out.println(cli3);
	          System.out.println(cli4);
	          
	          Thread.sleep(13000);
	          
	          System.out.println(cli1);
	          System.out.println(cli2);
	          System.out.println(cli3);
	          System.out.println(cli4);
    	  }
          
      } catch (Exception e) {
          System.err.println(e.getMessage());
          e.printStackTrace();
      }
  }
}
