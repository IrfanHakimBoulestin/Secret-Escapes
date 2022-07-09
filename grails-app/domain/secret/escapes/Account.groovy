package secret.escapes

class Account {

    String accountName
    Double balance
    String emailAddress

    static constraints = {
        accountName nullable: false, maxSize: 30
        balance nullable: false
        emailAddress nullable: false
    }
}
