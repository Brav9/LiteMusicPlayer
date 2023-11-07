package com.khalbro.litemusicplayer.java.com.khalbro.litemusicplayer

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.khalbro.litemusicplayer.R

const val CHANNEL_ID = "ID"
const val CHANNEL_NAME = "NAME"

class MusicService : Service() {

    private var player: MediaPlayer? = null
    private var isPlaying: Boolean = false
    private val songsStorage = SongsStorage()
    private var currentTrackPosition: Int = 0
    private var tracks: List<String> = emptyList<String>()
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        tracks = songsStorage.loadTracks(assets)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
            Actions.NEXT_MUSIC.toString() -> nextMusic()
            Actions.PLAY_STOP_MUSIC.toString() -> playStopMusic()
            Actions.PREVIOUS_MUSIC.toString() -> previousMusic()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun previousMusic() {
        val newPosition = currentTrackPosition - 1
        currentTrackPosition = if (newPosition < 0) {
            2
        } else {
            newPosition
        }
        if (isPlaying) {
            if (player != null) {
                player!!.pause()
                isPlaying = false
                playStopMusic()
//                songTitle()
            } else {
                isPlaying = true
//                songTitle()
            }
        }
    }

    private fun playStopMusic() {
        if (isPlaying) {
            if (player != null) {
                player!!.pause()
                isPlaying = false
            }
        } else {
            when (tracks[currentTrackPosition]) {
                "game_of_thrones.mp3" -> {
                    player = MediaPlayer.create(this, R.raw.game_of_thrones)
                    player!!.start()
                    isPlaying = true
                }

                "imperial_marsh.mp3" -> {
                    player = MediaPlayer.create(this, R.raw.imperial_marsh)
                    player!!.start()
                    isPlaying = true
                }

                "harry_potter.mp3" -> {
                    player = MediaPlayer.create(this, R.raw.harry_potter)
                    player!!.start()
                    isPlaying = true
                }
            }
        }
    }

    private fun nextMusic() {
        val newPosition = currentTrackPosition + 1
        currentTrackPosition = if (newPosition > tracks.size - 1) {
            0
        } else {
            newPosition
        }
        if (isPlaying) {
            if (player != null) {
                player!!.pause()
                isPlaying = false
                playStopMusic()
            } else {
                isPlaying = true
            }
        }
    }

    private fun start() {

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Lite Music Player")
            .setContentText("Playing track")
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .addAction(R.drawable.ic_previous, "Previous", previousAction())
            .addAction(R.drawable.ic_pause, "Pause", playStopAction())
            .addAction(R.drawable.ic_next, "Next", nextAction())
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(0, 1, 2)
            )
            .build()
        startForeground(1, notification)
    }

    enum class Actions {
        START, STOP, PLAY_STOP_MUSIC, NEXT_MUSIC, PREVIOUS_MUSIC
    }

//    fun songTitle(): String {
//        return tracks[currentTrackPosition]
//    }
//
//    fun songCover(): Int {
//        return when (tracks[currentTrackPosition]) {
//            "game_of_thrones.mp3" -> R.drawable.game_of_thrones
//            "imperial_marsh.mp3" -> R.drawable.imperial_marsh
//            "harry_potter.mp3" -> R.drawable.harry_potter
//            else -> {
//                R.drawable.game_of_thrones
//            }
//        }
//    }

    private fun playStopAction(): PendingIntent? {
        val playStopIntent = Intent(this, MusicService::class.java)
        playStopIntent.action = Actions.PLAY_STOP_MUSIC.toString()
        return PendingIntent.getService(
            this,
            0,
            playStopIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun previousAction(): PendingIntent? {
        val playStopIntent = Intent(this, MusicService::class.java)
        playStopIntent.action = Actions.PREVIOUS_MUSIC.toString()
        return PendingIntent.getService(
            this,
            0,
            playStopIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun nextAction(): PendingIntent? {
        val playStopIntent = Intent(this, MusicService::class.java)
        playStopIntent.action = Actions.NEXT_MUSIC.toString()
        return PendingIntent.getService(
            this,
            0,
            playStopIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }
}






