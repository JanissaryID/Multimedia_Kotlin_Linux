package classes

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import javafx.embed.swing.JFXPanel
import javafx.scene.Scene
import javafx.scene.layout.StackPane
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import media_player.rememberVideoPlayerState
import model.FileDrive
import uk.co.caprica.vlcj.factory.MediaPlayerFactory
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer
import view.image.ImageLoadData
import java.awt.image.BufferedImage
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.imageio.ImageIO

class UsbDrives {

    var selectedItem : Int by mutableStateOf(0)
    var selectedItemList = MutableStateFlow<File?>(null)
    var statItem : Int by mutableStateOf(0)
//    var selectedItems = mutableStateListOf<File>()

    var listFile = mutableStateListOf<File>()
    var listFileMusic = mutableStateListOf<File>()
    var listFileImage = mutableStateListOf<File>()
    var listFileVideos = mutableStateListOf<File>()

    var selectedTitleItem : String by mutableStateOf("")
    var selectedFormatItem : String by mutableStateOf("")
    var selectedSizeItem : Long by mutableStateOf(0)
    var selectedDateItem : String by mutableStateOf("")
    var selectedDurationItem : Long by mutableStateOf(0L)

    var jfxPanel: JFXPanel = JFXPanel()
    val factory = MediaPlayerFactory("--no-video-title-show")
    val mediaPlayer: EmbeddedMediaPlayer = factory.mediaPlayers().newEmbeddedMediaPlayer()
    val audioPlayer: AudioPlayerComponent = AudioPlayerComponent()

    val root = StackPane()
    val scene = Scene(root)

    var itemFocus : Int by mutableStateOf(0)
    var focus : Boolean by mutableStateOf(true)
    var fullScreen : Boolean by mutableStateOf(false)

    fun GetValueItem(menu: Int){
        mediaPlayer.controls().stop()
        audioPlayer.mediaPlayer().controls().stop()
        when(menu){
            0 -> {
                selectedItemList.value = listFileImage[selectedItem]
                ColectDataInformation(menu = menu)
            }
            1 -> {
                selectedItemList.value = listFileVideos[selectedItem]
                ColectDataInformation(menu = menu)
//                DataVideo()
            }
            2 -> {
                selectedItemList.value = listFileMusic[selectedItem]
                ColectDataInformation(menu = menu)
            }
        }
    }

    fun StopVideo(){
        mediaPlayer.controls().stop()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun FileInDrive(myPathDrive: File): FileDrive{

        var listFileAll: ArrayList<File> = arrayListOf()
        val listMusic: ArrayList<File> = arrayListOf()
        val listImage: ArrayList<File> = arrayListOf()
        val listVideos: ArrayList<File> = arrayListOf()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                if (myPathDrive.exists() && myPathDrive.isDirectory) {
                    println("Drive $myPathDrive found.")
                    FileDriveRecursive(myPathDrive, listMusic, listImage, listVideos)
                    statUsb()
                } else {
                    println("Drive $myPathDrive not found or not a directory.")
                }
            }
            catch (e: Exception){
                println("Error Get File $e")
            }
        }

