package secret.escapes

class PaymentsController {

    AccountsService accountsService

    def index() {
        redirect(url: '/')
    }

    def transferForm(){
        Integer accountId = Integer.parseInt(params.id)
        render view: 'transferForm', model: [ account: accountsService.retrieveAccount(accountId), toAccounts: accountsService.retrieveAllAccountsApartFromSelectedAccount(accountId) ]
    }
}
