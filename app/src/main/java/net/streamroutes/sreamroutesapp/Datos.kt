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
    var tema by mutableStateOf(false)

    // lista de idioma ES
    // IDIOMA ESPAÑOL
    val es = listOf(
        // Dialogs > Dialogs.kt
        " y vuelve a intentarlo nuevamente.",                                                                       // 0
        ", baja del autobús con cuidado. ¡Esperamos verte de nuevo!",                                               // 1
        ", verifica si necesitas bajarte aquí.",                                                                    // 2
        "Aceptar",                                                                                                  // 3
        "Acepto los términos y condiciones",                                                                        // 4
        "Al utilizar nuestra aplicación, aceptas los términos y condiciones de nuestro aviso de privacidad.",       // 5
        "autobús",                                                                                                  // 6
        "Aviso de privacidad",                                                                                      // 7
        "Cambia el tipo de mapa en el botón de la parte superior derecha a lado izquierdo de idioma.",              // 8
        "conexión a internet",                                                                                      // 9
        "Continuar",                                                                                                // 10
        "destino",                                                                                                  // 11
        "El menú de opciones esta posicionado en la parte superior izquierda.",                                     // 12
        "está cerca. Mantente alerta para abordarlo.",                                                              // 13
        "Finalizar",                                                                                                // 14
        "Has llegado a tu ",                                                                                        // 15
        "Hemos llegado a una ",                                                                                     // 16
        "Idioma de la aplicación",                                                                                  // 17
        "Interactúa con el mapa desplazandote por el usando tus dedos.",                                            // 18
        "La aplicación no tendrá acceso a algunas funciones.",                                                      // 19
        "Mapa interactivo",                                                                                         // 20
        "Marcadores",                                                                                               // 21
        "Menú de opciones",                                                                                         // 22
        "No permitir",                                                                                              // 23
        "Omitir",                                                                                                   // 24
        "parada",                                                                                                   // 25
        "Permitir acceso a tu ubicación",                                                                           // 26
        "Permitir acceso a tus contactos",                                                                          // 27
        "Permitir",                                                                                                 // 28
        "Puedes cambiar el idioma en el botón de la parte superior derecha, de igual forma en configuración.",      // 29
        "Regresar",                                                                                                 // 30
        "Siguiente",                                                                                                // 31
        "Stream Routes necesita acceder a tu ubicación para ofrecerte una experiencia más personalizada, mostrarte rutas y lugares de interés cercanos.",       // 32
        "Stream Routes necesita acceder a tus contactos para propocionar una experiencia personalizada, facilitar la conexión con tus amigos y contactos.",     // 33
        "Tipo de mapa",                                                                                             // 34
        "Toca los marcadores del mapa para obtener mas información sobre el.",                                      // 35
        "Tu ",                                                                                                      // 36
        "Verifica tu ",                                                                                             // 37

        // ConfigurationScreens > ChangeCityScreen.kt
        "Te enviará al menú de configuraciones",            // 38

        // ConfigurationScreens > ConfigurationScreen.kt
        "Idioma de la aplicación",                          // 39
        "Modo claro",                                       // 40
        "Modo oscuro",                                      // 41
        "Te enviará al menú de opciones",                   // 42

        // ConfigurationScreens > MapScreen.kt
        "Te enviará al menú de configuraciones",            // 43

        // ConfigurationScreens > NotificationScreen.kt
        "Te enviará al menú de configuraciones",            // 44

        // ConfigurationScreens > PrivacityScreen.kt
        "Te enviará al menú de configuraciones",            // 45

        // HelpScreens > HelAboutAppScreen.kt
        "Logo Stream Routes",                               // 46
        "Te enviará al menú de opciones",                   // 47

        // HelpScreens > HelpCommentsScreen.kt
        "Cuentanos como podemos ayudarte.",                 // 48
        "Te enviará al menú de opciones",                   // 49
        "Enviar",

        // HelpScreens > HelpContactScreen.kt
        "Nombre",                                           // 50
        "Nuestro correo",                                   // 51
        "Mensaje",                                          // 52
        "Escribe tu inconveniente.",                        // 53
        "Enviar",                                           // 54
        "Te enviará al menú de opciones",                   // 55

        // HelpScreens > HelpScreen.kt
        "Reseñas",                                          // 56
        "Revisa y opina.",                                  // 57
        "Te enviará al menú de opciones",                   // 58

        // HelpScreens > ResenaScreen.kt
        "Te enviará al menú de opciones",                   // 59
        "Reseñas",                                          // 60
        "Comparte tu opinión",                              // 61
        "Imagen",                                           // 62
        "Comenta tu viaje",                                 // 63

        // MenuScreens > RoutesScreen
        "Borrar texto de destino",              // 64
        "Buscar destino",                       // 65
        "Buscar",                               // 66
        "Cambiar el tipo de mapa",              // 67
        "Ciudad",                               // 68
        "Destino:",                             // 69
        "Duración Ruta",                        // 70
        "Icono de agregar como destino",        // 71
        "Icono de compartir ruta",              // 72
        "Icono de favorito",                    // 73
        "Icono para cerrar el dialogo",         // 74
        "Inicio:",                              // 75
        "Marcador Destino",                     // 76
        "Más información",                      // 77
        "Nombre Ruta",                          // 78
        "Paradas",                              // 79
        "Rutas",                                // 80
        "Tipo de mapa",                         // 81

        // MenuScreens > SuscripcionScreen.kt
        "Te enviará al menú de opciones",       // 82

        // MenuScreens > TipScreen.kt
        "Te enviará al menú de opciones",       // 83

        // MenuScreens > ValoranoScreen.kt
        "Te enviará al menú de opciones",       // 84

        // ProfileScreens > ProfileScreen.kt
        "Activada",                 // 85
        "Activo",                   // 86
        "Apellidos",                // 87
        "Autenticado",              // 88
        "Cambiar contraseña",       // 89
        "Cambiar correo",           // 90
        "Cambiar teléfono",         // 91
        "Cancelar suscripción",     // 92
        "Cancelar",                 // 93
        "Cerrar sesión",            // 94
        "Ciudad",                   // 95
        "Colonia",                  // 96
        "Confirmar contraseña",     // 97
        "Confirmar correo",         // 98
        "Confirmar teléfono",       // 99
        "Contraseña actual",        // 100
        "Contraseña nueva",         // 101
        "Contraseña",               // 102
        "Contraseña, verificación en dos pasos.",   // 103
        "Correo electrónico",       // 104
        "Correo",                   // 105
        "Corte",                    // 106
        "CP",                       // 107
        "Cumpleaños",               // 108
        "Código",                   // 109
        "Dirección",                // 110
        "Duración",                 // 111
        "Editar",                   // 112
        "Entretenimiento, comida, ropa",    // 113
        "Enviar código",            // 114
        "Estado",                   // 115
        "Estatus",                  // 116
        "Estudiante",               // 117
        "Estudiantil",              // 118
        "Expiración",               // 119
        "Fecha nac, país, teléfono, etc.",  // 120
        "Guardar información",      // 121
        "Género",                   // 122
        "Id. Membresia",            // 123
        "Información personal",     // 124
        "Inicio",                   // 125
        "Intereses",                // 126
        "Mejorar suscripción",      // 127
        "Membresía",                // 128
        "Mensual (Estudiantil)",    // 129
        "Mensual",                  // 130
        "Moneda pago",              // 131
        "MXN (Peso mexicano)",      // 132
        "Más información",          // 133
        "Nombre de usuario",        // 134
        "Nombre",                   // 135
        "Nuevo correo",             // 136
        "Nuevo teléfono",           // 137
        "Ocupación",                // 138
        "País",                     // 139
        "Rutas favoritas",          // 140
        "Seguridad",                // 141
        "Teléfono",                 // 142
        "Tipo membresía",           // 143
        "Tipo",                     // 144
        "Tipo, duración, corte, etc.",  // 145
        "Usuario",                  // 146
        "Verificación",             // 147
        "Verificar contraseña",     // 148
        "Verificar código",         // 149
        "Vigente",                  // 150

        // Start > MainScreen.kt
        "Ayuda y soporte",          // 151
        "Cerrar sesión",            // 152
        "Comparte",                 // 153
        "Compartir ubicación",      // 154
        "Configuración",            // 155
        "Descargando ruta (nombre)",    // 156
        "Descargando rutas",        // 157
        "Descargar rutas",          // 158
        "Entretenimiento",          // 159
        "Error al obtener la ubicación actual. Inténtalo mas tarde.",   // 160
        "Hospitales",               // 161
        "Paradas",                  // 162
        "Planifica tu viaje",       // 163
        "Por favor ve a la configuración de la aplicación y habilita los permisos de ubicación.",   // 164
        "Resturantes",              // 165
        "Rutas",                    // 166
        "Te mostrara el menú",      // 167
        "Terminales",               // 168
        "Valóranos",                // 169
        "Versión Premium"           // 170
    )




    val en = listOf(
        // Dialogs > Dialogs.kt
        " and try again.",                                                                              // 0
        ", step off the bus carefully. We hope to see you again!",                                      // 1
        ", check if you need to get off here.",                                                         // 2
        "Accept",                                                                                       // 3
        "I accept the terms and conditions",                                                            // 4
        "By using our application, you accept the terms and conditions of our privacy policy.",         // 5
        "bus",                                                                                          // 6
        "Privacy notice",                                                                               // 7
        "Change the map type in the button on the top right to the left language side.",                // 8
        "internet connection",                                                                          // 9
        "Continue",                                                                                     // 10
        "destination",                                                                                  // 11
        "The options menu is located at the top left.",                                                 // 12
        "is nearby. Stay alert to board it.",                                                           // 13
        "Finish",                                                                                       // 14
        "You have reached your ",                                                                       // 15
        "We have arrived at a ",                                                                        // 16
        "App language",                                                                                 // 17
        "Interact with the map by scrolling it using your fingers.",                                    // 18
        "The application will not have access to some functions.",                                      // 19
        "Interactive map",                                                                              // 20
        "Markers",                                                                                      // 21
        "Options menu",                                                                                 // 22
        "Don't allow",                                                                                  // 23
        "Skip",                                                                                         // 24
        "bus stop",                                                                                     // 25
        "Allow access to your location",                                                                // 26
        "Allow access to your contacts",                                                                // 27
        "Allow",                                                                                        // 28
        "You can change the language in the button at the top right, similarly in settings.",           // 29
        "Back",                                                                                         // 30
        "Next",                                                                                         // 31
        "Stream Routes needs access to your location to offer you a more personalized experience, show you routes, and nearby points of interest.",     // 32
        "Stream Routes needs access to your contacts to provide a personalized experience, facilitate connecting with your friends and contacts.",      // 33
        "Map type",                                                                                     // 34
        "Tap the map markers to get more information about them.",                                      // 35
        "Your ",                                                                                        // 36
        "Verify your ",                                                                                 // 37

        // ConfigurationScreens > ChangeCityScreen.kt
        "It will send you to the settings menu",            // 38

        // ConfigurationScreens > ConfigurationScreen.kt
        "App language",                                     // 39
        "Light mode",                                       // 40
        "Dark mode",                                        // 41
        "It will send you to the options menu",             // 42

        // ConfigurationScreens > MapScreen.kt
        "It will send you to the settings menu",            // 43

        // ConfigurationScreens > NotificationScreen.kt
        "It will send you to the settings menu",            // 44

        // ConfigurationScreens > PrivacityScreen.kt
        "It will send you to the settings menu",            // 45

        // HelpScreens > HelAboutAppScreen.kt
        "Stream Routes logo",                               // 46
        "It will send you to the options menu",             // 47

        // HelpScreens > HelpCommentsScreen.kt
        "Tell us how we can help you.",                     // 48
        "It will send you to the options menu",             // 49
        "Send",

        // HelpScreens > HelpContactScreen.kt
        "Name",                                             // 50
        "Our email",                                        // 51
        "Message",                                          // 52
        "Write down your issue.",                           // 53
        "Send",                                             // 54
        "It will send you to the options menu",             // 55

        // HelpScreens > HelpScreen.kt
        "Reviews",                                          // 56
        "Review and give your opinion.",                    // 57
        "It will send you to the options menu",             // 58

        // HelpScreens > ResenaScreen.kt
        "It will send you to the options menu",             // 59
        "Reviews",                                          // 60
        "Share your opinion",                               // 61
        "Image",                                            // 62
        "Comment on your trip",                             // 63

        // MenuScreens > RoutesScreen
        "Clear destination text",               // 64
        "Search destination",                   // 65
        "Search",                               // 66
        "Change map type",                      // 67
        "City",                                 // 68
        "Destination:",                         // 69
        "Route Duration",                       // 70
        "Add as destination icon",              // 71
        "Share route icon",                     // 72
        "Favorite icon",                        // 73
        "Close dialog icon",                    // 74
        "Start:",                               // 75
        "Destination Marker",                   // 76
        "More information",                     // 77
        "Route Name",                           // 78
        "Stops",                                // 79
        "Routes",                               // 80
        "Map type",                             // 81

        // MenuScreens > SuscripcionScreen.kt
        "It will send you to the options menu", // 82

        // MenuScreens > TipScreen.kt
        "It will send you to the options menu", // 83

        // MenuScreens > ValoranoScreen.kt
        "It will send you to the options menu", // 84

        // ProfileScreens > ProfileScreen.kt
        "Activated",                            // 85
        "Active",                               // 86
        "Last Name",                            // 87
        "Authenticated",                        // 88
        "Change password",                      // 89
        "Change email",                         // 90
        "Change phone number",                  // 91
        "Cancel subscription",                  // 92
        "Cancel",                               // 93
        "Log out",                              // 94
        "City",                                 // 95
        "Neighborhood",                         // 96
        "Confirm password",                     // 97
        "Confirm email",                        // 98
        "Confirm phone number",                 // 99
        "Current password",                     // 100
        "New password",                         // 101
        "Password",                             // 102
        "Password, two-step verification.",     // 103
        "Email",                                // 104
        "Email",                                // 105
        "Cut",                                  // 106
        "Postal Code",                          // 107
        "Birthday",                             // 108
        "Code",                                 // 109
        "Address",                              // 110
        "Duration",                             // 111
        "Edit",                                 // 112
        "Entertainment, food, clothing",        // 113
        "Send code",                            // 114
        "State",                                // 115
        "Status",                               // 116
        "Student",                              // 117
        "Student",                              // 118
        "Expiration",                           // 119
        "Date of birth, country, phone, etc.",  // 120
        "Save information",                     // 121
        "Gender",                               // 122
        "Membership ID",                        // 123
        "Personal information",                 // 124
        "Home",                                 // 125
        "Interests",                            // 126
        "Upgrade subscription",                 // 127
        "Membership",                           // 128
        "Monthly (Student)",                    // 129
        "Monthly",                              // 130
        "Payment currency",                     // 131
        "MXN (Mexican Peso)",                   // 132
        "More information",                     // 133
        "Username",                             // 134
        "Name",                                 // 135
        "New email",                            // 136
        "New phone number",                     // 137
        "Occupation",                           // 138
        "Country",                              // 139
        "Favorite routes",                      // 140
        "Security",                             // 141
        "Phone",                                // 142
        "Membership type",                      // 143
        "Type",                                 // 144
        "Type, duration, cut, etc.",            // 145
        "User",                                 // 146
        "Verification",                         // 147
        "Verify password",                      // 148
        "Verify code",                          // 149
        "Valid",                                // 150

        // Start > MainScreen.kt
        "Help and support",                     // 151
        "Log out",                              // 152
        "Share",                                // 153
        "Share location",                       // 154
        "Settings",                             // 155
        "Downloading route (name)",             // 156
        "Downloading routes",                   // 157
        "Download routes",                      // 158
        "Entertainment",                        // 159
        "Error obtaining current location. Please try again later.",    // 160
        "Hospitals",                            // 161
        "Stops",                                // 162
        "Plan your trip",                       // 163
        "Please go to the app settings and enable location permissions.",       // 164
        "Restaurants",                          // 165
        "Routes",                               // 166
        "It will show you the menu",            // 167
        "Terminals",                            // 168
        "Rate us",                              // 169
        "Premium Version"                       // 170
    )

    fun languageType(): List<String> {
        if (idioma == 0) return es
        return en
    }



}