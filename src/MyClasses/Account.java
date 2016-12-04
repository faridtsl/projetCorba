package MyClasses;

import java.util.*;

public class Account{

	private int id;
	private String name;
	private int solde;
	
	public Account(){}
	
	public Account(String name, int amount){
		this.name = name;
		this.solde = amount;
		this.id = new Random().nextInt(10000); //random
	}
	
	public Account(int id){
		this.id = id;
	}

	public int balance(){
		return solde;
	}
	
	public void deposit(int amount){
		solde += amount;
	}

	public void withdraw(int amount){
		solde -= amount;
	}
	
	@Override	
	public boolean equals(Object o){
		Account other = (Account) o;
		return other.id == this.id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSolde() {
		return solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}

}
