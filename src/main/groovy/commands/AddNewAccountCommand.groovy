package commands

import grails.validation.Validateable
import org.apache.commons.validator.GenericValidator

class AddNewAccountCommand implements Validateable {

    String accountName
    String emailAddress

    static constraints = {
        accountName nullable: false, blank: false, maxSize: 30, matches: "[a-zA-Z\\d ]+"
        emailAddress nullable: false, blank: false, validator: { String emailAddress ->
            return GenericValidator.isEmail(emailAddress)
        }
    }
}
