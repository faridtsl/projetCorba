package InterBank;


/**
* InterBank/BankTransactOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interbank.idl
* Thursday, December 8, 2016 4:12:42 PM CET
*/

public interface BankTransactOperations 
{
  void saveTransact (int bank_from, int from, int bank_id, int to, int amount);
  InterBank.Transact[] passTransacts (int bank_id);
} // interface BankTransactOperations
