import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import classes.MenuNavigation
import classes.UsbDetector
import classes.UsbDrives
import navcontroller.CustomNavigationHost
import navcontroller.Screen
import navcontroller.rememberNavController
import java.io.File

@Composable
@Preview
fun App() {

    val usbDetector = UsbDetector()
    usbDetector.asyncCheckUsb()
    val menuNavigation = MenuNavigation()
    val usbDrives = UsbDrives()
    val navController by rememberNavController(Screen.ScreenHome.name)

    MaterialTheme(colors = darkColors()) {
        CustomNavigationHost(navController = navController, usbDetector = usbDetector, indexUsb = usbDetector.selectedUsbIndex, menuNavigation = menuNavigation, usbDrives = usbDrives)
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
