package view.image

//import LOAD_IMAGE
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
import java.io.File
import java.util.ArrayList

@Composable
fun ImageLazyColumn(navController: NavController, images: List<File>, usbDrives: UsbDrives, menuNavigation: MenuNavigation) {

    LazyColumn(modifier = Modifier.fillMaxSize().padding(vertical = 36.dp, horizontal = 36.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(items = images){ index, image ->
            ComponentListItem(
                icon = "images.svg",
                title = image.name,
                usbDrives = usbDrives,
                index = index,
                modifier = Modifier.onFocusChanged {
                    if(it.isFocused){
                        usbDrives.itemFocus = index
                    }
                }){
                usbDrives.selectedItem = index
                usbDrives.selectedItemList.value = File(usbDrives.listFileImage[index].path)
                usbDrives.GetValueItem(menu = menuNavigation.menuIndex)
                println(usbDrives.selectedItemList.value)
            }
        }
    }
}