        return FileDrive(listFileAll,listMusic,listImage,listVideos)
    }

    private fun FileDriveRecursive(directory: File, musics: ArrayList<File>, images: ArrayList<File>, videos: ArrayList<File>){

        if (directory.isDirectory) {
            val files = directory.listFiles()
            if (files != null) {
                for (file in files) {
                    if (file.isDirectory) {
                        FileDriveRecursive(file, musics, images, videos)
                    } else {
                        if (isImageFile(file)) {
                            images.add(file)
                            listFileImage = images.toMutableStateList()
                        }
                        if (isMusicFile(file)) {
                            musics.add(file)
                            listFileMusic = musics.toMutableStateList()
                        }
                        if (isVideoFile(file)) {
                            videos.add(file)
                            listFileVideos = videos.toMutableStateList()
                        }
                    }
                }
            }
        }
    }

    private fun isImageFile(file: File): Boolean {
        val lastDotIndex: Int = file.name.lastIndexOf(".")
        if (lastDotIndex > 0 && lastDotIndex < file.name.length - 1) {
            val fileExtension: String = file.name.substring(lastDotIndex + 1).lowercase(Locale.getDefault())

            return fileExtension == "jpg" || fileExtension == "png" || fileExtension == "jpeg"
        }
        return false
    }

    private fun isMusicFile(file: File): Boolean {
        val lastDotIndex: Int = file.name.lastIndexOf(".")
        if (lastDotIndex > 0 && lastDotIndex < file.name.length - 1) {
            val fileExtension: String = file.name.substring(lastDotIndex + 1).lowercase(Locale.getDefault())

            return fileExtension == "mp3" || fileExtension == "wav" || fileExtension == "ogg"
        }
        return false
    }

    private fun isVideoFile(file: File): Boolean {
        val lastDotIndex: Int = file.name.lastIndexOf(".")
        if (lastDotIndex > 0 && lastDotIndex < file.name.length - 1) {
            val fileExtension: String = file.name.substring(lastDotIndex + 1).lowercase(Locale.getDefault())

            return fileExtension == "mp4" || fileExtension == "avi" || fileExtension == "mkv"
        }
        return false
    }

    fun readImageFromFile(path: String): BufferedImage? {
        return try {
            ImageIO.read(File(path))
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun statUsb(){
        statItem = if(listFileImage.isNotEmpty()){
            1
        } else{
            0
        }

        statItem = if(listFileVideos.isNotEmpty()){
            1
        } else{
            0
        }

        statItem = if(listFileMusic.isNotEmpty()){
            1
        } else{
            0
        }

        println("--Images--")
        listFileImage.forEach{
            println(it)
        }

        println("--Videos--")
        listFileVideos.forEach{
            println(it)
        }

        println("--Music--")
        listFileMusic.forEach{
            println(it)
        }
    }

    fun ColectDataInformation(menu: Int){
        mediaPlayer.controls().stop()
        when(menu){
            0 -> {
                DataImage()
            }
            1 -> {
                ShowVideo()
                DataVideo()
            }
            2 -> {
                PlayMusic()
                DataMusic()
            }
        }
    }

    private fun ShowVideo(){
        jfxPanel.scene = scene
    }

    private fun PlayMusic(){
        audioPlayer.mediaPlayer().controls().stop()
        audioPlayer.mediaPlayer().media().startPaused(selectedItemList.value.toString())
        audioPlayer.mediaPlayer().controls().play()
    }

    private fun DataImage(){
        selectedTitleItem = getTitleFromPath(selectedItemList.value.toString())!!
        selectedFormatItem = getFileFormat(selectedItemList.value.toString())
        selectedSizeItem = getFileSize(selectedItemList.value.toString())
        selectedDateItem = getFileCreationDate(selectedItemList.value.toString())!!
    }

    private fun DataVideo(){
        selectedTitleItem = getTitleFromPath(selectedItemList.value.toString())!!
        selectedFormatItem = getFileFormat(selectedItemList.value.toString())
        selectedSizeItem = getFileSize(selectedItemList.value.toString())
        selectedDateItem = getFileCreationDate(selectedItemList.value.toString())!!
        selectedDurationItem = if(mediaPlayer.media().info() != null) mediaPlayer.media().info().duration() else 0L
    }

    private fun DataMusic(){
        selectedTitleItem = getTitleFromPath(selectedItemList.value.toString())!!
        selectedFormatItem = getFileFormat(selectedItemList.value.toString())
        selectedSizeItem = getFileSize(selectedItemList.value.toString())
        selectedDateItem = getFileCreationDate(selectedItemList.value.toString())!!
        selectedDurationItem = audioPlayer.mediaPlayer().media().info().duration()
        println(audioPlayer.mediaPlayer().media().info().duration())
    }

    private fun getTitleFromPath(filePath: String): String? {
        val file = File(filePath)
        return file.nameWithoutExtension.replace("%20", " ")
    }

    fun formatDuration(milliseconds: Long): String {
        val totalSeconds = milliseconds / 1000
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    fun getFileSize(filePath: String): Long {
        val file = File(filePath)
        if (!file.exists() || !file.isFile()) {
            throw IllegalArgumentException("File does not exist or is not a regular file")
        }

        return file.length()
    }

    fun getFileFormat(filePath: String): String {
        val lastDotIndex = filePath.lastIndexOf('.')
        return if (lastDotIndex >= 0) {
            filePath.substring(lastDotIndex + 1)
        } else {
            ""
        }
    }

    fun getFileCreationDate(filePath: String): String? {
        val file = File(filePath)
        if (!file.exists() || !file.isFile()) {
            throw IllegalArgumentException("File does not exist or is not a regular file")
        }

        val date = Date(file.lastModified())
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        return dateFormat.format(date)
    }

    fun formatSize(size: Long): String {
        if (size <= 0) return "0 B"

        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val digitGroups = (Math.log10(size.toDouble()) / Math.log10(1024.0)).toInt()

        return String.format("%.2f %s", size / Math.pow(1024.0, digitGroups.toDouble()), units[digitGroups])
    }
}