package page

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import classes.MenuNavigation
import classes.UsbDetector
import classes.UsbDrives
import model.FileDrive
import model.UsbDrive
import navcontroller.NavController
import view.image.ImageLoadData
import view.music.MusicLoadData
import view.video.VideoLoadData
import java.io.File

@Composable
fun CenterPage(
    navController: NavController,
    usbDetector: UsbDetector,
    indexUsb: Int,
    menuNavigation: MenuNavigation,
    usbDrives: UsbDrives,
    modifier: Modifier
) {
    val colorPrimary = MaterialTheme.colors.primary

    println("drives = ${usbDetector.listDrive.toList()}")

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {

        Box(modifier = Modifier.height(68.dp).fillMaxWidth().padding(start = 32.dp),contentAlignment = Alignment.CenterStart){
            if(usbDetector.stateDrive == 1){
                Text(text = usbDetector.listDrive[indexUsb].Name.toString(),
                    color = colorPrimary,
                    fontSize = MaterialTheme.typography.h3.fontSize,
                    fontWeight = FontWeight.Bold,
                )
            }
            else{
                Text(text = "Drive Not Found",
                    color = colorPrimary,
                    fontSize = MaterialTheme.typography.h3.fontSize,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxSize().padding(start = 32.dp),contentAlignment = Alignment.CenterStart){
            if(usbDetector.stateDrive == 1){
                when(menuNavigation.menuIndex){
                    0 -> {
                        ImageLoadData(navController = navController, usbDrives = usbDrives, menuNavigation = menuNavigation)
                    }
                    1 -> {
                        VideoLoadData(navController = navController, usbDrives = usbDrives, menuNavigation = menuNavigation)
                    }
                    2 -> {
                        MusicLoadData(navController = navController, usbDrives = usbDrives, menuNavigation = menuNavigation)
                    }
                    3 -> {
//
                    }
                }
            }
        }
    }
}