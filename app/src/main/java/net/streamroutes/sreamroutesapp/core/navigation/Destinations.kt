package net.streamroutes.sreamroutesapp.core.navigation

sealed class Destinations(val route: String) {

    // login navgraph
    data object SignIn : Destinations("signIn")
    data object ChangePassword : Destinations("changePassword")
    data object SignUp : Destinations("signUp")
    data object Select : Destinations("select")

    // transport navgraph
    data object Profile : Destinations("profile")
    data object HomeEditProfile : Destinations("homeEditProfile")
    data object HomeTransport : Destinations("homeTransport")
    data object Premium : Destinations("premium")
    data object Maps : Destinations("maps")
    data object Tourism : Destinations("tourism")
    data object Forum : Destinations("forum")
    data object Settings : Destinations("settings")

    // settings navgraph
    data object HomeSettings : Destinations("homeSettings")
    data object NotificationsSettings : Destinations("notificationsSettings")
    data object MapsSettings : Destinations("mapsSettings")
    data object PrivacitySettings : Destinations("privacitySettings")
    data object StorageSettings : Destinations("storageSettings")
    data object ApparenceSettings : Destinations("apparenceSettings")

    // profile navgraph
    data object EditProfile : Destinations("editProfile")
    data object EditAccount : Destinations("editAccount")
    data object EditPersonalInformation : Destinations("editPersonalInformation")
    data object Posts : Destinations("posts")
    data object Routes : Destinations("routes")
    data object History : Destinations("history")

    // parking navgraph
    data object HomeParking : Destinations("homeParking")
    data object Parks : Destinations("parking")
}