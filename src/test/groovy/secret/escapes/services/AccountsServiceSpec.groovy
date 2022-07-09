package secret.escapes.services

import commands.AddNewAccountCommand
import grails.test.hibernate.HibernateSpec
import grails.testing.services.ServiceUnitTest
import secret.escapes.Account
import secret.escapes.AccountsService

class AccountsServiceSpec extends HibernateSpec  implements ServiceUnitTest<AccountsService>{

    def setup() {
        new Account().tap {
            it.accountName = 'AN1'
            it.emailAddress = 'email@email.com'
            it.balance = 200D
            save()
        }
        new Account().tap {
            it.accountName = 'AN2'
            it.emailAddress = 'email@email.com'
            it.balance = 200D
            save()
        }
    }

    def cleanup() {
    }

    void "addNewAccount"() {
        when:
        AddNewAccountCommand cmd = new AddNewAccountCommand().tap {
            it.emailAddress = 'email@email.com'
            it.accountName = 'accountName'
        }
        assert Account.all.size() == 2
        service.addNewAccount(cmd)

        then:
        Account.all.size() == 3
        Account.all.get(2).accountName == 'accountName'
        Account.all.get(2).balance == 200D
        Account.all.get(2).emailAddress == 'email@email.com'
    }

    void "retrieveAllAccounts"(){
        when:
        List<Account> expectedAccounts = [Account.findByAccountName('AN1'), Account.findByAccountName('AN2')]
        List<Account> actualAccountsRetrieved = service.retrieveAllAccounts()

        then:
        expectedAccounts == actualAccountsRetrieved
    }

    void "retrieveAllAccountsApartFromSelectedAccount"(){
        when:
        List<Account> expectedAccounts = [Account.findByAccountName('AN2')]
        List<Account> actualAccountsRetrieved = service.retrieveAllAccountsApartFromSelectedAccount(Account.findByAccountName('AN1').id as Integer)

        then:
        expectedAccounts == actualAccountsRetrieved
    }

    void "retrieveAccount"(){
        when:
        Account accountRetrieved = service.retrieveAccount(Account.findByAccountName('AN1').id as Integer)
        Account secondAccountRetrieved = service.retrieveAccount(Account.findByAccountName('AN2').id as Integer)

        then:
        accountRetrieved.getAccountName() == 'AN1'
        secondAccountRetrieved.getAccountName() == 'AN2'
    }

    void "processTransfer"(){
        when:
        Account accountFrom = Account.findByAccountName('AN1')
        Account accountTo = Account.findByAccountName('AN2')
        service.processTransfer(accountFrom.id as Integer, accountTo.id as Integer, 25D)

        then:
        accountFrom.balance == 175D
        accountTo.balance == 225D
    }

    void "checkFundsAvailable, balance less than transaction amount, return true"(){
        when:
        Account account = Account.findByAccountName('AN2')
        boolean hasFunds = service.checkFundsAvailable(account.id as Integer, 150D)

        then:
        hasFunds
    }

    void "checkFundsAvailable, balance equal than transaction amount, return true"(){
        when:
        Account account = Account.findByAccountName('AN2')
        boolean hasFunds = service.checkFundsAvailable(account.id as Integer, 200D)

        then:
        hasFunds
    }

    void "checkFundsAvailable, balance greater than transaction amount, return true"(){
        when:
        Account account = Account.findByAccountName('AN2')
        boolean hasFunds = service.checkFundsAvailable(account.id as Integer, 201D)

        then:
        !hasFunds
    }
}
