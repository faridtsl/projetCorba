package ServeurBancaire;

/**
* ServeurBancaire/BankCustomerHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bank.idl
* Thursday, December 8, 2016 4:16:16 PM CET
*/

public final class BankCustomerHolder implements org.omg.CORBA.portable.Streamable
{
  public ServeurBancaire.BankCustomer value = null;

  public BankCustomerHolder ()
  {
  }

  public BankCustomerHolder (ServeurBancaire.BankCustomer initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ServeurBancaire.BankCustomerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ServeurBancaire.BankCustomerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ServeurBancaire.BankCustomerHelper.type ();
  }

}
