package net.streamroutes.sreamroutesapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {


    // variables importantes
    // 0 = ES 1 = EN
    var idioma by mutableStateOf(0)

    // lista de idioma ES
    val es = listOf(
        // main screen
        "Ciudad",
        // menu screen
        "Menu",
        "Nombre Usuario",
        "Tipo Membresia",
        "Ciudad",
        "Version Premium",
        "Rutas",
        "Planifica tu viaje",
        "Compartir Ubicacion",
        "Ubi. act:",
        "No se pudo obtener la ubicacion actual. Asegurate de tener la ubicacion encendida",
        "Comparte",
        "Valoranos",
        "Configuracion",
        "Ayuda y soporte",

        //SuscripcionScreen
        "Suscripcion",
        "Elimina anuncios",
        "Disfruta de tu viaje sin los anuncios emergentes de la aplicacion.",
        "Muevete seguro",
        "Comparte tu ubicacion real en todo momento con tu gente de confianza.",
        "Prepara tu viaje",
        "Programa una lista de ubicaciones las cuales quieras visitar sin perder tanto tiempo buscando una ruta.",
        "Ubicaciones en tiempo real",
        "Ten presente la ubicacion de tu autobus en todo momento.",
        "Mensual",
        "Anual",
        "Mes",
        "SUSCRIBIRSE",
        "Saber mas...",

        //RoutesScreen
        "Origen",
        "Destino",
        "Hora",
        "Buscar",

        //TripScreen
        "Planifica tu viaje",
        "Ubicación seleccionada",
        "Ubicacion",
        "Lugares seleccionados",
        "Plan guardado a",
        "PLANEAR",

        //ValoranoScreen
        "Valoranos",
        "Te enviara al menu de opciones",
        "Haznos saber tu conformidad con la aplicación, " +
                "tu opinión es muy importante para nosotros. " +
                "Saber tu conformidad en una escala nos ayuda " +
                "a mejorar la aplicación y lanzar una mejor " +
                "interfaz para el usuario.",
        "ENVIAR",

        //ChangeCityScreen
        "Cambiar Ciudad",
        "Te enviara al menu de configuraciones",

        //NotificationScreen
        "Notificaciones",
        "Te enviara al menu de configuraciones",
        "Notificaciones push",
        "Tipo de notificacion",
        "Noticias de la aplicacion",
        "Nuevas versiones, promociones, etc.",
        "Alertas",
        "Cambios de horarios, rutas, etc.",
        "Suscripcion",
        "Expiracion de la suscripcion, etc."
    )

    // lista de idioma EN
    val en = listOf(
        // main screen
        "City",
        // menu screen
        "Menu",
        "Username",
        "Membership Type",
        "City",
        "Premium Version",
        "Routes",
        "Plan Your Trip",
        "Share Location",
        "Location: ",
        "Couldn't get current location. Make sure your location is turned on.",
        "Share",
        "Rate Us",
        "Settings",
        "Help and Support",

        //SuscripcionScreen
        "Subscription",
        "Remove ads",
        "Enjoy your trip without the app's pop-up ads.",
        "move safe",
        "Share your real location at all times with your trusted people.",
        "Prepare your trip",
        "Schedule a list of locations you want to visit without spending so much time looking for a route.",
        "Locations in real time",
        "Keep in mind the location of your bus at all times.",
        "Monthly",
        "Annual",
        "Month",
        "TO SUBSCRIBE",
        "Know more...",

        //RoutesScreen
        "Origin",
        "Destination",
        "Hour",
        "Search",

        //TripScreen
        "Plan your trip",
        "Selected location",
        "Location",
        "Selected Places",
        "Plan saved to",
        "TO PLAN",

        //ValueScreen
        "Value us",
        "It will send you to the options menu",
        "Let us know your agreement with the application," +
                "Your opinion is very important to us." +
                "Knowing your conformity on a scale helps us" +
                "to improve the app and release a better one" +
                "user interface.",
        "SEND",

        //ChangeCityScreen
        "Change City",
        "It will send you to the settings menu",

        //NotificationScreen
        "Notifications",
        "It will send you to the settings menu",
        "Push notifications",
        "Type of notification",
        "Application News",
        "New versions, promotions, etc.",
        "Alerts",
        "Changes of schedules, routes, etc.",
        "Subscription",
        "Subscription expiration, etc."
    )

    fun languageType(): List<String> {
        if (idioma == 0) return es
        return en
    }



}