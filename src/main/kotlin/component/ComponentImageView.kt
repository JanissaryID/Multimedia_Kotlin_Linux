package component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import classes.UsbDrives
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.awt.image.BufferedImage
import java.io.File

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun ComponentImageView(usbDrives: UsbDrives, image: File?) {
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    Box(modifier = Modifier.fillMaxSize()){
        if(image!!.exists()){
            LaunchedEffect(usbDrives.selectedItem.toString()) {
                GlobalScope.launch(Dispatchers.IO){
                    try {
                        val bufferedImage: BufferedImage? = usbDrives.readImageFromFile(usbDrives.selectedItemList.value.toString())
                        val bitmap: ImageBitmap = bufferedImage!!.toComposeBitmap()
                        imageBitmap = bitmap
                    }
                    catch (_: Exception){

                    }
                }
            }

            imageBitmap?.let {
                Image(
                    bitmap = it,
                    contentDescription = "Image Icon",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
        else{
            Image(
                painter = painterResource("images.svg"),
                contentDescription = "Image Icon",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}