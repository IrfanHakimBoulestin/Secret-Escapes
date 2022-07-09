package secret.escapes

class AccountsController {

    def index() {
        render view: "/index"
    }

    def addNewAccount(){
        render view: "addNewAccount"
    }
}
