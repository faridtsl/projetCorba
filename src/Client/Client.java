package Client;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.Properties;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

import ServeurBancaire.BankCustomerHelper;
import ServeurBancaire.BankCustomer;

public class Client {
	
	private BankCustomer bank;
	private int accountID;
	private int money;
	private String name;
	private int bankId;
	
	private Client() {
		
	}
	
	public Client(ORB orb, String service, int bankId, String name, int money) {
		 try {	       
	          org.omg.CORBA.Object objRef;
	          objRef = orb.resolve_initial_references("NameService");
			  NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

	          
	          this.bankId = bankId;
	          
	          objRef = ncRef.resolve_str("bank"+bankId);
	          bank = BankCustomerHelper.narrow(objRef);        
	          this.name = name;
	          this.money = money;
	          this.accountID = 0;
	      } catch (Exception e) {
	          System.err.println(e.getMessage());
	          e.printStackTrace();
	      }
	}
	
	public void createAccount(int deposit) {
		if (deposit > money)
			deposit = money;
		accountID = bank.create(name, deposit);
		money -= deposit;
	}
	
	public void deposit(int amount) {
		if (amount > money)
			amount = money;
		bank.deposit(accountID, amount);
		money -= amount;
	}
	
	public void withdraw (int amount) {
		if (amount > bank.balance(accountID))
			amount = bank.balance(accountID);
		bank.withdraw(accountID, amount);
		money += amount;
	}
	
	int balance() {
		return bank.balance(accountID);
	}
	
	int cash() {
		return money;
	}
	
	void destroyAccount() {
		bank.destroy(accountID);
		accountID = 0;
	}
	
	int account() {
		return accountID;
	}
	
	void transfert (Client to, int amount) {
		if (amount > bank.balance(accountID))
			amount = bank.balance(accountID);
		bank.transfert(accountID, to.bankId, to.accountID, amount);
	}
	
	public String toString() {
		String str = name + " : " + cash() + " (cash) " + balance() + " (account - " + accountID + ") \n";
		str += "\t bank : " + bankId;
		return str; 
	}
}
