package page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import classes.UsbDrives

@Composable
fun BottomPageVideo(usbDrives: UsbDrives) {

    val colorPrimary = MaterialTheme.colors.primary
    val colorBg = MaterialTheme.colors.secondary

    Box(modifier = Modifier.fillMaxWidth().height(50.dp), contentAlignment = Alignment.Center){
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Icon(painter = painterResource("prev.svg"),
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
//                        menuNavigation.focus = false
//                        usbDrives.focus = true
//                        usbDrives.selectedItem = 0
//                        menuNavigation.menuIndex = index
//                        usbDrives.GetValueItem(menu = menuNavigation.menuIndex)
                    }
            )
            Icon(painter = painterResource("pause.svg"),
                contentDescription = "Pause",
                tint = colorBg,
                modifier = Modifier
////                    .focusable(menuNavigation.focus)
//                    .onFocusChanged {
//                        if(it.isFocused){
//                            menuNavigation.menuFocus = index
//                        }
//                    }
                    .size(48.dp).clickable {
                        usbDrives.fullScreen = true
//                        menuNavigation.focus = false
//                        usbDrives.focus = true
//                        usbDrives.selectedItem = 0
//                        menuNavigation.menuIndex = index
//                        usbDrives.GetValueItem(menu = menuNavigation.menuIndex)
                    }
            )
            Icon(painter = painterResource("next.svg"),
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
//                        menuNavigation.focus = false
//                        usbDrives.focus = true
//                        usbDrives.selectedItem = 0
//                        menuNavigation.menuIndex = index
//                        usbDrives.GetValueItem(menu = menuNavigation.menuIndex)
                    }
            )
        }
    }
}