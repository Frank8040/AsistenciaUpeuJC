package pe.edu.upeu.asistenciaupeujc

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujc.ui.navigation.NavigationHost
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.Dialog
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.Drawer
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.TopBar
import pe.edu.upeu.asistenciaupeujc.ui.theme.AsistenciaUpeuJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val darkMode = remember { mutableStateOf(false)
            }
            AsistenciaUpeuJCTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    MainScreen(darkMode =darkMode )
                }
            }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AsistenciaUpeuJCTheme {
        Greeting("Android")
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    darkMode: MutableState<Boolean>,
    //themeType: MutableState<ThemeType>
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val scope = rememberCoroutineScope()
    val openDialog = remember {
        mutableStateOf(false)
    }
    val navigationItems = listOf(
        Destinations.Pantalla_01,
        Destinations.Pantalla_02,
        Destinations.Pantalla_03,
        Destinations.Pantalla_04
    )
    val navigationItems2 = listOf(
        Destinations.Pantalla_01,
        Destinations.Pantalla_02,
        Destinations.Pantalla_03,
    )
    ModalNavigationDrawer(drawerContent = {
            Drawer( scope, drawerState, navController,
                items = navigationItems
            )
        }, drawerState = drawerState) {
//
        Scaffold(
                topBar = {
                        TopBar(
                                scope,
                        drawerState,
                        openDialog = {
                            openDialog.value = true
                        }
                        ,
                        displaySnackBar =
                            {
                                scope.launch {
/*val resultado =
scaffoldState.snackbarHostState.showSnackbar(
message = "Nuevo SnackBar!",
duration = SnackbarDuration.Short,
actionLabel = "Aceptar"
)
when(resultado){
SnackbarResult.ActionPerformed
-> {
Log.d("MainActivity", "Snackbar Accionado")
}
SnackbarResult.Dismissed
-> {
Log.d("MainActivity", "Snackbar Ignorado")
}
}*/
                                }
                            }
                        )
                    }, modifier = Modifier
        )
        {
            NavigationHost(navController, darkMode)
        }
//
    }
    Dialog(showDialog = openDialog.value, dismissDialog = {
        openDialog.value = false
    })
}

