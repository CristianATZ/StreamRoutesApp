package net.streamroutes.sreamroutesapp.viewmodel

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
        "Rumapp necesita acceder a tu ubicación para ofrecerte una experiencia más personalizada, mostrarte rutas y lugares de interés cercanos.",       // 32
        "Rumapp necesita acceder a tus contactos para propocionar una experiencia personalizada, facilitar la conexión con tus amigos y contactos.",     // 33
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
        "Logo Rumapp Routes",                               // 46
        "Te enviará al menú de opciones",                   // 47

        // HelpScreens > CommentScreen.kt
        "Cuentanos cómo podemos ayudarte.",                 // 48
        "Te enviará al menú de opciones",                   // 49
        "Enviar",

        // HelpScreens > ContactScreen.kt
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
        "Duración ruta:",                        // 70
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
        "Suscripción",      // 127
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
        "Rutas de transporte",                    // 166
        "Te mostrara el menú",      // 167
        "Terminales",               // 168
        "Valóranos",                // 169
        "Versión Premium",          // 170
        "Ciudad",                   // 171



        // ORACIONES FALTANTES
        // MenuScreen.kt
        "Menú",                     // 172
        "Nombre Usuario",           // 173
        "Tipo Membresía",           // 174
        "Versión Premium",          // 175
        "Planifica tu viaje",       // 176
        "Compartir Ubicación",      // 177
        "Ubi. act:",                // 178
        "No se pudo obtener la ubicacion actual. Asegurate de tener la ubicación encendida",    // 179
        "Comparte",                 // 180
        "Valóranos",                // 181
        "Configuración",            // 182
        "Ayuda y soporte",          // 183

        // MenuScreens > SuscriptionScreen.kt
        "Suscripción",              // 184
        "Elimina anuncios",         // 185
        "Disfruta de tu viaje sin los anuncios emergentes de la aplicación.",       // 186
        "Muévete seguro",           // 187
        "Comparte tu ubicación real en todo momento con tu gente de confianza.",    // 188
        "Prepara tu viaje",         // 189
        "Programa una lista de ubicaciones las cuales quieras visitar sin perder tanto tiempo buscando una ruta.",  // 190
        "Ubicaciones en tiempo real",   // 191
        "Ten presente la ubicación de tu autobús en todo momento.", // 192
        "Mensual",                  // 193
        "Anual",                    // 194
        "Mes",                      // 195
        "Suscribirse",              // 196
        "Saber más...",             // 197

        // MenuScreens > RouteScreen.kt
        "Origen",       // 198
        "Hora",         // 199
        "Comparte tu ubicación en tiempo real",     // 200
        "Comparte la aplicación con tus amigos",    // 201

        // MenuScreens > TripScreen.kt
        "Planifica tu viaje",       // 202
        "Marcador Origen",          // 203
        "Buscar destino",                // 204
        "Lugares seleccionados",    // 205
        "Colonia",                  // 206
        "CP",                       // 207
        "Plan guardado a",          // 208
        "ubicación(es)",            // 209
        "Planear",                  // 210

        // MenuScreens > ValoranoScreen.kt
        "Valóranos",                // 211
        "¡Califícanos!",            // 212
        "Haznos saber tu conformidad con la aplicación, " +
                "tu opinión es muy importante para nosotros. " +
                "Saber tu conformidad en una escala nos ayuda " +
                "a mejorar la aplicación y lanzar una mejor " +
                "interfaz para el usuario.",    // 213
        "Enviar",                   // 214

        // ConfigurationScreen > ConfigurationScreen.kt
        "Configuración",        // 215
        "Cambiar de ciudad",    // 216
        "Notificaciones",       // 217
        "Mapa",                 // 218
        "Privacidad",           // 219

        // ConfigurationScreen > ConfigurationScreen.kt
        "Cambiar Ciudad",       // 220

        // NotificationScreen
        "Notificaciones",       // 221
        "Notificaciones push",  // 222
        "Tipo de notificación", // 223
        "Siempre",              // 224
        "Durante",              // 225
        "Nunca",                // 226
        "Noticias de la aplicación",    // 227
        "Nuevas versiones, promociones, etc.",  // 228
        "Alertas",              // 229
        "Cambios de horarios, rutas, etc.", // 230
        "Suscripción",          // 231
        "Expiración de la suscripción, etc.",   // 232

        // MapScreen
        "Mapa",                                         // 233
        "Paradas",                        // 234
        "Indica dónde son las paradas.",                // 235
        "Terminales",                     // 236
        "Indica estaciones de autobúses.",              // 237
        "Ubicacion actual",                 // 238
        "Mira tu ubicación actual.",                    // 239
        "Comida",                                       // 240
        "Restaurantes, puestos de comida, etc.",        // 241
        "Salud",                                        // 242
        "Hospitales, farmacias, consultorios, etc.",    // 243

        // PrivacityScreeen
        "Privacidad",           // 244
        "Localización",         // 245
        "Podremos sugerirte mejores rutas en base a tu ubicación. Permite este servicio incluso cuando la app no esté en uso.",     // 246
        "Anuncios personalizados",      // 247
        "Podremos usar tus datos para mostrarte anuncios que podrían ser relevantes para ti. Si esta opción no esta marcada se mostrarán anuncios genéricos.",  // 248
        "Rutas personalizadas",         // 249
        "Usar tus rutas frecuentes para generarte un plan de viaje personalizado para ti. Si esta opción no esta marcada no se mostrarán estrategias de viajes personaliszadas.",   // 250
        "Pago de suscripción",          // 251
        "Si esta opción está marcada se permitirá el cobro automatico de la suscripción cada mes.",     // 252

        // HelpScreen
        "Ayuda",                        // 253
        "Comentarios",                  // 254
        "¿Sugerencias?",                // 255
        "Contáctanos",                  // 256
        "Cuéntanos tu experiencia.",    // 257
        "Acerca de",                    // 258
        "Versión de la aplicación.",    // 259

        "Comentarios",                  // 260
        "¿Deseas incluir información de la cuenta? (Opcional)",                             // 261
        "Si incluyes detalles de la cuenta en tu comentario," +
                "como correo, etc. Será más fácil para nosotros responderte",               // 262
        "Se tomará en cuenta tus sugerencias para futuras versiones de la aplicación.",      // 263

        // HelpContactScreen
        "ITSUR, Uriangato, Gto.",       // 264
        "¿Cómo llegar?",                // 265

        // HelpAboutScreen
        "Acerca de",                    // 266
        "Versión",                      // 267
        "Rumapp. ® Todos los derechos reservados 2023-2024.",    // 268

        // login screen
        "Teléfono",                 // 269
        "Contraseña",               // 270
        "Olvidé mi",                // 271
        "contraseña",               // 272
        "Ingresar",                 // 273
        "¿No tienes cuenta?",       // 274
        "Registrate",               // 275

        // registration screen
        "Registrate",               // 276
        "Teléfono",                 // 277
        "Contraseña",               // 278
        "Confirmar Contraseña",     // 279
        "Registrarte",              // 280

        // verification screen
        "Olvidé mi contraseña",         // 281
        "Teléfono",                     // 282
        "Código enviado",               // 283
        "Ingresa un teléfono válido",   // 284
        "Enviar",                       // 285
        "Código de verificación",       // 286
        "Código",                       // 287
        "Código incorrecto",            // 288
        "Verificar",                    // 289

        // changeScreen
        "Cambiar Contraseña",           // 290
        "Contraseña",                   // 291
        "Confirmar contraseña",         // 292
        "Contraseña",                   // 293
        "Cambiar",                      // 294

        // extras
        "Ruta",                         // 295

        // TurismScreen
        "Turismo",                      // 296
        "Chat de apoyo",                // 297
        "Atención al turista",         // 298

        // extra login screen
        "Iniciar sesión",  // 300

        // extra change screen
        "Cancelar",     // 301

        // extra register screen
        "Cancelar",     // 302

        // extra main screen menu lateral
        "Paquetes",    // 303
        "Ruta mas rápida",  // 304
        "Turismo",    // 305
        "Chat general",   //306

        // extra suscription screen
        "Beneficios",       // 307
        "Paquetes premium", // 308
        "Paquete estudiantil",      // 309
        "Paquete con duración de 1 mes.",   // 310
        "Activo a cualquier persona que se encuentre estudiando.",      // 311
        "Contar con algún documento oficial expedido por el plantel educativo.",    // 312
        "Todas las funciones disponibles en este paquete",      // 313
        "Paquete general",      // 314
        "Disponible para cualquier persona.",   // 315
        "Paquete turista",      // 316
        "Paquete con duración de 15 días.",     // 317
        "Paquete anual",        // 318
        "Paquete con duración para todo el año.", // 319
        "Contratar",            // 320

        // fast screen
        "Elige un destino",     // 321
        "Toca el mapa para elegir un destino y calcular la ruta óptima para llegar.",    // 322
        "Aceptar",              // 323
        "Destino",              // 324
        "Calcular destino",     // 325

        // extra routes screen
        "Transporte",           // 326
        "Rutas",                // 327
        "Filtra aquí",          // 328
        "Filtrar",              // 329
        "Este autobús tiene localización en tiempo real.",      // 330
        "Aceptar",              // 331
        "Este autobús NO tiene localización en tiempo real.",   // 332
        "Autobús",              // 333
        "Mapa",                 // 334
        "Paradas",              // 335
        "Icónicos",             // 336
        "Duración",             // 337
        "Paradas totales",      // 338
        "Inicio ruta",          // 339
        "Final ruta",           // 340
        "Parada cercana",       // 341
        "Mostrar ruta",         // 342

        // extra trip screen
        "Busca aquí",           // 343
        "Agregar",              // 344
        "Cancelar",             // 345
        "Destinos",             // 346
        "Borrar todo",          // 347
        "Planificar",           // 348

        // extra tourism screen
        "Ver ruta",             // 349
        "Ver más",              // 350
        "Duración",             // 351
        "Paradas totales",      // 352
        "Inicio ruta",          // 353
        "Final ruta",           // 354
        "Mostrar ruta",         // 355

        // chat screen
        "Chat general",         // 356
        "Mensaje",              // 357

        // extra settings screen
        "Idioma EN/ES",               // 358
        "Tema oscuro",          // 359
        "Tema claro",           // 360

        // extra notificacions screen
        "Siempre",              // 361
        "Nunca",                // 362

        // extra map screen
        "Marcadores",           // 363
        "Mapa",                 // 364
        "Tipo de mapa",         // 365
        "Mapnik, etc.",         // 366
        "Tema del mapa",        // 367
        "Claro, oscuro, neón, etc.",    // 368

        // extra feedback screen
        "Enviar",               // 369

        // extra contact screen
        "Contáctanos",          // 370
        "Nombre",               // 371
        "Escribe tu inconveniente",   // 372
        "Enviar",               // 373
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
        "Rumapp needs access to your location to offer you a more personalized experience, show you routes, and nearby points of interest.",     // 32
        "Rumapp needs access to your contacts to provide a personalized experience, facilitate connecting with your friends and contacts.",      // 33
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
        "Rumapp logo",                               // 46
        "It will send you to the options menu",             // 47

        // HelpScreens > CommentScreen.kt
        "Tell us how we can help you.",                     // 48
        "It will send you to the options menu",             // 49
        "Send",

        // HelpScreens > ContactScreen.kt
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
        "Route duration:",                       // 70
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
        "Premium Version",                      // 170
        "City",                                 // 171



        // MISSING SENTENCES
        // MenuScreen.kt
        "Menu",                     // 172
        "Username",                 // 173
        "Membership Type",          // 174
        "Premium Version",          // 175
        "Plan Your Trip",           // 176
        "Share Location",           // 177
        "Real-time Location:",      // 178
        "Failed to retrieve current location. Make sure your location is enabled.",    // 179
        "Share",                    // 180
        "Rate Us",                  // 181
        "Settings",                 // 182
        "Help and Support",         // 183

        // MenuScreens > SuscriptionScreen.kt
        "Subscription",             // 184
        "Remove Ads",               // 185
        "Enjoy your trip without app pop-up ads.",       // 186
        "Move Safely",              // 187
        "Share your real-time location with your trusted contacts.",    // 188
        "Plan Your Trip",           // 189
        "Create a list of locations you want to visit without wasting time searching for a route.",  // 190
        "Real-time Locations",      // 191
        "Keep track of your bus's location at all times.", // 192
        "Monthly",                  // 193
        "Annual",                   // 194
        "Month",                    // 195
        "SUBSCRIBE",                // 196
        "Learn more...",            // 197

        // MenuScreens > RouteScreen.kt
        "Origin",       // 198
        "Time",         // 199
        "Share your real-time location",        // 200
        "Share the app with your friends",      // 201

        // MenuScreens > TripScreen.kt
        "Plan Your Trip",       // 202
        "Origin Marker",        // 203
        "Location",             // 204
        "Selected Places",      // 205
        "Neighborhood",         // 206
        "ZIP Code",             // 207
        "Saved Plan to",        // 208
        "location(s)",          // 209
        "PLAN",                 // 210

        // MenuScreens > ValoranoScreen.kt
        "Rate Us",          // 211
        "Rate Us!",         // 212
        "Let us know your satisfaction with the app; your feedback is very important to us. " +
                "Rating us on a scale helps us improve the app and release a better user interface.",    // 213
        "SEND",             // 214

        // ConfigurationScreen > ConfigurationScreen.kt
        "Settings",             // 215
        "Change City",          // 216
        "Notifications",        // 217
        "Map",                  // 218
        "Privacy",              // 219

        // ConfigurationScreen > ConfigurationScreen.kt
        "Change City",       // 220

        // NotificationScreen
        "Notifications",                            // 221
        "Push Notifications",                       // 222
        "Notification Type",                        // 223
        "Always",                                   // 224
        "During",                                   // 225
        "Never",                                    // 226
        "App Updates",                              // 227
        "New versions, promotions, etc.",           // 228
        "Alerts",                                   // 229
        "Schedule changes, route changes, etc.",    // 230
        "Subscription",                             // 231
        "Subscription expiration, etc.",            // 232

        // MapScreen
        "Map",                                      // 233
        "Stop Markers",                             // 234
        "Indicates where the stops are.",           // 235
        "Terminal Markers",                         // 236
        "Indicates bus stations.",                  // 2z7
        "Real-time Location Markers",               // 238
        "View your current location.",              // 239
        "Food",                                     // 240
        "Restaurants, food stalls, etc.",           // 241
        "Health",                                   // 242
        "Hospitals, pharmacies, clinics, etc.",     // 243

        // PrivacyScreeen
        "Privacy",              // 244
        "Location",             // 245
        "We may suggest better routes based on your location. Allow this service even when the app is not in use.",     // 246
        "Personalized Ads",      // 247
        "We may use your data to show you ads that may be relevant to you. If this option is not selected, generic ads will be shown.",  // 248
        "Personalized Routes",         // 249
        "Use your frequent routes to generate a personalized travel plan for you. If this option is not selected, personalized travel strategies will not be shown.",   // 250
        "Subscription Payment",          // 251
        "If this option is selected, automatic subscription payment will be allowed every month.",     // 252

        // HelpScreen
        "Help",                         // 253
        "Feedback",                     // 254
        "Suggestions?",                 // 255
        "Contact Us",                   // 256
        "Share your experience.",       // 257
        "About",                        // 258
        "App Version",                  // 259
        "Feedback",                     // 260
        "Do you want to include account information? (Optional)",                             // 261
        "If you include account details in your feedback, such as email, etc., it will be easier for us to respond to you.",   // 262
        "Your suggestions will be considered for future app versions.",      // 263

        // HelpContactScreen
        "ITSUR, Uriangato, Gto.",       // 264
        "How to Get Here?",             // 265

        // HelpAboutScreen
        "About",                                                // 266
        "Version",                                              // 267
        "Rumapp. ® All rights reserved 2023-2024.",      // 268

        // login screen
        "Phone Number",             // 269
        "Password",                 // 270
        "I forgot my",              // 271
        "password",                 // 272
        "Login",                    // 273
        "Don't have an account?",   // 274
        "Sign Up",                  // 275

        // registration screen
        "Sign Up",                  // 276
        "Phone Number",             // 277
        "Password",                 // 278
        "Confirm Password",         // 279
        "Register",                 // 280

        // verification screen
        "I forgot my password",         // 281
        "Phone Number",                 // 282
        "Code Sent",                    // 283
        "Enter a valid phone number",   // 284
        "Send",                         // 285
        "Verification Code",            // 286
        "Code",                         // 287
        "Incorrect Code",               // 288
        "Verify",                       // 289

        // changeScreen
        "Change Password",      // 290
        "Password",             // 291
        "Confirm Password",     // 292
        "Password",             // 293
        "Change",               // 294

        // extra
        "Route",                // 295

        // TurismScreen
        "Tourism",                  // 296
        "ChatBot",                  // 297
        "Tourist Support",           // 298

        // extra login screen
        "Log in",  // 300

        // extra change screen
        "Cancel",     // 301

        // extra register screen
        "Cancel",     // 302

        // extra main screen menu lateral
        "Packages",    // 303
        "Fastest Route",  // 304
        "Tourism",    // 305
        "General Chat",   //306

        // extra supscription screen
        "Benefits",       // 307
        "Premium Packages", // 308
        "Student Package",      // 309
        "Package with 1-month duration.",   // 310
        "Available to anyone currently studying.",      // 311
        "Must have an official document issued by the educational institution.",    // 312
        "All functions available in this package",      // 313
        "General Package",      // 314
        "Available for anyone.",   // 315
        "Tourist Package",      // 316
        "Package with 15-day duration.",     // 317
        "Annual Package",        // 318
        "Package with year-round duration.", // 319
        "Hire",            // 320

        // fast screen
        "Choose a destination",     // 321
        "Tap the map to select your destination and calculate the optimal route to get there.",    // 322
        "Accept",              // 323
        "Destination",              // 324
        "Calculate Destination",     // 325

        // extra routes screen
        "Transport",           // 326
        "Routes",                // 327
        "Filter here",          // 328
        "Filter",              // 329
        "This bus has real-time location.",      // 330
        "Accept",              // 331
        "This bus DOES NOT have real-time location.",   // 332
        "Bus",              // 333
        "Map",                 // 334
        "Stops",              // 335
        "Iconic",             // 336
        "Duration",             // 337
        "Total Stops",      // 338
        "Start Route",          // 339
        "End Route",           // 340
        "Nearby Stop",       // 341
        "Show Route",         // 342

        // extra trip screen
        "Search here",           // 343
        "Add",              // 344
        "Cancel",             // 345
        "Destinations",             // 346
        "Clear All",          // 347
        "Plan",           // 348

        // extra tourism screen
        "View Route",             // 349
        "View More",              // 350
        "Duration",             // 351
        "Total Stops",      // 352
        "Start Route",          // 353
        "End Route",           // 354
        "Show Route",         // 355

        // chat screen
        "General Chat",         // 356
        "Message",              // 357

        // extra settings screen
        "Language ES/EN",               // 358
        "Dark Theme",          // 359
        "Light Theme",           // 360

        // extra notifications screen
        "Always",              // 361
        "Never",                // 362

        // extra map screen
        "Markers",           // 363
        "Map",                 // 364
        "Map Type",         // 365
        "Mapnik, etc.",         // 366
        "Map Theme",        // 367
        "Light, Dark, Neon, etc.",    // 368

        // extra feedback screen
        "Send",               // 369

        // extra contact screen
        "Contact Us",          // 370
        "Name",               // 371
        "Write down your issue",   // 372
        "Send",               // 373

    )

    fun languageType(): List<String> {
        if (idioma == 0) return es
        return en
    }



}