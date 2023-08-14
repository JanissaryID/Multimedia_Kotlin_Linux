package component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import classes.UsbDrives

@Composable
fun ComponentListItem(usbDrives: UsbDrives, icon: String, title: String, index:Int, onClick: () -> Unit) {
    val colorPrimary = MaterialTheme.colors.secondary
    val colorBg = MaterialTheme.colors.surface

    Card(modifier = Modifier.size(width = 960.dp, height = 80.dp),
        elevation = 0.dp,
        border = BorderStroke(3.dp, color = colorPrimary),
        shape = RoundedCornerShape(20.dp),
    ) {
        Surface(modifier = Modifier
            .clickable { onClick.invoke() },
            color = if (usbDrives.selectedItem == index) colorPrimary else colorBg
        ) {
            Row(modifier = Modifier
                .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(painter = painterResource(icon),
                    contentDescription = "Icon Item",
                    tint = if (usbDrives.selectedItem == index) colorBg else colorPrimary,
                    modifier = Modifier.size(42.dp)
                )
                Text(text = title,
                    color = if (usbDrives.selectedItem == index) colorBg else colorPrimary,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}