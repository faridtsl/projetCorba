all: klass

klass:
	mkdir -p bin && javac src/*/*.java -d ./bin

clean:
	$(RM) -rf *.class 

.phony: runNameServInterbank, runNameServBanks, runInterbank, runBanks, runClients

runNameServInterbank:
	tnameserv -ORBInitialPort 2000

runNameServBanks:
	tnameserv -ORBInitialPort 3000

runInterbank:
	cd bin && java InterBank.InterBankServer 2000

runBanks:
	cd bin && java ServeurBancaire.BankServer 3000 2000

runClients:
	cd bin && java Client.ClientServer 3000
	
runLogInterbank:
	cd bin && java InterBank.InterBankLogClient 2000
