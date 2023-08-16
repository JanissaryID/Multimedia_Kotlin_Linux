package component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import classes.UsbDrives
import component.jfx.JFXPanelVideoShow
import javafx.scene.text.Text
import kotlinx.coroutines.flow.MutableStateFlow
import media_player.OnTimeChangedListener
import media_player.VideoPlayer
import media_player.rememberVideoPlayerState
import page.BottomPageVideo
import java.io.File

@Composable
fun ComponentVideoShow(video: File?, usbDrives: UsbDrives) {

    val videoPlayerState = rememberVideoPlayerState()

    val videoFile = usbDrives.selectedItemList.value.toString()

    Surface(modifier = Modifier.fillMaxSize()
        .background(Color.Transparent),
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            if(video!!.exists()){
//                JFXPanelVideoShow(usbDrives = usbDrives)

                VideoPlayer(
                    mrl = videoFile,
                    state = videoPlayerState,
                    modifier = Modifier
                )

                LaunchedEffect(videoFile) {
                    videoPlayerState.doWithMediaPlayer { mediaPlayer ->
                        mediaPlayer.addOnTimeChangedListener(
                            object : OnTimeChangedListener {
                                override fun onTimeChanged(timeMillis: Long) {
                                }
                            }
                        )
                    }
                }
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