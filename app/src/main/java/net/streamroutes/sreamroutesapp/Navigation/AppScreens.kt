package net.streamroutes.sreamroutesapp.Navigation

sealed class AppScreens (val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object LoginScreen: AppScreens("login_screen")
    object RegistrationScreen: AppScreens("registration_screen")
    object ForgotScreen: AppScreens("forgot_screen")
    object MainScreen: AppScreens("main_screen")
    object ProfileScreen: AppScreens("profile_screen")
    object ProfileConfigureScreen: AppScreens("profile_configure_screen")
    object ProfileDataInfoScreen: AppScreens("profile_data_info_screen")
    object ProfileVariedInfoScreen: AppScreens("profile_varied_info_screen")
    object ProfileContactInfoScreen: AppScreens("profile_contact_info_screen")
    object ProfileChangePhoneScreen: AppScreens("profile_change_phone_screen")
    object ProfileChangeEmailScreen: AppScreens("profile_change_email_screen")
    object HelpScreen: AppScreens("help_screen")
    object HelpCommentsScreen: AppScreens("help_comments_screen")
    object HelpContactScreen: AppScreens("help_contact_screen")
    object HelpAboutAppScreen: AppScreens("help_    about_screen")
    object CustomerScreen: AppScreens("customer_screen")
}

