package classes.jfx_class

import javafx.application.Platform
import javafx.embed.swing.JFXPanel
import javafx.scene.Scene
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uk.co.caprica.vlcj.factory.MediaPlayerFactory
import uk.co.caprica.vlcj.javafx.videosurface.ImageViewVideoSurface
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer

class JFXVideoShow(path: String, mediaPlayer: EmbeddedMediaPlayer, mediaPlayerFactory: MediaPlayerFactory): JFXPanel() {

    val myPath: String
    var myMediaPlayer: EmbeddedMediaPlayer? = null
    var myMediaPlayerFactory: MediaPlayerFactory? = null
    init {
        myPath = path
        myMediaPlayer = mediaPlayer
        myMediaPlayerFactory = mediaPlayerFactory
        Platform.runLater(::initialiseJavaFXScene)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun initialiseJavaFXScene() {

        println("JFXVideo Show $myPath")
        val root = StackPane()
        val scene = Scene(root)
        val imageView = ImageView()

        myMediaPlayer!!.controls().stop()

        myMediaPlayer!!.videoSurface().set(ImageViewVideoSurface(imageView))

        root.children.add(imageView)

        scene.fill = javafx.scene.paint.Color.BLACK
        setScene(scene)

        GlobalScope.launch(Dispatchers.IO){
            myMediaPlayer!!.controls().stop()
            myMediaPlayer!!.media().start(myPath)

            Thread.currentThread().join();
            myMediaPlayer!!.release()
            myMediaPlayerFactory!!.release()
        }
    }
}