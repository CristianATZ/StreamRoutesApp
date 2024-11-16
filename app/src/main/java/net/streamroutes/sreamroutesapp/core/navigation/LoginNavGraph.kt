package net.streamroutes.sreamroutesapp.core.navigation

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.streamroutes.sreamroutesapp.app.GeneralActivity
import net.streamroutes.sreamroutesapp.features.authentication.presentation.login.LoginScreen
import net.streamroutes.sreamroutesapp.features.authentication.presentation.password.PasswordScreen
import net.streamroutes.sreamroutesapp.features.authentication.presentation.register.RegisterScreen

@Composable
fun LoginNavigation(
    navHostController: NavHostController
) {
    val context = LocalContext.current

    // Definición de animaciones de transición
    val slideInFromLeft = slideInHorizontally(
        initialOffsetX = { -it } // Entra desde la izquierda
    )

    val slideOutToLeft = slideOutHorizontally(
        targetOffsetX = { -it } // Sale hacia la izquierda
    )

    val slideInFromRight = slideInHorizontally(
        initialOffsetX = { it } // Entra desde la derecha
    )

    val slideOutToRight = slideOutHorizontally(
        targetOffsetX = { it } // Sale hacia la derecha
    )

    NavHost(
        navController = navHostController,
        startDestination = Destinations.SignIn.route
    ) {
        // inicio de sesion
        composable(
            route = Destinations.SignIn.route,
            enterTransition = { slideInFromLeft },
            exitTransition = { slideOutToLeft },
            popEnterTransition = { slideInFromLeft },
            popExitTransition = { slideOutToRight }
        ) {
            LoginScreen(
                onSignIn = {
                    val intent = Intent(context, GeneralActivity::class.java)
                    context.startActivity(intent)
                    (context as? ComponentActivity)?.finish()
                },
                onSignUp = {
                    navHostController.navigate(Destinations.SignUp.route) {
                        launchSingleTop = true
                    }
                },
                onChangePassword = {
                    navHostController.navigate(Destinations.ChangePassword.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        // registro de cuenta
        composable(
            route = Destinations.SignUp.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            RegisterScreen(
                onBackSignIn = {
                    navHostController.popBackStack()
                }
            )
        }

        // cambiar contrasenia
        composable(
            route = Destinations.ChangePassword.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            PasswordScreen(
                onBackSignIn = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}