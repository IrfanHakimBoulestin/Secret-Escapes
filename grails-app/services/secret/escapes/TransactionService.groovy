package secret.escapes

import grails.gorm.transactions.Transactional

@Transactional
class TransactionService {

    List<Transaction> retrieveTransactionsForAccount(Integer id) {
        Account account = Account.get(id)
        return Transaction.findAllByAccountOrReceiverAccount(account, account)
    }

    void addTransaction(Integer accountFrom, Integer accountTo, Double transactionAmount) {
        new Transaction().tap { transaction ->
            transaction.account = Account.get(accountFrom)
            transaction.receiverAccount = Account.get(accountTo)
            transaction.transactionAmount = transactionAmount
            save()
        }
    }
}
