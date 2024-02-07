package net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens

enum class MapType {
    ROADMAP, SATELLITE, HYBRID, TERRAIN
}

enum class MapStyle {
    DARK, LIGHT, NEON
}

enum class Notification {
    SIEMPRE, NUNCA, A_VECES
}

data class ConfigurationState (
    // conf
    val city : String = "Moroleon",
    val language : Int = 0,
    val theme : Boolean = false,

    // map
    val stops : Boolean = false,
    val stations : Boolean = false,
    val currentLocation : Boolean = false,
    val food : Boolean = false,
    val health : Boolean = false,
    val mapType : MapType = MapType.ROADMAP,
    val mapStyle : MapStyle = MapStyle.LIGHT,

    // notification
    val notificationType : Notification = Notification.SIEMPRE,
    val alerts : Boolean = true,
    val suscription : Boolean = true,
    val map : Boolean = true,

    // privacy
    val current : Boolean = true,
    val ads : Boolean = true,
    val routes : Boolean = true,
    val payment : Boolean = true
)