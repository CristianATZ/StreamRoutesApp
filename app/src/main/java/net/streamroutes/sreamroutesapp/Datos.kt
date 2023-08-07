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

        // Menu (compartir ubicacion)
        "Comparte tu ubicacion en tiempo real",

        // Menu (compartir aplicacion)
        "Comparte la aplicacion con tus amigos",

        //TripScreen
        "Planifica tu viaje",
        "Ubicación seleccionada",
        "Ubicacion",
        "Lugares seleccionados",
        "Colonia",
        "CP",
        "Plan guardado a",
        "ubicacion(es)",
        "PLANEAR",

        //ValoranoScreen
        "Valoranos",
        "¡Calificanos!",
        "Haznos saber tu conformidad con la aplicación, " +
                "tu opinión es muy importante para nosotros. " +
                "Saber tu conformidad en una escala nos ayuda " +
                "a mejorar la aplicación y lanzar una mejor " +
                "interfaz para el usuario.",
        "ENVIAR",

        // ConfigurationScreen
        "Configuracion",
        "Cambiar de ciudad",
        "Notificaciones",
        "Mapa",
        "Privacidad",

        //ChangeCityScreen
        "Cambiar Ciudad",

        //NotificationScreen
        "Notificaciones",
        "Notificaciones push",
        "Tipo de notificacion",
        "Siempre",
        "Durante",
        "Nunca",
        "Noticias de la aplicacion",
        "Nuevas versiones, promociones, etc.",
        "Alertas",
        "Cambios de horarios, rutas, etc.",
        "Suscripcion",
        "Expiracion de la suscripcion, etc.",

        //MapScreen
        "Mapa",
        "Marcadores de paradas",
        "Indica donde son las paradas.",
        "Marcadores de terminales",
        "Indica estaciones de autobuses.",
        "Marcadores de ubicacion act.",
        "Mira tu ubicacion actual.",
        "Restaurantes, puestos de comida, etc.",
        "Marcadores de restaurantes, etc.",
        "Salud",
        "Hospitales, farmacias, consultorios, etc.",

        //PrivacityScreeen
        "Privacidad",
        "Localizacion",
        "Podremos sugerirte mejores rutas en base a tu ubicacion. Permite este servicio incluso cuando la app no este en uso.",
        "Anuncios personalizados",
        "Podremos usar tus datos para mostrarte anuncios que podrian ser releantes para ti. Si esta opcion no esta marcada se mostraran anuncios genericos.",
        "Rutas personalizadas",
        "Usar tus rutas frecuentes para generarte un plan de viaje personalizado para ti. Si esta opcion no esta marcada no se mostratan estrategias de viajes personaliszadas.",
        "Pago de suscripcion",
        "Si esta opcion esta marcada se permitira el cobro automatico de la suscripcion cada mes.",

        //HelpScreen
        "Ayuda",
        "Comentarios",
        "¿Sugerencias?",
        "Contactanos",
        "Cuentanos tu experiencia.",
        "Acerca de",
        "Version de la aplicacion.",

        //HelpCommentScreen
        "Comentarios",
        "Cuentanos como podemos ayudarte",
        "¿Deseas incluir información de la cuenta? (Opcional)",
        "Si incluyes detalles de la cuenta en tu comentario,\n" +
                "        como correo, etc. Sera mas facil para nosotros responderte",
        "ENVIAR",
        "Se tomara en cuenta tus sugerencias para futuras versiones\n" +
                "    de la aplicación",

        //HelpContactScreen
        "Contactanos",
        "Nombre",
        "Nuestro correo",
        "Mensaje",
        "ENVIAR",
        "ITSUR, Uriangato, Gto.",
        "¿Como llegar?",

        //HelpAboutScreen
        "Acerca de",
        "Version",
        "Stream Routes. ® Todos los derechos reservados 2023-2024.",

        // login screen
        "Telefono",
        "Contraseña",
        "Olvide mi",
        "contraseña",
        "INGRESAR",
        "¿No tienes cuenta?",
        "Registrate",

        // registration screen
        "Registrate",
        "Telefono",
        "Contraseña",
        "Contraseña",
        "REGISTRARSE",

        // verification screen
        "Olvide mi contraseña",
        "Telefono",
        "Codigo enviado",
        "Ingresa un telefono valido",
        "ENVIAR",
        "Codgio de verificacion",
        "Codigo",
        "Codigo incorrecto",
        "VERIFICAR",

        // changeScreen
        "Cambiar Contraseña",
        "Contraseña",
        "Confirmar contraseña",
        "Contraseña",
        "CAMBIAR",
    )

    // lista de idioma EN
    val en = listOf(
        ""
    )

    fun languageType(): List<String> {
        if (idioma == 0) return es
        return en
    }



}