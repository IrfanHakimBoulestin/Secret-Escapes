package secret.escapes.commands

import commands.AddNewAccountCommand
import spock.lang.Specification

class AddNewAccountCommandSpec extends Specification {

    AddNewAccountCommand command

    void setup(){
        command = new AddNewAccountCommand().tap {
            it.emailAddress = 'email.address@gmaill.com'
            it.accountName = 'Account One'
        }
    }

    void "valid"(){
        expect:
        command.validate()
    }

    void "invalid, accountNameNull"(){
        when:
        command.accountName = null

        then:
        !command.validate()
    }

    void "invalid, accountNameBlank"(){
        when:
        command.accountName = ''

        then:
        !command.validate()
    }

    void "invalid, emailAddressNull"(){
        when:
        command.emailAddress = null

        then:
        !command.validate()
    }

    void "invalid, emailAddressBlank"(){
        when:
        command.emailAddress = ''

        then:
        !command.validate()
    }

    void "invalid, emailAddressDoesNotMatch"(){
        when:
        command.emailAddress = '--------'

        then:
        !command.validate()
    }

    void "invalid, accountNameExceeds30Chars"(){
        when:
        command.accountName = 'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'

        then:
        !command.validate()
    }

    void "invalid, accountNameDoesNotMatch"(){
        when:
        command.accountName = 'AA++'

        then:
        !command.validate()
    }
}
