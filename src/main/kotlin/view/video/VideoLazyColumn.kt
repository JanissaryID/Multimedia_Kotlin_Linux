package view.video

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import classes.MenuNavigation
import classes.UsbDrives
import component.ComponentListItem
import navcontroller.NavController
import navcontroller.Screen
import java.io.File

@Composable
fun VideoLazyColumn(navController: NavController, videos: List<File>, usbDrives: UsbDrives, menuNavigation: MenuNavigation) {

    LazyColumn(modifier = Modifier.fillMaxSize().padding(vertical = 36.dp, horizontal = 36.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(items = videos){ index, video ->
            ComponentListItem(icon = "videos.svg", title = video.name, usbDrives = usbDrives, index = index, modifier = Modifier.onFocusChanged {
                if(it.isFocused){
                    usbDrives.itemFocus = index
                    usbDrives.selectedItem = index
                    usbDrives.selectedItemList.value = File(usbDrives.listFileVideos[index].path)
                    usbDrives.GetValueItem(menu = menuNavigation.menuIndex)
                    println(usbDrives.selectedItemList.value)
                    usbDrives.StopVideo()
                }
            }){
                navController.navigate(Screen.ScreenVideo.name)
                usbDrives.selectedItem = index
                usbDrives.selectedItemList.value = File(usbDrives.listFileVideos[index].path)
                usbDrives.GetValueItem(menu = menuNavigation.menuIndex)
                println(usbDrives.selectedItemList.value)
                usbDrives.StopVideo()
            }
        }
    }
}