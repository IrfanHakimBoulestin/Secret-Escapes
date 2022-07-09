package secret.escapes.services

import commands.AddNewAccountCommand
import grails.test.hibernate.HibernateSpec
import grails.testing.services.ServiceUnitTest
import secret.escapes.Account
import secret.escapes.AccountsService

class AccountsServiceSpec extends HibernateSpec  implements ServiceUnitTest<AccountsService>{

    def setup() {
    }

    def cleanup() {
    }

    void "addNewAccount"() {
        when:
        AddNewAccountCommand cmd = new AddNewAccountCommand().tap {
            it.emailAddress = 'email@email.com'
            it.accountName = 'accountName'
        }
        assert Account.all.isEmpty()
        service.addNewAccount(cmd)

        then:
        Account.all.size() == 1
        Account.first().accountName == 'accountName'
        Account.first().balance == 200D
        Account.first().emailAddress == 'email@email.com'
    }

    void "retrieveAllAccounts"(){
        when:
        Account accountOne = new Account().tap {
            it.emailAddress = 'email@email.com'
            it.accountName = 'accountName'
            it.balance = 200D
            save()
        }
        List<Account> expectedAccounts = [accountOne]
        List<Account> actualAccountsRetrieved = service.retrieveAllAccounts()

        then:
        expectedAccounts == actualAccountsRetrieved
    }
}
