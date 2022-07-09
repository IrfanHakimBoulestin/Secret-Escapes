package secret.escapes.controllers

import grails.testing.web.controllers.ControllerUnitTest
import groovy.mock.interceptor.MockFor
import secret.escapes.Account
import secret.escapes.AccountsService
import secret.escapes.Transaction
import secret.escapes.TransactionService
import secret.escapes.TransactionsController
import spock.lang.Specification

class TransactionsControllerSpec extends Specification implements ControllerUnitTest<TransactionsController> {

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

    void "viewTransactions, service method is called"() {
        given:
        params.id = '1'
        def transactionServiceMock = new MockFor(TransactionService)
        transactionServiceMock.demand.retrieveTransactionsForAccount(1){ return [new Transaction(), new Transaction()] }
        controller.transactionService = transactionServiceMock.proxyInstance()

        def accountsServiceMock = new MockFor(AccountsService)
        accountsServiceMock.demand.retrieveAccount(1){ Integer accountId -> return new Account() }
        controller.accountsService = accountsServiceMock.proxyInstance()
        
        when:
        controller.viewTransactions()

        then:
        view == '/transactions/viewAllTransactions'
        model.transactions.size() == 2
        model.account
        transactionServiceMock.verify(controller.transactionService)
        accountsServiceMock.verify(controller.accountsService)
    }
}
