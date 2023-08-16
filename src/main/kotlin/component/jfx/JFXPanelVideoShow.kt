package component.jfx

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.round
import classes.jfx_class.JFXVideoShow
import classes.UsbDrives
import page.BottomPageVideo

@Composable
fun JFXPanelVideoShow(usbDrives: UsbDrives) {

    val jPanel = remember { usbDrives.jfxPanel }
    val density = LocalDensity.current.density

    Box(modifier = Modifier.wrapContentSize().background(Color.DarkGray), contentAlignment = Alignment.Center){
        BottomPageVideo(usbDrives = usbDrives)
    }

    SwingPanel(
        factory = {
            JFXVideoShow(usbDrives.selectedItemList.value.toString(),usbDrives.mediaPlayer,usbDrives.factory)
        },
        update = {

        },
        modifier = Modifier.onGloballyPositioned { childCoordinates ->
            val coordinates = childCoordinates.parentCoordinates!!
            val location = coordinates.localToWindow(Offset.Zero).round()
            val size = coordinates.size
            println("Size Jpanel = ${size.height} -- ${size.width} -- ${density}")
            // 1009 -- 1920
            jPanel.setBounds(
                (location.x / density).toInt(),
                (location.y / density).toInt(),
                (size.width / density).toInt(),
                (size.height / density).toInt()
            )
//            jPanel.revalidate()
            jPanel.validate()
            jPanel.repaint()
        },
    )

    DisposableEffect(jPanel) {
        onDispose {
            jPanel.remove(JFXVideoShow(usbDrives.selectedItemList.value.toString(),usbDrives.mediaPlayer,usbDrives.factory))
        }
    }
}