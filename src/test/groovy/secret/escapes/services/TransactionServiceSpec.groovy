package secret.escapes.services

import grails.test.hibernate.HibernateSpec
import grails.testing.services.ServiceUnitTest
import secret.escapes.Account
import secret.escapes.Transaction
import secret.escapes.TransactionService

class TransactionServiceSpec extends HibernateSpec implements ServiceUnitTest<TransactionService>{

    def setup() {
        Account account1 = new Account().tap {
            it.accountName = 'AN1'
            it.emailAddress = 'email@email.com'
            it.balance = 200D
            save()
        }
        Account account2 = new Account().tap {
            it.accountName = 'AN2'
            it.emailAddress = 'email@email.com'
            it.balance = 200D
            save()
        }
        new Transaction().tap {
            it.account = account1
            it.receiverAccount = account2
            it.transactionAmount = 20D
            save()
        }
        new Transaction().tap {
            it.account = account1
            it.receiverAccount = account2
            it.transactionAmount = 20D
            save()
        }
    }

    def cleanup() {
    }

    void "retrieveTransactionsForAccount, returns transactions for account"() {
        expect:
        service.retrieveTransactionsForAccount(1).size() == 2
    }

    void "retrieveTransactionsForAccount, no transactions for account, nothing returned"() {
        expect:
        service.retrieveTransactionsForAccount(2).isEmpty()
    }

}
