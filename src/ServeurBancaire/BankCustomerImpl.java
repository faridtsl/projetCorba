package ServeurBancaire;

import java.util.*;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import InterBank.BankTransact;
import InterBank.BankTransactHelper;
import InterBank.Transact;

public class BankCustomerImpl extends BankCustomerPOA {

	private HashMap<Integer, Account> accounts;
	private ORB orb;
	private int id;
	private BankTransact interbank;

	public BankCustomerImpl(String port, int id) {
		this.id = id;
		
		String args[] = {"-ORBInitialPort",port,"-ORBInitialHost","localhost"};
		this.orb = ORB.init(args, null);
		accounts = new HashMap<Integer, Account>();		
		try {

			org.omg.CORBA.Object objRef;
			objRef = orb.resolve_initial_references("NameService");
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
		Account a = this.accounts.get(id);
		if (a != null)
			a.deposit(amount);
	}

	@Override
	public void withdraw(int id, int amount) {
		// TODO Auto-generated method stub
		Account a = this.accounts.get(id);
		if (a != null)
			a.withdraw(amount);
	}

	@Override
	public int balance(int id) {
		// TODO Auto-generated method stub
		Account a = this.accounts.get(id);
		if (a != null)
			return a.balance();
		return 0;
	}

	@Override
	public int create(String name, int amount) {
		// TODO Auto-generated method stub
		Account a = new Account(name, amount);
		this.accounts.put(a.getId(), a);
		return a.getId();
	}

	@Override
	public void destroy(int a) {
		// TODO Auto-generated method stub
		Account b = this.accounts.get(a);
		if (b != null)
			this.accounts.remove(b.getId());
	}

	@Override
	public void transfert(int from, int bank_id, int to, int amount) {
		// TODO Auto-generated method stub
		if(bank_id == this.id){
			this.deposit(to,amount);
			this.withdraw(from,amount);
			return ;
		}
		Account a = this.accounts.get(from);
		if (a != null) {
			interbank.saveTransact(id, from, bank_id, to, amount);
		}
	}
	
	protected void callback(){
		Transact ts[] = interbank.passTransacts(id);
		for(Transact t : ts){
			if (t.bank_from == id) {
				this.withdraw(t.from, t.amount);
			}
			else if (t.bank_to == id) {
				this.deposit(t.to, t.amount);
			}
		}
		
		String log = "Bank n°" + id + "\n";
		for(Account a : accounts.values()) {
			log += a + "\n";
		}
		System.out.println(log);
	}

}
