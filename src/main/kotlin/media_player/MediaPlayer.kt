package media_player

import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter
import uk.co.caprica.vlcj.player.base.MediaPlayer

class MediaPlayer(private val mediaPlayer: MediaPlayer) {
    fun play() {
        mediaPlayer.controls().play()
    }
    fun pause() {
        mediaPlayer.controls().pause()
    }
    val isPlaying: Boolean
        get() = mediaPlayer.status().isPlaying

    fun setRate(rate: Float) {
        mediaPlayer.controls().setRate(rate)
    }
    fun setTime(millis: Long) {
        mediaPlayer.controls().setTime(millis)
    }

    fun setTimeAccurate(millis: Long) {
        mediaPlayer.controls().setTime(millis)
    }

    fun getTimeMillis(): Long {
        return mediaPlayer.status().time()
    }

    fun getLengthMillis(): Long {
        return mediaPlayer.status().length()
    }

    fun addOnTimeChangedListener(listener: OnTimeChangedListener) {
        mediaPlayer.events().addMediaPlayerEventListener(object : MediaPlayerEventAdapter() {
            override fun timeChanged(mediaPlayer: MediaPlayer?, newTime: Long) {
                super.timeChanged(mediaPlayer, newTime)
                listener.onTimeChanged(newTime)
            }
        })
    }

    fun dispose() {
        mediaPlayer.release()
    }
}

interface OnTimeChangedListener {
    fun onTimeChanged(timeMillis: Long)
}