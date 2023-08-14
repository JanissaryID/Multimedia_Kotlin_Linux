package view.image

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
fun ImageLoadData(navController: NavController, usbDrives: UsbDrives, menuNavigation: MenuNavigation) {
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
            }
        }
        1 -> {
            if (usbDrives.listFileImage.isNotEmpty()){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    ImageLazyColumn(navController = navController, images = usbDrives.listFileImage, usbDrives = usbDrives, menuNavigation = menuNavigation)
                }
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