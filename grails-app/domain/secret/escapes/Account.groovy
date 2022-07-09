package secret.escapes

class Account {

    String accountName
    Double balance
    String emailAddress

    static constraints = {
        accountName nullable: false
        balance nullable: false
        emailAddress nullable: false
    }
}
