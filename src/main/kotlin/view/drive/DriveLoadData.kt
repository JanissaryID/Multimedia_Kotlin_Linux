package view.drive

//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontWeight
//import classes.ClassDrive
//import classes.ClassUsbDetector
//import navcontroller.NavController
//
//@Composable
//fun DriveLoadData(usbDetector: ClassUsbDetector, navController: NavController, classDrive: ClassDrive) {
//    when (usbDetector.stateDrive) {
//        0 -> {
//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = "Drive Empty",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = MaterialTheme.typography.h1.fontSize,
//                    color = MaterialTheme.colors.secondary,
//                )
//            }
//        }
//        1 -> {
//            if (usbDetector.listDrives.isNotEmpty()){
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.CenterStart
//                ) {
//                    DriveLazyGrid(usbDetector = usbDetector, navController = navController, classDrive = classDrive)
//                }
//            }
//        }
//    }
//}