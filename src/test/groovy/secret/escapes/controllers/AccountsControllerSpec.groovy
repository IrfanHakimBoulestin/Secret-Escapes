package secret.escapes.controllers

import grails.testing.web.controllers.ControllerUnitTest
import secret.escapes.AccountsController
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
}
