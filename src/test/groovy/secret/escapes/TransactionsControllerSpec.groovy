package secret.escapes

import grails.testing.web.controllers.ControllerUnitTest
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
}
