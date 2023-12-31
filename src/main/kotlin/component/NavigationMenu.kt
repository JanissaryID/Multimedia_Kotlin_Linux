package component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import classes.MenuNavigation
import classes.UsbDrives

@Composable
fun NavigationMenu(menuNavigation: MenuNavigation, usbDrives: UsbDrives) {

    val colorPrimary = MaterialTheme.colors.primary
    val colorBg = MaterialTheme.colors.secondary

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(42.dp)
    ){
        itemsIndexed(items = menuNavigation.listMenuIcon){ index, menu ->

            Icon(painter = painterResource(menu),
                contentDescription = menuNavigation.listMenuCaption[index],
                tint = if (menuNavigation.menuFocus == index) colorBg else colorPrimary,
                modifier = Modifier
//                    .focusable(menuNavigation.focus)
                    .onFocusChanged {
                        if(it.isFocused){
                            menuNavigation.menuFocus = index
                        }
                    }
                    .size(if (menuNavigation.menuFocus == index) 88.dp else 68.dp).clickable {
                    menuNavigation.focus = false
                    usbDrives.focus = true
                    usbDrives.selectedItem = 0
                    menuNavigation.menuIndex = index
                    usbDrives.GetValueItem(menu = menuNavigation.menuIndex)
                }
            )
        }
    }
}