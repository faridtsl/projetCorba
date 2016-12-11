package InterBank;
import java.util.*;

import org.omg.CORBA.ORB;

public class BankTransactImpl extends BankTransactPOA {

	HashMap<Integer,List<Transact>> transacts;
	ORB orb;
	
	public BankTransactImpl(ORB orb){
		this.orb = orb;
		transacts = new HashMap<Integer, List<Transact>>();
	}

	@Override
	public void saveTransact(int bank_from, int from, int bank_id, int to, int amount) {
		// TODO Auto-generated method stub
		Transact t = new Transact(from, bank_from, to, bank_id, amount, 0);
		List<Transact> lto = transacts.get(bank_id);

		List<Transact> lfrom = transacts.get(bank_from);
		if(lto == null){
			lto = new LinkedList<Transact>();
			transacts.put(bank_id,lto);
		}
		if(lfrom == null){
			lfrom = new LinkedList<Transact>();
			transacts.put(bank_from,lfrom);
		}
		lto.add(t);
		lfrom.add(t);
	}

	@Override
	public Transact[] passTransacts(int bank_id) {
		List<Transact> l = transacts.get(bank_id);
		if(l == null) 
			return new Transact[0];
		List<Transact> res = new LinkedList<Transact>();
		for( Transact t : l ){
			if(t.getDone() == 0 && bank_id == t.getBank_to()){
				res.add(t);
				t.setDone(1);
			}
			if(t.getDone() == 1 && bank_id == t.getBank_from()){
				res.add(t);
				t.setDone(2);
			}
		}
		Transact[] arr = new Transact[res.size()];
		return res.toArray(arr);
	}


}
