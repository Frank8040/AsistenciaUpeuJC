package pe.edu.upeu.asistenciaupeujc.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Pantalla_01 : Destinations( "pantalla1", "Pantalla 1", Icons.Filled.Home )
    object Pantalla_02 : Destinations("pantalla2/?newText={newText}", "Pantalla 2", Icons.Filled.Settings) {
        fun createRoute(newText: String) = "pantalla2/?newText=$newText"
    }
    object Pantalla_03 : Destinations("pantalla3", "Pantalla 3", Icons.Filled.Favorite)
    object Pantalla_04 : Destinations("pantalla4", "Pantalla 4x", Icons.Filled.Face )
}