package net.streamroutes.sreamroutesapp.core.navigation

sealed class Destinations(val route: String) {

    // general navgraph
    data object Choose : Destinations("choose")
    data object TransportMain : Destinations("transportMain")
    data object ParkingMain : Destinations("parkingMain")

    // login navgraph
    data object SignIn : Destinations("signIn")
    data object ChangePassword : Destinations("changePassword")
    data object SignUp : Destinations("signUp")

    // transport navgraph
    data object Profile : Destinations("profile")
    data object HomeEditProfile : Destinations("homeEditProfile")
    data object HomeTransport : Destinations("homeTransport")
    data object Premium : Destinations("premium")
    data object Maps : Destinations("maps")
    data object HomeTourism : Destinations("homeTourism")
    data object Forum : Destinations("forum")
    data object Settings : Destinations("settings")

    // tourism navgraph
    data object Tourism : Destinations("tourism")
    data object MapsPoints : Destinations("mapsPoints")
    data object TourismRoute : Destinations("tourismRoute")

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

    // maps navgraph
    data object Transport : Destinations("transport")
    data object Planner : Destinations("planner")
    data object Fastest : Destinations("fastest")
    data object MapStops : Destinations("mapStops")
    data object TransportRoute : Destinations("transportRoute")

    // parking navgraph
    data object HomeParking : Destinations("homeParking")
    data object Parks : Destinations("parking")

    // sub parking nav
    data object Parking : Destinations("homeSubParking")
    data object ParkingInformation : Destinations("parkingInformation")
    data object ParkingBooking : Destinations("parkingBooking")
    data object ParkingRoute : Destinations("parkingRoute")
    data object ParkingQR : Destinations("parkingQR")
}