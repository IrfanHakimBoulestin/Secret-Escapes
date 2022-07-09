package secret.escapes.domain

import grails.testing.gorm.DomainUnitTest
import secret.escapes.Account
import secret.escapes.Transaction
import spock.lang.Specification

class TransactionSpec extends Specification implements DomainUnitTest<Transaction> {

    Transaction transaction

    def setup() {
        transaction = new Transaction().tap {
            it.transactionAmount = 20D
            it.account = new Account()
            it.receiverAccount = new Account()
        }
    }

    def cleanup() {
    }

    void "valid"() {
        expect:
        transaction.validate()
    }

    void "invalid_transactionAmountNull"() {
        when:
        transaction.transactionAmount = null

        then:
        !transaction.validate()
    }

    void "invalid_transactionAmountLessThanMin"() {
        when:
        transaction.transactionAmount = 0D

        then:
        !transaction.validate()
    }

    void "invalid_accountNull"() {
        when:
        transaction.account = null

        then:
        !transaction.validate()
    }

    void "invalid_receiverAccountNull"() {
        when:
        transaction.account = null

        then:
        !transaction.validate()
    }
}
