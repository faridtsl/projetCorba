package InterBank;

/**
 * InterBank/Transact.java . Generated by the IDL-to-Java compiler (portable),
 * version "3.2" from interbank.idl Thursday, December 8, 2016 4:12:42 PM CET
 */

public final class Transact implements org.omg.CORBA.portable.IDLEntity {

	public int from = (int) 0;
	public int bank_from = (int) 0;
	public int to = (int) 0;
	public int bank_to = (int) 0;
	public int amount = (int) 0;
	public int done = (int) 0;

	public Transact() {
	} // ctor

	public Transact(int _from, int _bank_from, int _to, int _bank_to, int _amount, int _done) {
		from = _from;
		bank_from = _bank_from;
		to = _to;
		bank_to = _bank_to;
		amount = _amount;
		done = _done;
	} // ctor

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getBank_from() {
		return bank_from;
	}

	public void setBank_from(int bank_from) {
		this.bank_from = bank_from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public int getBank_to() {
		return bank_to;
	}

	public void setBank_to(int bank_to) {
		this.bank_to = bank_to;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getDone() {
		return done;
	}

	public void setDone(int done) {
		this.done = done;
	}
	
	public String toString() {
		String str = "From " + from + "-" + bank_from + " to " + to + "-" + bank_to + "\t | Amount : " + amount + "\t | Done : " + done;;
		return str;
	}
} // class Transact
