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
import page.BottomPageVideo
import java.io.File

@Composable
fun ScreenVideo(usbDrives: UsbDrives, video: File?) {
    Column(modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {
        Box(modifier = Modifier.wrapContentSize().weight(10f), contentAlignment = Alignment.Center){
            ComponentVideoShow(video = video, usbDrives = usbDrives)
        }
//        if(!usbDrives.fullScreen){
//            Box(modifier = Modifier.wrapContentSize().background(Color.DarkGray).weight(0.5f), contentAlignment = Alignment.Center){
//                BottomPageVideo(usbDrives = usbDrives)
//            }
//        }
    }
}