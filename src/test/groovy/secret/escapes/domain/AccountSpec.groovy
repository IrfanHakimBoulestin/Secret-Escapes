package secret.escapes.domain

import grails.testing.gorm.DomainUnitTest
import secret.escapes.Account
import spock.lang.Specification

class AccountSpec extends Specification implements DomainUnitTest<Account> {

    Account account

    def setup() {
        account = new Account().tap { account ->
            account.accountName = 'Account One'
            account.balance = 200D
            account.emailAddress = 'email@gmail.com'
        }
    }

    def cleanup() {
    }

    void "Valid"() {
        expect:
        account.validate()
    }

    void "invalid_noAccountName"() {
        when:
        account.accountName = null

        then:
        !account.validate()
    }

    void "invalid_accountNameMaxExceeded"() {
        when:
        account.accountName = 'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'

        then:
        !account.validate()
    }

    void "invalid_noBalance"() {
        when:
        account.balance = null

        then:
        !account.validate()
    }

    void "invalid_noEmail"() {
        when:
        account.emailAddress = null

        then:
        !account.validate()
    }
}
