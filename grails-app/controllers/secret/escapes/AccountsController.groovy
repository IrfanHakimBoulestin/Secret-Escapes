package secret.escapes

import commands.AddNewAccountCommand

class AccountsController {

    AccountsService accountsService

    def index() {
        render view: "/index"
    }

    def addNewAccount(){
        render view: "addNewAccount"
    }

    def processNewAccount( ){
        AddNewAccountCommand cmd = new AddNewAccountCommand().tap {
            it.emailAddress = params.emailAddress
            it.accountName = params.accountName
        }
        if ( cmd.validate() ) {
            accountsService.addNewAccount(cmd)
            flash.success = 'New Account Added Successfully!'
        } else {
            flash.error = 'Invalid data has been provided, please try again!'
        }
        render view: "/index"
    }
}
