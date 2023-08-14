package component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.round
import classes.JFXCompose
import classes.JFXVideo
import classes.UsbDrives

@Composable
fun JFXPanelVideo(usbDrives: UsbDrives) {

    val jPanel = remember { usbDrives.jfxPanel }
    val density = LocalDensity.current.density

    SwingPanel(
        factory = { JFXVideo(usbDrives.selectedItemList.value.toString(),usbDrives.mediaPlayer,usbDrives.factory) },
        modifier = Modifier.onGloballyPositioned { childCoordinates ->
            val coordinates = childCoordinates.parentCoordinates!!
            val location = coordinates.localToWindow(Offset.Zero).round()
            val size = coordinates.size
            jPanel.setBounds(
                (location.x / density).toInt(),
                (location.y / density).toInt(),
                (size.width / density).toInt(),
                (size.height / density).toInt()
            )
            jPanel.validate()
            jPanel.repaint()
        },
    )

    DisposableEffect(jPanel) {
        onDispose {
            jPanel.remove(JFXVideo(usbDrives.selectedItemList.value.toString(),usbDrives.mediaPlayer,usbDrives.factory))
        }
    }
}