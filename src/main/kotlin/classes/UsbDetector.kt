package classes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import model.UsbDrive
import net.samuelcampos.usbdrivedetector.USBDeviceDetectorManager
import net.samuelcampos.usbdrivedetector.events.DeviceEventType
import net.samuelcampos.usbdrivedetector.events.USBStorageEvent

class UsbDetector {

    var selectedUsbIndex : Int by mutableStateOf(0)

    var listDrive = mutableStateListOf<UsbDrive>()
    var stateDrive : Int by mutableStateOf(0)

    var indexDrive : Int by mutableStateOf(0)
    var focus : Boolean by mutableStateOf(true)

    fun usbDevice(it: USBStorageEvent){
        if(it.eventType == DeviceEventType.CONNECTED){
//            println("Here")
            listDrive.add(UsbDrive(Name = it.storageDevice.deviceName, Directory = it.storageDevice.rootDirectory.toString()))
            println(it.eventType)
            println(it.storageDevice.rootDirectory)
            println(it.storageDevice.device)
        }
        else{
            listDrive.remove(UsbDrive(Name = it.storageDevice.deviceName, Directory = it.storageDevice.rootDirectory.toString()))
            println(it.eventType)
        }

        stateDrive = if(listDrive.isNotEmpty()) 1 else 0
//        println("in list ${listDrive.toList()}")
    }

    private fun GetUsbDrive(){
        val driveDetector = USBDeviceDetectorManager()
//        driveDetector.removableDevices
//
//        println(driveDetector.removableDevices)
        if(listDrive.toList().isEmpty()){
            driveDetector.addDriveListener {
                usbDevice(it = it)
//              dont forget close object
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun asyncCheckUsb(){
        GlobalScope.launch(Dispatchers.IO) {
            while (true){
                try {
                    GetUsbDrive()
//                    println("Async Get Usb Drive")
                    Thread.sleep(1000)
                }
                catch (e: Exception){
                    println("Error async : $e")
                }
            }
        }
    }
}