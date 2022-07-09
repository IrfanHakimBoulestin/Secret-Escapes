package secret.escapes

import commands.AddNewAccountCommand
import grails.gorm.transactions.Transactional

@Transactional
class AccountsService {

    void addNewAccount(AddNewAccountCommand cmd) {
        new Account().tap { account ->
            account.accountName = cmd.accountName
            account.balance = 200D
            account.emailAddress = cmd.emailAddress
            save()
        }
    }

    List<Account> retrieveAllAccounts(){
        return Account.all
    }

    List<Account> retrieveAllAccountsApartFromSelectedAccount(Integer id){
        return Account.all - Account.get(id)
    }

    Account retrieveAccount(Integer id){
        return Account.get(id)
    }

    void processTransfer(Integer accountFrom, Integer accountTo, Double transactionAmount){
        Account.get(accountFrom).tap {
            it.balance = it.balance - transactionAmount
            save()
        }
        Account.get(accountTo).tap {
            it.balance = it.balance + transactionAmount
            save()
        }
    }

    boolean checkFundsAvailable(Integer accountFrom, Double transactionAmount){
        return Account.get(accountFrom).balance - transactionAmount >= 0
    }
}
