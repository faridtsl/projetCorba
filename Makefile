all: klass

idl: bank.idl
	idlj -fall bank.idl

klass:
	javac src/*/*.java

clean:
	$(RM) -rf *.class 

.phony: runNameServInterbank, runNameServBanks, runInterbank, runBanks, runClients

runNameServInterbank:
	tnameserv -ORBInitialPort 2000

runNameServBanks:
	tnameserv -ORBInitialPort 3000

runInterbank:
	cd src && java InterBank.InterBankServer 2000

runBanks:
	cd src && java ServeurBancaire.BankServer 3000 2000

runClients:
	cd src && java Client.ClientServer 3000
