package navcontroller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import classes.MenuNavigation
import classes.UsbDetector
import classes.UsbDrives
import model.UsbDrive
import screen.ScreenHome
import java.io.File

@Composable
fun CustomNavigationHost(
    navController: NavController,
    usbDetector: UsbDetector,
    menuNavigation: MenuNavigation,
    indexUsb: Int,
    usbDrives: UsbDrives
) {
    NavigationHost(navController) {
        composable(Screen.ScreenHome.name) {
            if(usbDetector.stateDrive == 1){
                usbDrives.FileInDrive(File(usbDetector.listDrive[usbDetector.indexDrive].Directory.toString()))
                ScreenHome(
                    menuNavigation = menuNavigation,
                    usbDetector = usbDetector,
                    indexUsb = indexUsb,
                    navController = navController,
                    usbDrives = usbDrives
                )
            }
        }

        composable(Screen.ScreenDrive.name) {
//            classDrive.FileInDrive()
////            classDrive.ShowFile()
//            ScreenDrive(usbDetector, navController, classDrive)
        }

    }.build()
}