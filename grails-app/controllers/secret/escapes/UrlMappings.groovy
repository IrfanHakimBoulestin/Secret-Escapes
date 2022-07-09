package secret.escapes

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "/accounts"(controller: 'AccountsController', action: 'index')
        "/accounts/$action"(controller: 'AccountsController')
        "/transactions"(controller: 'TransactionsController', action: 'index')
        "/transactions/$action"(controller: 'TransactionsController')
        "/payments"(controller: 'PaymentsController', action: 'index')
        "/payments/$action"(controller: 'PaymentsController')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
