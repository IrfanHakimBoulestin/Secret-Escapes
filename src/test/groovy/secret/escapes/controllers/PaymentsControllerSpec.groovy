package secret.escapes.controllers

import grails.testing.web.controllers.ControllerUnitTest
import groovy.mock.interceptor.MockFor
import secret.escapes.Account
import secret.escapes.AccountsService
import secret.escapes.PaymentsController
import spock.lang.Specification

class PaymentsControllerSpec extends Specification implements ControllerUnitTest<PaymentsController> {

    def setup() {
    }

    def cleanup() {
    }

    void "index, redirected to accounts dashboard"() {
        when:
        controller.index()

        then:
        response.redirectedUrl == '/'
    }

    void "transferForm calls the accountService to retrieveAccount and retrieveAllAccountsApartFromSelectedAccount rendering the transferForm view"() {
        given:
        params.id = '1'
        def accountServiceMock = new MockFor(AccountsService)
        accountServiceMock.demand.retrieveAccount(1){ Integer accountId -> return new Account() }
        accountServiceMock.demand.retrieveAllAccountsApartFromSelectedAccount(1){ Integer accountId -> return [new Account()] }
        controller.accountsService = accountServiceMock.proxyInstance()

        when:
        controller.transferForm()

        then:
        view == '/payments/transferForm'
        model.account
        model.toAccounts.size() == 1
        accountServiceMock.verify(controller.accountsService)
    }
}
