package InterBank;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class InterBankServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			if (args.length < 1) {
				System.out.println("Please use : java InterBankServer <interbank port>");
			} else {
				String interbankPort = args[0];
				
				// create and initialize the ORB
				String arg[] = {"-ORBInitialPort",interbankPort,"-ORBInitialHost","localhost"};
				ORB orb = ORB.init(arg, null);
	
				// get reference to rootpoa & activate the POAManager
				POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
				rootpoa.the_POAManager().activate();
	
				// create servant and register it with the ORB
				BankTransactImpl bankServant1 = new BankTransactImpl(orb);
	
				// get object reference from the servant
				org.omg.CORBA.Object ref1 = rootpoa.servant_to_reference(bankServant1);
				BankTransact href1 = BankTransactHelper.narrow(ref1);
				
				// get the root naming context
				// NameService invokes the name service
				org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
				// Use NamingContextExt which is part of the Interoperable
				// Naming Service (INS) specification.
				NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
	
				// bind the Object Reference in Naming
				String name1 = "interbank";
				NameComponent path1[] = ncRef.to_name(name1);
				ncRef.rebind(path1, href1);
	
				System.out.println("TransactServer ready and waiting ...");
				// wait for invocations from clients
				orb.run();
			}
		}

		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

		System.out.println("TansactServer Exiting ...");
	}

}
