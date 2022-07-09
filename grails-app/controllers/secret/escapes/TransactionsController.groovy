package secret.escapes

class TransactionsController {

    TransactionService transactionService

    AccountsService accountsService

    def index() {
        redirect(uri: '/')
    }

    def viewTransactions(){
        Integer accountId = Integer.parseInt(params.id)
        Account account = accountsService.retrieveAccount(accountId)
        List<Transaction> transactions = transactionService.retrieveTransactionsForAccount(accountId)
        render view: 'viewAllTransactions', model: [transactions: transactions, account: account]
    }
}
