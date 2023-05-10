package net.streamroutes.sreamroutesapp.Navigation

sealed class AppScreens (val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object MainScreen: AppScreens("main_screen")
    object RegistrationScreen: AppScreens("registration_screen")
    object ForgotScreen: AppScreens("forgot_screen")
    object LoginScreen: AppScreens("login_screen")
}

