package InterBank;

/**
* InterBank/BankTransactHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interbank.idl
* Thursday, December 8, 2016 4:12:42 PM CET
*/

public final class BankTransactHolder implements org.omg.CORBA.portable.Streamable
{
  public InterBank.BankTransact value = null;

  public BankTransactHolder ()
  {
  }

  public BankTransactHolder (InterBank.BankTransact initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = InterBank.BankTransactHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    InterBank.BankTransactHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return InterBank.BankTransactHelper.type ();
  }

}
