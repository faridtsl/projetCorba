package ServeurBancaire;


/**
* ServeurBancaire/BankCustomerOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bank.idl
* Thursday, December 8, 2016 4:16:16 PM CET
*/

public interface BankCustomerOperations 
{
  void deposit (int id, int amount);
  void withdraw (int id, int amount);
  int balance (int id);
  int create (String name, int amount);
  void destroy (int a);
  void transfert (int from, int bank_id, int to, int amount);
} // interface BankCustomerOperations
