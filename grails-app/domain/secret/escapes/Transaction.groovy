package secret.escapes

class Transaction {

    Double transactionAmount
    Account receiverAccount

    static constraints = {
        transactionAmount nullable: false, min: 0.01D
        receiverAccount nullable: false
    }

    static hasOne = [
            account: Account,
    ]
}
