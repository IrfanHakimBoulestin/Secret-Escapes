package secret.escapes.services

import grails.plugins.mail.MailService
import grails.test.hibernate.HibernateSpec
import grails.testing.services.ServiceUnitTest
import groovy.mock.interceptor.MockFor
import secret.escapes.Account
import secret.escapes.EmailService

class EmailServiceSpec extends HibernateSpec implements ServiceUnitTest<EmailService>{

    def setup() {
        new Account().tap {
            it.accountName = 'AN1'
            it.emailAddress = 'email@email.com'
            it.balance = 200D
            save()
        }
        new Account().tap {
            it.accountName = 'AN2'
            it.emailAddress = 'email@email.com'
            it.balance = 200D
            save()
        }
    }

    def cleanup() {
    }

    void "sendTransferCompleteEmail"() {
        given:
        Integer account1Id= (Integer) Account.findByAccountName('AN1').id
        Integer account2Id= (Integer) Account.findByAccountName('AN1').id

        def mailServiceMock = new MockFor(MailService)
        mailServiceMock.demand.sendMail(1){  Closure c-> }
        service.mailService = mailServiceMock.proxyInstance()

        when:
        service.sendTransferCompleteEmail(account1Id, account2Id)

        then:
        mailServiceMock.verify(service.mailService)
    }
}
