package page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import classes.MenuNavigation
import classes.UsbDrives
import component.NavigationMenu

@Composable
fun LeftPage(menuNavigation: MenuNavigation, usbDrives: UsbDrives) {

    val colorPrimary = MaterialTheme.colors.primary

    Column(modifier = Modifier.fillMaxHeight().width(108.dp).padding(16.dp)) {

        Box(modifier = Modifier.wrapContentHeight().fillMaxWidth(),contentAlignment = Alignment.Center){
            Icon(painter = painterResource("usb.svg"),
                contentDescription = "Usb",
                tint = colorPrimary,
                modifier = Modifier.size(68.dp).clickable {

                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            color = colorPrimary,
            modifier = Modifier
                .fillMaxWidth()  //fill the max height
                .height(3.dp)
        )
        Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
            NavigationMenu(menuNavigation = menuNavigation, usbDrives = usbDrives)
        }
    }
}