package screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import classes.MenuNavigation
import classes.UsbDetector
import classes.UsbDrives
import component.NavigationMenu
import model.FileDrive
import model.UsbDrive
import navcontroller.NavController
import page.CenterPage
import page.LeftPage
import page.RightPage

@Composable
fun ScreenHome(
    navController: NavController,
    menuNavigation: MenuNavigation,
    usbDetector: UsbDetector,
    indexUsb: Int,
    usbDrives: UsbDrives
) {

    val colorBg = MaterialTheme.colors.background

    val modifier: Modifier = Modifier

    Row(modifier = Modifier.fillMaxSize().background(colorBg)) {
        LeftPage(menuNavigation = menuNavigation, usbDrives = usbDrives)
        CenterPage(
            usbDetector = usbDetector,
            indexUsb = indexUsb,
            navController = navController,
            menuNavigation = menuNavigation,
            usbDrives = usbDrives,
            modifier = modifier.weight(1.8f)
        )
        RightPage(
            usbDetector = usbDetector,
            navController = navController,
            menuNavigation = menuNavigation,
            usbDrives = usbDrives,
            modifier = modifier.weight(1f)
        )
    }
}