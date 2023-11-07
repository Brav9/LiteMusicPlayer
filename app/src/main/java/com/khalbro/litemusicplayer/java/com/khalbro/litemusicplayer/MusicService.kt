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
    private val playerController = PlayerController

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> update()
            Actions.STOP.toString() -> stopSelf()
            Actions.NEXT_MUSIC.toString() -> nextMusic()
            Actions.PLAY_STOP_MUSIC.toString() -> playStopMusic()
            Actions.PREVIOUS_MUSIC.toString() -> previousMusic()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun previousMusic() {
        playerController.previousMusicIndex()
        playerController.getSongTitle()
        playerController.getSongCover()
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

    private fun playStopMusic() {

        if (isPlaying) {
            if (player != null) {
                player!!.pause()
                isPlaying = false
                playStopAction()
            }
        } else {
            player = MediaPlayer()
            when (playerController.selectMusicTrack()) {
                "game_of_thrones.mp3" -> {
                    val afd = assets.openFd("tracks/game_of_thrones.mp3")
                    player!!.setDataSource(
                        afd.fileDescriptor,
                        afd.startOffset,
                        afd.length
                    )
                    afd.close()
                    player!!.prepare()
                    player!!.start()
                    isPlaying = true

                }

                "imperial_marsh.mp3" -> {
                    val afd = assets.openFd("tracks/imperial_marsh.mp3")
                    player!!.setDataSource(
                        afd.fileDescriptor,
                        afd.startOffset,
                        afd.length
                    )
                    afd.close()
                    player!!.prepare()
                    player!!.start()
                    isPlaying = true
                }

                "harry_potter.mp3" -> {
                    val afd = assets.openFd("tracks/harry_potter.mp3")
                    player!!.setDataSource(
                        afd.fileDescriptor,
                        afd.startOffset,
                        afd.length
                    )
                    afd.close()
                    player!!.prepare()
                    player!!.start()
                    isPlaying = true
                }
            }
        }
    }

    private fun nextMusic() {
        playerController.nextMusicIndex()

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

    private fun update() {

        val playStopIcon: Int = if (isPlaying) {
            R.drawable.ic_pause
        } else {
            R.drawable.ic_play
        }
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Lite Music Player")
            .setContentText("Playing track")
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .addAction(R.drawable.ic_previous, "Previous", previousAction())
            .addAction(playStopIcon, "Pause", playStopAction())
            .addAction(R.drawable.ic_next, "Next", nextAction())
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(0, 1, 2)
            )
            .build()
        startForeground(1, notification)
        playerController.getSongCover()
    }

    enum class Actions {
        START, STOP, PLAY_STOP_MUSIC, NEXT_MUSIC, PREVIOUS_MUSIC
    }

    private fun playStopAction(): PendingIntent? {
        val playStopIntent = Intent(this, MusicService::class.java)
        playStopIntent.action = Actions.PLAY_STOP_MUSIC.toString()
        return PendingIntent.getService(
            this,
            0,
            playStopIntent,
            PendingIntent.FLAG_MUTABLE
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

    private fun playStopIcon(isPlaying: Boolean): Int {
        val icon: Int = if (!isPlaying) {
            R.drawable.ic_pause

        } else {
            R.drawable.ic_play
        }
        return icon
    }
}






