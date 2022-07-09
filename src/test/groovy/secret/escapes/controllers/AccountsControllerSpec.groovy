package secret.escapes.controllers

import commands.AddNewAccountCommand
import grails.testing.web.controllers.ControllerUnitTest
import groovy.mock.interceptor.MockFor
import secret.escapes.AccountsController
import secret.escapes.AccountsService
import spock.lang.Specification

class AccountsControllerSpec extends Specification implements ControllerUnitTest<AccountsController> {

    def setup() {
    }

    def cleanup() {
    }

    void "when index is called, index view is rendered"() {
        when:
        controller.index()

        then:
        view == '/index'
    }

    void "when addNewAccount is called, addNewAccount view is rendered"() {
        when:
        controller.addNewAccount()

        then:
        view == '/accounts/addNewAccount'
    }

    void "processNewAccount, invalidParams_error rendered on index page"(){
        when:
        params.emailAddress = 'email@random.com'
        def accountServiceMock = new MockFor(AccountsService)
        accountServiceMock.demand.addNewAccount(0){ AddNewAccountCommand cmd -> }
        controller.accountsService = accountServiceMock.proxyInstance()
        controller.processNewAccount()

        then:
        flash.error == 'Invalid data has been provided, please try again!'
        view == '/index'
        accountServiceMock.verify(controller.accountsService)
    }

    void "processNewAccount, validParams_error rendered on index page"(){
        given:
        params.emailAddress = 'email@random.com'
        params.accountName = 'AN1'
        def accountServiceMock = new MockFor(AccountsService)
        accountServiceMock.demand.addNewAccount(1){ AddNewAccountCommand cmd -> }
        controller.accountsService = accountServiceMock.proxyInstance()

        when:
        controller.processNewAccount()

        then:
        flash.success == 'New Account Added Successfully!'
        view == '/index'
        accountServiceMock.verify(controller.accountsService)
    }
}
