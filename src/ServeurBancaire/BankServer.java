package ServeurBancaire;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Policy;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.LifespanPolicyValue;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class BankServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			BankCustomerImpl bankServant1 = new BankCustomerImpl(orb, 1);
			BankCustomerImpl bankServant2 = new BankCustomerImpl(orb, 2);
			BankCustomerImpl bankServant3 = new BankCustomerImpl(orb, 3);

			callbacks(bankServant1);
			callbacks(bankServant2);
			callbacks(bankServant3);

			// get object reference from the servant
			org.omg.CORBA.Object ref1 = rootpoa.servant_to_reference(bankServant1);
			org.omg.CORBA.Object ref2 = rootpoa.servant_to_reference(bankServant2);
			org.omg.CORBA.Object ref3 = rootpoa.servant_to_reference(bankServant3);
			BankCustomer href1 = BankCustomerHelper.narrow(ref1);
			BankCustomer href2 = BankCustomerHelper.narrow(ref2);
			BankCustomer href3 = BankCustomerHelper.narrow(ref3);

			// get the root naming context
			// NameService invokes the name service
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			// Use NamingContextExt which is part of the Interoperable
			// Naming Service (INS) specification.
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// bind the Object Reference in Naming
			String name1 = "bank1";
			String name2 = "bank2";
			String name3 = "bank3";
			NameComponent path1[] = ncRef.to_name(name1);
			ncRef.rebind(path1, href1);

			NameComponent path2[] = ncRef.to_name(name2);
			ncRef.rebind(path2, href2);

			NameComponent path3[] = ncRef.to_name(name3);
			ncRef.rebind(path3, href3);

			System.out.println("BankServer ready and waiting ...");
			// wait for invocations from clients
			orb.run();
		}

		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

		System.out.println("BankServer Exiting ...");

	}

	private static void callbacks(BankCustomerImpl bankServant) {
		// TODO Auto-generated method stub
		Runnable r = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					bankServant.callback();
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
	}
}
