package navcontroller

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(
    val label: String,
    val icon: ImageVector
) {
    ScreenHome(
        label = "Home",
        icon = Icons.Filled.Home
    ),
    ScreenDrive(
        label = "Drive",
        icon = Icons.Filled.Notifications
    ),
}