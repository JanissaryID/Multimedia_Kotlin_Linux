package page

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import classes.JFXCompose
import classes.MenuNavigation
import classes.UsbDetector
import classes.UsbDrives
import component.ComponentImageView
import component.ComponentMusicView
import component.ComponentVideoView
import navcontroller.NavController
import view.image.ImageLoadData
import view.music.MusicLoadData
import view.video.VideoLoadData
import java.io.File

@Composable
fun RightPage(
    usbDrives: UsbDrives,
    navController: NavController,
    usbDetector: UsbDetector,
    menuNavigation: MenuNavigation,
    modifier: Modifier
) {
    val colorPrimary = MaterialTheme.colors.primary

    val colorBg = MaterialTheme.colors.secondary

    Column(modifier = modifier.fillMaxSize().padding(end = 32.dp, top = 64.dp, bottom = 64.dp)) {

        Box(modifier = Modifier.height(68.dp).fillMaxWidth().padding(start = 32.dp),contentAlignment = Alignment.CenterStart){

        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxSize().padding(start = 32.dp).weight(1f),contentAlignment = Alignment.CenterStart){
            if(usbDetector.stateDrive == 1){
                when(menuNavigation.menuIndex){
                    0 -> {
                        ComponentImageView(usbDrives = usbDrives, image = usbDrives.selectedItemList.value)
                    }
                    1 -> {
                        ComponentVideoView(usbDrives = usbDrives, video = usbDrives.selectedItemList.value)
                    }
                    2 -> {
                        ComponentMusicView()
                    }
                    3 -> {
//
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.fillMaxSize().padding(start = 32.dp).weight(1f)){
            Text(text = usbDrives.selectedTitleItem,
                color = colorBg,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Format ${usbDrives.selectedFormatItem}",
                color = colorBg,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold
            )
            if(menuNavigation.menuIndex != 0){
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Duration ${usbDrives.formatDuration(usbDrives.selectedDurationItem)}",
                    color = colorBg,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Size ${usbDrives.formatSize(usbDrives.selectedSizeItem)}",
                color = colorBg,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Date ${usbDrives.selectedDateItem}",
                color = colorBg,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}