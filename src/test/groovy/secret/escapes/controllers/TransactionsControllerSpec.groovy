package secret.escapes.controllers

import grails.testing.web.controllers.ControllerUnitTest
import groovy.mock.interceptor.MockFor
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
        
        when:
        controller.viewTransactions()

        then:
        view == '/transactions/viewAllTransactions'
        model.transactions.size() == 2
        transactionServiceMock.verify(controller.transactionService)
    }
}
