package ServeurBancaire;

import java.util.*;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import InterBank.BankTransact;
import InterBank.BankTransactHelper;
import InterBank.Transact;

public class BankCustomerImpl extends BankCustomerPOA {

	private LinkedList<Account> accounts;
	private ORB orb;
	private int id;
	private BankTransact interbank;

	public BankCustomerImpl(ORB orb, int id) {
		this.id = id;
		this.orb = orb;
		accounts = new LinkedList<Account>();		
		try {

			String arg[] = {"-ORBInitialPort","1050","-ORBInitialHost","localhost"};
	        ORB orbt = ORB.init(arg, null);
			org.omg.CORBA.Object objRef;
			objRef = orbt.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			objRef = ncRef.resolve_str("interbank");
			interbank = BankTransactHelper.narrow(objRef);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void deposit(int id, int amount) {
		// TODO Auto-generated method stub
		this.accounts.get(this.accounts.indexOf(new Account(id))).deposit(amount);
	}

	@Override
	public void withdraw(int id, int amount) {
		// TODO Auto-generated method stub
		this.accounts.get(this.accounts.indexOf(new Account(id))).withdraw(amount);
	}

	@Override
	public int balance(int id) {
		// TODO Auto-generated method stub
		return this.accounts.get(this.accounts.indexOf(new Account(id))).balance();
	}

	@Override
	public int create(String name, int amount) {
		// TODO Auto-generated method stub
		Account a = new Account(name, amount);
		this.accounts.add(a);
		return a.getId();
	}

	@Override
	public void destroy(int a) {
		// TODO Auto-generated method stub
		this.accounts.remove(this.accounts.get(this.accounts.indexOf(new Account(a))));
	}

	@Override
	public void transfert(int from, int bank_id, int to, int amount) {
		// TODO Auto-generated method stub
		this.withdraw(from, amount);
		interbank.saveTransact(id, from, bank_id, to, amount);
	}
	
	protected void callback(){
		Transact ts[] = interbank.passTransacts(id);
		for(Transact t : ts){
			this.deposit(t.to, t.amount);
		}
	}

}
