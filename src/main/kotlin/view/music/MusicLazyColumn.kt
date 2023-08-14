package view.music

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import classes.MenuNavigation
import classes.UsbDrives
import component.ComponentListItem
import navcontroller.NavController
import java.io.File

@Composable
fun MusicLazyColumn(navController: NavController, usbDrives: UsbDrives, music: List<File>, menuNavigation: MenuNavigation) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(vertical = 36.dp, horizontal = 36.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(items = music){ index, music ->
            ComponentListItem(icon = "musics.svg", title = music.name, usbDrives = usbDrives, index = index){
                usbDrives.selectedItem = index
                usbDrives.selectedItemList.value = File(usbDrives.listFileMusic[index].path)
                usbDrives.GetValueItem(menu = menuNavigation.menuIndex)
                println(usbDrives.selectedItemList.value)

//                usbDrives.ColectDataInformation(menu = 0)
//                usbDrives.selectedItem = index
//                usbDrives.selectedItemList.value = File(usbDrives.listFileImage[index].path)
//                println(usbDrives.selectedItemList.value)
            }
        }
    }
}