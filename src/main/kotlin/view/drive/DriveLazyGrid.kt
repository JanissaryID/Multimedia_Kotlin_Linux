package view.drive

//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.unit.dp
//import classes.ClassDrive
//import classes.ClassUsbDetector
//import components.ComponentUsb
//import navcontroller.NavController
//import navcontroller.Screen

//@Composable
//fun DriveLazyGrid(usbDetector: ClassUsbDetector, navController: NavController, classDrive: ClassDrive) {
//    LazyColumn(
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ){
//
//        itemsIndexed(items = usbDetector.listDrives){ index, usb ->
//
//            ComponentUsb(usbDetector = usbDetector, usb = usb, index = index){
//                classDrive.drive = usb
//                usbDetector.selectedIndex = index
//                navController.navigate(Screen.ScreenDrive.name)
//            }
//        }
//    }
//}