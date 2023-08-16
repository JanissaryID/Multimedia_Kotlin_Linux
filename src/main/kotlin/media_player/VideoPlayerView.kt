package media_player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.ColorAlphaType
import org.jetbrains.skia.ImageInfo
import uk.co.caprica.vlcj.factory.MediaPlayerFactory
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter
import uk.co.caprica.vlcj.player.embedded.videosurface.CallbackVideoSurface
import uk.co.caprica.vlcj.player.embedded.videosurface.VideoSurfaceAdapters
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.BufferFormat
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.BufferFormatCallback
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.RenderCallback
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.format.RV32BufferFormat
import java.nio.ByteBuffer


@Composable
fun VideoPlayerView(
    mrl: String,
    viewThumbnail: Boolean = false,
    state: VideoPlayerState,
    modifier: Modifier,
) {
    var imageBitmap by remember(mrl) { mutableStateOf<ImageBitmap?>(null) }
    var mediaPlayerRead by remember(mrl) { mutableStateOf(false) }


    if(mediaPlayerRead) {
        imageBitmap?.let {
            androidx.compose.foundation.Image(
                bitmap = it,
                contentDescription = "Video",
                modifier = modifier,
                contentScale = ContentScale.Inside

            )
        } ?: run {
            Box(modifier = modifier.background(Color.Transparent).fillMaxSize())
        }
    } else {
        Box(modifier = modifier.background(Color.Transparent).fillMaxSize())
    }

    val mediaPlayer = remember(mrl) {
        var byteArray :ByteArray? = null
        var info: ImageInfo? = null

        val factory = MediaPlayerFactory("--no-audio")

        val embeddedMediaPlayer = factory.mediaPlayers().newEmbeddedMediaPlayer()
        val callbackVideoSurface = CallbackVideoSurface(
            object : BufferFormatCallback {
                override fun getBufferFormat(sourceWidth: Int, sourceHeight: Int): BufferFormat {
                    info = ImageInfo.makeN32(sourceWidth, sourceHeight, ColorAlphaType.OPAQUE)
                    return RV32BufferFormat(sourceWidth, sourceHeight)
                }

                override fun allocatedBuffers(buffers: Array<out ByteBuffer>) {
                    byteArray =  ByteArray(buffers[0].limit())
                }
            },
            object : RenderCallback {
                var pos: Float = -1f

                override fun display(
                    mediaPlayer: MediaPlayer,
                    nativeBuffers: Array<out ByteBuffer>,
                    bufferFormat: BufferFormat?
                ) {
                    if(!mediaPlayer.status().isPlaying && pos == mediaPlayer.status().position()) {
                        return
                    }
                    pos = mediaPlayer.status().position()

                    val byteBuffer = nativeBuffers[0]

                    byteBuffer.get(byteArray)
                    byteBuffer.rewind()

                    val bmp = Bitmap()
                    bmp.allocPixels(info!!)
                    bmp.installPixels(byteArray)
                    imageBitmap = bmp.asComposeImageBitmap()
                }
            },
            true,
            VideoSurfaceAdapters.getVideoSurfaceAdapter(),
        )
        embeddedMediaPlayer.videoSurface().set(callbackVideoSurface)
        embeddedMediaPlayer
    }


    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = mrl) {
        println("Launched effect")

        if(viewThumbnail){
            mediaPlayer.media().play(mrl, ":start-time=100")
        }
        else{
            mediaPlayer.media().play(mrl)
        }

        mediaPlayer.events().addMediaPlayerEventListener(object : MediaPlayerEventAdapter() {

            override fun timeChanged(mediaPlayer: MediaPlayer?, newTime: Long) {
                super.timeChanged(mediaPlayer, newTime)
                println("timeChanged $newTime")
            }

            override fun mediaPlayerReady(mediaPlayer: MediaPlayer) {
                super.mediaPlayerReady(mediaPlayer)

                println("mediaPlayerReady ${mediaPlayer.video().videoDimension().width} ${mediaPlayer.video().videoDimension().height}")

                mediaPlayer.submit {
                    mediaPlayer.controls().pause()
                    coroutineScope.launch {
                        delay(100)
                        mediaPlayerRead = true
                        state.onMediaPlayerReady(mediaPlayer)
                    }
                }
                println("mediaPlayerRe")
            }
        })
    }

    DisposableEffect(key1 = mrl, effect = {
        this.onDispose {
            mediaPlayer.release()
        }
    })
}