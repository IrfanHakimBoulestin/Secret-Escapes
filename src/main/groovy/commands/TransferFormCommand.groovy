package commands

import grails.validation.Validateable

class TransferFormCommand implements Validateable {

    Integer accountFrom
    Integer accountTo
    Double transactionAmount

    static constraints = {
        accountFrom nullable: false
        accountTo nullable: false
        transactionAmount nullable: false, blank: false
    }
}
