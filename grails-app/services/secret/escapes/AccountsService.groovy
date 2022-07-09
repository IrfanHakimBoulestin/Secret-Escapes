package secret.escapes

import commands.AddNewAccountCommand
import grails.gorm.transactions.Transactional

@Transactional
class AccountsService {

    def addNewAccount(AddNewAccountCommand cmd) {
        new Account().tap { account ->
            account.accountName = cmd.accountName
            account.balance = 200D
            account.emailAddress = cmd.emailAddress
            save()
        }
    }
}
