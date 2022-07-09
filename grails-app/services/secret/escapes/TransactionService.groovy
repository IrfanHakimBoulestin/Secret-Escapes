package secret.escapes

import grails.gorm.transactions.Transactional

@Transactional
class TransactionService {

    List<Transaction> retrieveTransactionsForAccount(Integer id) {
        return Transaction.findAllByAccount(Account.get(id))
    }
}
