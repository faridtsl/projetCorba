package InterBank;
import java.util.*;

import org.omg.CORBA.ORB;

public class BankTransactImpl extends BankTransactPOA {

	HashMap<Integer,List<Transact>> transacts;
	ORB orb;
	
	public BankTransactImpl(ORB orb){
		this.orb = orb;
		transacts = new HashMap();
	}

	@Override
	public void saveTransact(int bank_from, int from, int bank_id, int to, int amount) {
		// TODO Auto-generated method stub
		Transact t = new Transact(from, bank_from, to, bank_id, amount, 0);
		List<Transact> l = transacts.get(bank_id);
		if(l == null){
			l = new LinkedList();
		}
		l.add(t);
		transacts.put(bank_id,l);
	}

	@Override
	public Transact[] passTransacts(int bank_id) {
		List<Transact> l = transacts.get(bank_id);
		if(l == null) return new Transact[0];
		List<Transact> res = new LinkedList();
		List<Transact> ll = new LinkedList();
		for( Transact t : l ){
			if(t.getDone() == 0){
				res.add(t);
			}
			t.setDone(1);
			ll.add(t);
		}
		transacts.put(bank_id,ll);
		Transact[] arr = new Transact[res.size()];
		return l.toArray(arr);
	}


}
