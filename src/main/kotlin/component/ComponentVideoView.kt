package component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import classes.UsbDrives
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import media_player.OnTimeChangedListener
import media_player.VideoPlayer
import media_player.VideoPlayerView
import media_player.rememberVideoPlayerState
import java.io.File

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun ComponentVideoView(video: File?, usbDrives: UsbDrives) {
    val videoPlayerState = rememberVideoPlayerState()

    val videoFile = usbDrives.selectedItemList.value.toString()

    Surface(modifier = Modifier.fillMaxSize()
        .background(Color.Transparent),
        shape = RoundedCornerShape(14.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            if(video!!.exists()){
                VideoPlayerView(
                    mrl = videoFile,
                    state = videoPlayerState,
                    modifier = Modifier,
                    viewThumbnail = true
                )

                LaunchedEffect(videoFile) {
                    GlobalScope.launch(Dispatchers.IO){
                        Thread.currentThread().join()
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