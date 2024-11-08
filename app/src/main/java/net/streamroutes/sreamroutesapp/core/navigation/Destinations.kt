package net.streamroutes.sreamroutesapp.core.navigation

sealed class Destinations(val route: String) {

    // login navgraph
    data object SignIn : Destinations("signIn")
    data object ChangePassword : Destinations("changePassword")
    data object SignUp : Destinations("signUp")
    data object Select : Destinations("select")

    // transport navgraph

    // parking navgraph
}