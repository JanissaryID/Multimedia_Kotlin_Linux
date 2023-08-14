package view.music

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import classes.MenuNavigation
import classes.UsbDrives
import navcontroller.NavController

@Composable
fun MusicLoadData(navController: NavController, usbDrives: UsbDrives, menuNavigation: MenuNavigation) {
    when (usbDrives.statItem) {
        0 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Image Empty",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.h1.fontSize,
                    color = MaterialTheme.colors.secondary,
                )
//                println("Empty")
            }
        }
        1 -> {
            if (usbDrives.listFileMusic.isNotEmpty()){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    MusicLazyColumn(navController = navController, usbDrives = usbDrives, music = usbDrives.listFileMusic, menuNavigation = menuNavigation)
                }
//                println("Not Empty")
            }
        }
        2 ->{
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}