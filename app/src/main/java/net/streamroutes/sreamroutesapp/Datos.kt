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
        "Ciudad", // 0

        // menu screen
        "Menu", // 1
        "Nombre Usuario", // 2
        "Tipo Membresia", // 3
        "Ciudad", // 4
        "Version Premium", // 5
        "Rutas", // 6
        "Planifica tu viaje", // 7
        "Compartir Ubicacion", // 8
        "Ubi. act:", // 9
        "No se pudo obtener la ubicacion actual. Asegurate de tener la ubicacion encendida", // 10
        "Comparte", // 11
        "Valoranos", // 12
        "Configuracion", // 13
        "Ayuda y soporte", // 14

        // SuscripcionScreen
        "Suscripcion", // 15
        "Elimina anuncios", // 16
        "Disfruta de tu viaje sin los anuncios emergentes de la aplicacion.", // 17
        "Muevete seguro", // 18
        "Comparte tu ubicacion real en todo momento con tu gente de confianza.", // 19
        "Prepara tu viaje", // 20
        "Programa una lista de ubicaciones las cuales quieras visitar sin perder tanto tiempo buscando una ruta.", // 21
        "Ubicaciones en tiempo real", // 22
        "Ten presente la ubicacion de tu autobus en todo momento.", // 23
        "Mensual", // 24
        "Anual", // 25
        "Mes", // 26
        "SUSCRIBIRSE", // 27
        "Saber mas...", // 28

        // RoutesScreen
        "Origen", // 29
        "Destino", // 30
        "Hora", // 31
        "BUSCAR", // 32

        // Menu (compartir ubicacion)
        "Comparte tu ubicacion en tiempo real", // 33

        // Menu (compartir aplicacion)
        "Comparte la aplicacion con tus amigos", // 34

        // TripScreen
        "Planifica tu viaje", // 35
        "Marcador Origen", // 36
        "Ubicacion", // 37
        "Lugares seleccionados", // 38
        "Colonia", // 39
        "CP", // 40
        "Plan guardado a", // 41
        "ubicacion(es)", // 42
        "PLANEAR", // 43

        // ValoranoScreen
        "Valoranos", // 44
        "¡Calificanos!", // 45
        "Haznos saber tu conformidad con la aplicación, " +
                "tu opinión es muy importante para nosotros. " +
                "Saber tu conformidad en una escala nos ayuda " +
                "a mejorar la aplicación y lanzar una mejor " +
                "interfaz para el usuario.", // 46
        "ENVIAR", // 47

        // ConfigurationScreen
        "Configuracion", // 48
        "Cambiar de ciudad", // 49
        "Notificaciones", // 50
        "Mapa", // 51
        "Privacidad", // 52

        // ChangeCityScreen
        "Cambiar Ciudad", // 53

        // NotificationScreen
        "Notificaciones", // 54
        "Notificaciones push", // 55
        "Tipo de notificacion", // 56
        "Siempre", // 57
        "Durante", // 58
        "Nunca", // 59
        "Noticias de la aplicacion", // 60
        "Nuevas versiones, promociones, etc.", // 61
        "Alertas", // 62
        "Cambios de horarios, rutas, etc.", // 63
        "Suscripcion", // 64
        "Expiracion de la suscripcion, etc.", // 65

        // MapScreen
        "Mapa", // 66
        "Marcadores de paradas", // 67
        "Indica donde son las paradas.", // 68
        "Marcadores de terminales", // 69
        "Indica estaciones de autobuses.", // 70
        "Marcadores de ubicacion act.", // 71
        "Mira tu ubicacion actual.", // 72
        "Comida", // 73
        "Restaurantes, puestos de comida, etc.", // 74
        "Salud", // 75
        "Hospitales, farmacias, consultorios, etc.", // 76

        // PrivacityScreeen
        "Privacidad", // 77
        "Localizacion", // 78
        "Podremos sugerirte mejores rutas en base a tu ubicacion. Permite este servicio incluso cuando la app no este en uso.", // 79
        "Anuncios personalizados", // 80
        "Podremos usar tus datos para mostrarte anuncios que podrian ser relevantes para ti. Si esta opcion no esta marcada se mostraran anuncios genericos.", // 81
        "Rutas personalizadas", // 82
        "Usar tus rutas frecuentes para generarte un plan de viaje personalizado para ti. Si esta opcion no esta marcada no se mostratan estrategias de viajes personaliszadas.", // 83
        "Pago de suscripcion", // 84
        "Si esta opcion esta marcada se permitira el cobro automatico de la suscripcion cada mes.", // 85

        // HelpScreen
        "Ayuda", // 86
        "Comentarios", // 87
        "¿Sugerencias?", // 88
        "Contactanos", // 89
        "Cuentanos tu experiencia.", // 90
        "Acerca de", // 91
        "Version de la aplicacion.", // 92

        // HelpCommentScreen
        "Comentarios", // 93
        "Cuentanos como podemos ayudarte", // 94
        "¿Deseas incluir información de la cuenta? (Opcional)", // 95
        "Si incluyes detalles de la cuenta en tu comentario," +
                "como correo, etc. Sera mas facil para nosotros responderte", // 96
        "ENVIAR", // 97
        "Se tomara en cuenta tus sugerencias para futuras versiones de la aplicación", // 98

        // HelpContactScreen
        "Contactanos", // 99
        "Nombre", // 100
        "Nuestro correo", // 101
        "Mensaje", // 102
        "ENVIAR", // 103
        "ITSUR, Uriangato, Gto.", // 104
        "¿Como llegar?", // 105

        // HelpAboutScreen
        "Acerca de", // 106
        "Version", // 107
        "Stream Routes. ® Todos los derechos reservados 2023-2024.", // 108

        // login screen
        "Telefono", // 109
        "Contraseña", // 110
        "Olvide mi", // 111
        "contraseña", // 112
        "INGRESAR", // 113
        "¿No tienes cuenta?", // 114
        "Registrate", // 115

        // registration screen
        "Registrate", // 116
        "Telefono", // 117
        "Contraseña", // 118
        "Confirmar Contraseña", // 119
        "REGISTRARSE", // 120

        // verification screen
        "Olvide mi contraseña", // 121
        "Telefono", // 122
        "Codigo enviado", // 123
        "Ingresa un telefono valido", // 124
        "ENVIAR", // 125
        "Codgio de verificacion", // 126
        "Codigo", // 127
        "Codigo incorrecto", // 128
        "VERIFICAR", // 129

        // changeScreen
        "Cambiar Contraseña", // 130
        "Contraseña", // 131
        "Confirmar contraseña", // 132
        "Contraseña", // 133
        "CAMBIAR" // 134
    )

    // lista de idioma EN
    val en = listOf(
        // Main screen
        "City", // 0
        // Menu screen
        "Menu", // 1
        "Username", // 2
        "Membership Type", // 3
        "City", // 4
        "Premium Version", // 5
        "Routes", // 6
        "Plan Your Trip", // 7
        "Share Location", // 8
        "Current Location", // 9
        "Could not retrieve current location. Make sure location services are enabled.", // 10
        "Share", // 11
        "Rate Us", // 12
        "Settings", // 13
        "Help and Support", // 14

        // Subscription Screen
        "Subscription", // 15
        "Remove Ads", // 16
        "Enjoy your journey without popup ads in the application.", // 17
        "Travel Safely", // 18
        "Share your real-time location with your trusted contacts.", // 19
        "Plan Your Journeys", // 20
        "Create a list of locations to visit without wasting time searching for routes.", // 21
        "Real-Time Locations", // 22
        "Keep track of your bus's location at all times.", // 23
        "Monthly", // 24
        "Annual", // 25
        "Month", // 26
        "SUBSCRIBE", // 27
        "Learn More...", // 28

        // Routes Screen
        "Origin", // 29
        "Destination", // 30
        "Time", // 31
        "Search", // 32

        // Menu (Share Location)
        "Share Your Location in Real-Time", // 33

        // Menu (Share App)
        "Share the App with Friends", // 34

        // Trip Screen
        "Plan Your Trip", // 35
        "Origin Marker", // 36
        "Location", // 37
        "Selected Places", // 38
        "Neighborhood", // 39
        "PC", // 40
        "Saved as Plan for", // 41
        "location(s)", // 42
        "PLAN", // 43

        // Rate Us Screen
        "Rate Us", // 44
        "Rate the App!", // 45
        "Let us know your satisfaction with the application. Your feedback is crucial to us. Understanding your satisfaction on a scale helps us enhance the app and deliver a better user interface.", // 46
        "SEND", // 47

        // Settings Screen
        "Settings", // 48
        "Change City", // 49
        "Notifications", // 50
        "Map", // 51
        "Privacy", // 52

        // Change City Screen
        "Change City", // 53

        // Notification Screen
        "Notifications", // 54
        "Push Notifications", // 55
        "Notification Type", // 56
        "Always", // 57
        "During", // 58
        "Never", // 59
        "App News", // 60
        "New Versions, Promotions, etc.", // 61
        "Alerts", // 62
        "Schedule Changes, Routes, etc.", // 63
        "Subscription", // 64
        "Subscription Expiry, etc.", // 65

        // Map Screen
        "Map", // 66
        "Stop Markers", // 67
        "Indicate where the stops are.", // 68
        "Terminal Markers", // 69
        "Indicate bus stations.", // 70
        "Current Location Markers", // 71
        "See your current location.", // 72
        "Food", // 73
        "Restaurants, Food Stalls, etc.", // 74
        "Healthcare", // 75
        "Hospitals, Pharmacies, Clinics, etc.", // 76

        // Privacy Screen
        "Privacy", // 77
        "Location", // 78
        "We can suggest better routes based on your location. Allow this service even when the app is not in use.", // 79
        "Personalized Ads", // 80
        "We may use your data to show you ads relevant to you. If this option is not selected, generic ads will be displayed.", // 81
        "Custom Routes", // 82
        "Use your frequent routes to generate a personalized travel plan. If this option is not selected, personalized travel strategies will not be shown.", // 83
        "Subscription Payment", // 84
        "If this option is enabled, automatic subscription billing will be allowed every month.", // 85

        // Help Screen
        "Help", // 86
        "Comments", // 87
        "Suggestions?", // 88
        "Contact Us", // 89
        "Share your experience with us.", // 90
        "About", // 91
        "App Version", // 92

        // Help Comment Screen
        "Comments", // 93
        "Tell us how we can assist you", // 94
        "Include account information? (Optional)", // 95
        "Including account details, such as email, will help us respond to you more effectively.", // 96
        "SEND", // 97
        "Your suggestions will be considered for future versions of the app.", // 98

        // Help Contact Screen
        "Contact Us", // 99
        "Name", // 100
        "Our Email", // 101
        "Message", // 102
        "SEND", // 103
        "ITSUR, Uriangato, Gto.", // 104
        "How to Reach Us?", // 105

        // Help About Screen
        "About", // 106
        "Version", // 107
        "Stream Routes. ® All rights reserved 2023-2024.", // 108

        // Login Screen
        "Phone Number", // 109
        "Password", // 110
        "Forgot My", // 111
        "Password", // 112
        "LOGIN", // 113
        "Don't have an account?", // 114
        "Sign Up", // 115

        // Registration Screen
        "Sign Up", // 116
        "Phone Number", // 117
        "Password", // 118
        "Confirm Password", // 119
        "REGISTER", // 120

        // Verification Screen
        "Forgot Password", // 121
        "Phone Number", // 122
        "Code Sent", // 123
        "Enter a valid phone number", // 124
        "SEND", // 125
        "Verification Code", // 126
        "Code", // 127
        "Incorrect Code", // 128
        "VERIFY", // 129

        // Change Password Screen
        "Change Password", // 130
        "Password", // 131
        "Confirm Password", // 132
        "Password", // 133
        "CHANGE" // 134
    )

    fun languageType(): List<String> {
        if (idioma == 0) return es
        return en
    }



}