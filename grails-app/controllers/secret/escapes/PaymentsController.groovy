package secret.escapes

import commands.TransferFormCommand

class PaymentsController {

    AccountsService accountsService

    TransactionService transactionService

    def index() {
        redirect(uri: '/')
    }

    def transferForm(){
        Integer accountId = Integer.parseInt(params.id)
        render view: 'transferForm', model: [ account: accountsService.retrieveAccount(accountId), toAccounts: accountsService.retrieveAllAccountsApartFromSelectedAccount(accountId) ]
    }

    def processTransfer(){
        Integer accountFromId = Integer.parseInt(params.accountFrom.replaceAll(' ', '').split(":")[1])
        Integer accountToId = Integer.parseInt(params.accountTo.replaceAll(' ', '').split(":")[1])
        Double transactionAmount = params.transactionAmount ? Double.valueOf(params.transactionAmount) : null

        TransferFormCommand cmd = new TransferFormCommand().tap{
            it.accountFrom = accountFromId
            it.accountTo = accountToId
            it.transactionAmount = transactionAmount
        }

        if ( !cmd.validate() ){
            flash.error = 'Please ensure that the transaction amount is provided!'
            return redirect(uri: "/payments/transferForm?id=${accountFromId.toString()}")
        }

        boolean enoughMoneyToCoverTransfer = accountsService.checkFundsAvailable(cmd.accountFrom, cmd.transactionAmount)
        if ( !enoughMoneyToCoverTransfer ){
            flash.error = 'You do not have enough money in this account to complete this transfer, please check your balance!'
        } else {
            accountsService.processTransfer(cmd.accountFrom, cmd.accountTo, cmd.transactionAmount)
            transactionService.addTransaction(cmd.accountFrom, cmd.accountTo, cmd.transactionAmount)
            flash.success = 'Transfer has been successfully completed!'
        }
        redirect(uri: "/accounts/viewAllAccounts")
    }
}
