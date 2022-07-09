package secret.escapes

class TransactionsController {

    TransactionService transactionService

    def index() {
        redirect(uri: '/')
    }

    def viewTransactions(){
        Integer accountId = Integer.parseInt(params.id)
        List<Transaction> transactions = transactionService.retrieveTransactionsForAccount(accountId)
        render view: 'viewAllTransactions', model: [transactions: transactions]
    }
}
