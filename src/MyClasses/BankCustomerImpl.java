package MyClasses;

import java.util.*;
import org.omg.CORBA.ORB;
import ServeurBancaire.BankCostumerPOA;

public class BankCustomerImpl extends BankCostumerPOA {

	private LinkedList<Account> accounts;
	private ORB orb;
	private int id;

	public BankCustomerImpl(ORB orb, int id) {
		this.id = id;
		this.orb = orb;
		accounts = new LinkedList<Account>();
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
		Account a = new Account(name,amount);
		this.accounts.add(a);
		return a.getId();
	}

	@Override
	public void destroy(int a) {
		// TODO Auto-generated method stub
		this.accounts.remove(this.accounts.get(this.accounts.indexOf(new Account(a))));
	}

	@Override
	public void transfert(int from, int bank_id, String name, int amount) {
		// TODO Auto-generated method stub
		
	}

}
