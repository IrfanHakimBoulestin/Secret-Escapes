package secret.escapes

import grails.gorm.transactions.Transactional

@Transactional
class TransactionService {

    List<Transaction> retrieveTransactionsForAccount(Integer id) {
        return Transaction.findAllByAccount(Account.get(id))
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
