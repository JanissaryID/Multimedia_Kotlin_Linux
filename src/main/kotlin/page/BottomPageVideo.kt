package page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import classes.UsbDrives
import media_player.VideoPlayerState
import navcontroller.NavController
import navcontroller.Screen
import kotlin.math.max
import kotlin.math.min

@Composable
fun BottomPageVideo(usbDrives: UsbDrives, videoPlayerState: VideoPlayerState, navController: NavController) {

    val colorPrimary = MaterialTheme.colors.primary
    val colorBg = MaterialTheme.colors.secondary

    val videoFile = usbDrives.selectedItemList.value.toString()

    var isPlaying by remember(videoFile) { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxWidth().height(50.dp), contentAlignment = Alignment.Center){
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Icon(painter = painterResource("backward.svg"),
                contentDescription = "-10 sec",
                tint = colorBg,
                modifier = Modifier
////                    .focusable(menuNavigation.focus)
//                    .onFocusChanged {
//                        if(it.isFocused){
//                            menuNavigation.menuFocus = index
//                        }
//                    }
                    .size(48.dp).clickable {
                        videoPlayerState.doWithMediaPlayer { mediaPlayer ->
                            mediaPlayer.setTimeAccurate( max(mediaPlayer.getTimeMillis() - 10000, 0) )
                        }
                    }
            )
            Icon(painter = painterResource(if(isPlaying) "pause.svg" else "play.svg"),
                contentDescription = if(isPlaying) "Pause" else "Play",
                tint = colorBg,
                modifier = Modifier
////                    .focusable(menuNavigation.focus)
//                    .onFocusChanged {
//                        if(it.isFocused){
//                            menuNavigation.menuFocus = index
//                        }
//                    }
                    .size(48.dp).clickable {
                        videoPlayerState.doWithMediaPlayer { mediaPlayer ->
                            if(mediaPlayer.isPlaying) {
                                isPlaying = false
                                mediaPlayer.pause()
                            } else {
                                mediaPlayer.play()
                                isPlaying = true
                            }
                        }
                    }
            )
            Icon(painter = painterResource("forward.svg"),
                contentDescription = "+10 sec",
                tint = colorBg,
                modifier = Modifier
////                    .focusable(menuNavigation.focus)
//                    .onFocusChanged {
//                        if(it.isFocused){
//                            menuNavigation.menuFocus = index
//                        }
//                    }
                    .size(48.dp).clickable {
                        videoPlayerState.doWithMediaPlayer { mediaPlayer ->
                            mediaPlayer.setTimeAccurate( min(mediaPlayer.getTimeMillis() + 10000, mediaPlayer.getLengthMillis()) )
                        }
                    }
            )
        }
    }
    Box(modifier = Modifier.fillMaxWidth().height(50.dp), contentAlignment = Alignment.BottomEnd){
        Icon(painter = painterResource("close.svg"),
            contentDescription = "close",
            tint = colorBg,
            modifier = Modifier
////                    .focusable(menuNavigation.focus)
//                    .onFocusChanged {
//                        if(it.isFocused){
//                            menuNavigation.menuFocus = index
//                        }
//                    }
                .size(48.dp).clickable {
//                    videoPlayerState.doWithMediaPlayer { mediaPlayer ->
//                        mediaPlayer.dispose()
//                    }
                    navController.navigate(Screen.ScreenHome.name)
                }
        )
    }
}