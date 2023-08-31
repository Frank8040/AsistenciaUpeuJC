package pe.edu.upeu.asistenciaupeujc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.Pantalla_01
import pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.Pantalla_02
import pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.Pantalla_03
import pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.Pantalla_04

@Composable
fun NavigationHost(
    navController: NavHostController,
    darkMode: MutableState<Boolean>
) {
    NavHost(
        navController = navController, startDestination = Destinations.Pantalla_01.route
    ) {
        composable(Destinations.Pantalla_01.route) {
            Pantalla_01(
                navegarPantalla_02 = { newText ->navController.navigate(Destinations.Pantalla_02.createRoute(newText))
                }
            )
        }
        composable( Destinations.Pantalla_02.route,
            arguments = listOf(navArgument("newText") {
                defaultValue = "Pantalla 2"
            })
        ) { navBackStackEntry ->
            var newText =
                navBackStackEntry.arguments?.getString("newText")
            requireNotNull(newText)
            Pantalla_02(newText, darkMode)
        }
        composable(Destinations.Pantalla_03.route) { Pantalla_03() }
        composable(Destinations.Pantalla_04.route) { Pantalla_04() }
    }
}