package secret.escapes.controllers

import grails.testing.web.controllers.ControllerUnitTest
import groovy.mock.interceptor.MockFor
import secret.escapes.Account
import secret.escapes.AccountsService
import secret.escapes.EmailService
import secret.escapes.PaymentsController
import secret.escapes.TransactionService
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

    void "processTransfer_requestCommandInvalid_redirectToTransferForm"(){
        when:
        params.accountFrom = 'secret.escapes.Account : 1'
        params.accountTo = 'secret.escapes.Account : 2'
        controller.processTransfer()

        then:
        response.redirectedUrl == '/payments/transferForm?id=1'
        flash.error == 'Please ensure that the transaction amount is provided!'
    }

    void "processTransfer_enoughMoneyToCoverTransferFalse_redirectToViewAllAccounts"(){
        given:
        params.accountFrom = 'secret.escapes.Account : 1'
        params.accountTo = 'secret.escapes.Account : 2'
        params.transactionAmount = '20.00'

        def accountServiceMock = new MockFor(AccountsService)
        accountServiceMock.demand.checkFundsAvailable(1){ Integer accountFrom, Double transactionAmount -> return false }
        controller.accountsService = accountServiceMock.proxyInstance()

        when:
        controller.processTransfer()

        then:
        response.redirectedUrl == '/accounts/viewAllAccounts'
        flash.error == 'You do not have enough money in this account to complete this transfer, please check your balance!'
        accountServiceMock.verify(controller.accountsService)
    }

    void "processTransfer_enoughMoneyToCoverTransfer_transferProcessedAndTransactionRecorded"(){
        given:
        params.accountFrom = 'secret.escapes.Account : 1'
        params.accountTo = 'secret.escapes.Account : 2'
        params.transactionAmount = '20.00'

        def accountServiceMock = new MockFor(AccountsService)
        accountServiceMock.demand.checkFundsAvailable(1){ Integer accountFrom, Double transactionAmount -> return true }
        accountServiceMock.demand.processTransfer(1){ Integer accountFrom, Integer accountTo, Double transactionAmount ->  }
        controller.accountsService = accountServiceMock.proxyInstance()

        def transactionServiceMock = new MockFor(TransactionService)
        transactionServiceMock.demand.addTransaction(1){ Integer accountFrom, Integer accountTo, Double transactionAmount ->  }
        controller.transactionService = transactionServiceMock.proxyInstance()

        def emailServiceMock = new MockFor(EmailService)
        emailServiceMock.demand.sendTransferCompleteEmail(1){ Integer accountFrom, Integer accountTo ->  }
        controller.emailService = emailServiceMock.proxyInstance()


        when:
        controller.processTransfer()

        then:
        response.redirectedUrl == '/accounts/viewAllAccounts'
        flash.success == 'Transfer has been successfully completed!'
        accountServiceMock.verify(controller.accountsService)
        transactionServiceMock.verify(controller.transactionService)
        emailServiceMock.verify(controller.emailService)
    }
}
