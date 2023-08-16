package screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import classes.UsbDrives
import component.ComponentVideoShow
import media_player.rememberVideoPlayerState
import navcontroller.NavController
import page.BottomPageVideo
import java.io.File

@Composable
fun ScreenVideo(usbDrives: UsbDrives, video: File?, navController: NavController) {
    val videoPlayerState = rememberVideoPlayerState()

    Column(modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {
        Box(modifier = Modifier.wrapContentSize().weight(10f), contentAlignment = Alignment.Center){
            ComponentVideoShow(video = video, usbDrives = usbDrives, videoPlayerState = videoPlayerState)
        }
        if(!usbDrives.fullScreen){
            Box(modifier = Modifier.wrapContentSize().background(Color.DarkGray.copy(alpha = 0.6f)).weight(0.5f), contentAlignment = Alignment.BottomCenter){
                BottomPageVideo(usbDrives = usbDrives, videoPlayerState = videoPlayerState, navController = navController)
            }
        }
    }
}