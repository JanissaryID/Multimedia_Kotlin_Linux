package component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import classes.JFXCompose
import classes.UsbDrives
import java.io.File

@Composable
fun ComponentVideoView(video: File?, usbDrives: UsbDrives) {
    Surface(modifier = Modifier.fillMaxSize()
        .background(Color.Transparent),
        shape = RoundedCornerShape(14.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            if(video!!.exists()){
                JFXPanelVideo(usbDrives = usbDrives)
            }
            else{
                Image(
                    painter = painterResource("videos.svg"),
                    contentDescription = "Video Icon",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}