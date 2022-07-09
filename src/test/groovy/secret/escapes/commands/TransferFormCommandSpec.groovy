package secret.escapes.commands

import commands.TransferFormCommand
import spock.lang.Specification

class TransferFormCommandSpec extends Specification {

    TransferFormCommand command

    void setup(){
        command = new TransferFormCommand().tap {
            it.accountFrom = 1
            it.accountTo = 1
            it.transactionAmount = 10D
        }
    }

    void "valid"(){
        expect:
        command.validate()
    }

    void "invalid, accountFromNull"(){
        when:
        command.accountFrom = null

        then:
        !command.validate()
    }

    void "invalid, accountToNull"(){
        when:
        command.accountTo = null

        then:
        !command.validate()
    }

    void "invalid, transactionAmountNull"(){
        when:
        command.transactionAmount = null

        then:
        !command.validate()
    }
}
