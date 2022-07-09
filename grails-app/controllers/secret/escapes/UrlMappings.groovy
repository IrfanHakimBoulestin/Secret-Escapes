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
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
