package com.mohamed.safenest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mohamed.safenest.ui.MainScreen
import com.mohamed.safenest.ui.screens.alertscreen.AlertsScreen
import com.mohamed.safenest.ui.screens.home.HomeScreen
import com.mohamed.safenest.ui.screens.login.LoginScreen
import com.mohamed.safenest.ui.screens.onboarding.OnboardingScreen
import com.mohamed.safenest.ui.screens.register.RegisterScreen
import com.mohamed.safenest.ui.screens.splash.SplashScreen


object Route {
    const val SPLASH="splashScreen"
    const val ONBOARDING_SCREEN ="onboardingScreen"
    const val MAIN_SCREEN ="mainScreen"
//    const val TAB_INDEX= "alerts/{tabIndex}"
    const val ALERTS = "alerts"
    const val HOME_SCREEN="homeScreen"
    const val LOGIN_SCREEN="LoginScreen"
    const val REGISTER_SCREEN="RegisterScreen"
    const val FORGET_PASSWORD_SCREEN="ForgetPasswordScreen"
}


@Composable
fun Nav() {
    val navController= rememberNavController()

    NavHost(navController=navController,startDestination = Route.SPLASH) {
        composable(route = Route.SPLASH) { SplashScreen(navController = navController) }
        composable(route= Route.ONBOARDING_SCREEN) { OnboardingScreen(navController = navController) }
        composable(route= Route.MAIN_SCREEN) { MainScreen(navController = navController) }
        composable(route= Route.HOME_SCREEN) { HomeScreen(navController = navController) }
        composable(route = Route.ALERTS) { AlertsScreen(navController = navController) }
        composable(route = Route.LOGIN_SCREEN) { LoginScreen(navController = navController) }
        composable(Route.REGISTER_SCREEN) {RegisterScreen(navController = navController)}
//        composable(Route.FORGET_PASSWORD_SCREEN) { ForgetPassword(navController = navController) }

        composable(route= "${Route.ALERTS}/{tabIndex}") { backStackEntry->
            val tabIndex=backStackEntry.arguments?.getString("tabIndex")?.toIntOrNull() ?: 0
            AlertsScreen(initialTabIndex  = tabIndex, navController = navController)
        }


    }
}