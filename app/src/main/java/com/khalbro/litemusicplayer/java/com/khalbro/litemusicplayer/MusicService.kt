package com.khalbro.litemusicplayer.java.com.khalbro.litemusicplayer

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.session.MediaSession
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.khalbro.litemusicplayer.R

class MusicService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
K
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
            Actions.NEXT_MUSIC.toString() -> nextMusic()
            Actions.START_MUSIC.toString() -> startMusic()
            Actions.STOP_MUSIC.toString() -> stopMusic()
            Actions.PREVIOUS_MUSIC.toString() -> previousMusic()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun previousMusic() {
        TODO("Not yet implemented")
    }

    private fun stopMusic() {
        TODO("Not yet implemented")
    }

    private fun startMusic() {
        TODO("Not yet implemented")
    }

    private fun nextMusic() {
        TODO("Not yet implemented")
    }

    private fun start() {

        val intent = Intent(this, MusicService::class.java)
        intent.action = R.drawable.ic_next.toString()
        val nextPendingIntent = PendingIntent.getService(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val prevPendingIntent = PendingIntent.getService(
            this,
            1,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val pausePendingIntent = PendingIntent.getService(
            this,
            2,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Lite Music Player")
            .setContentText("Playing track")
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .addAction(R.drawable.ic_previous, "Previous", prevPendingIntent)
            .addAction(R.drawable.ic_pause, "Pause", pausePendingIntent)
            .addAction(R.drawable.ic_next, "Next", nextPendingIntent)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle()
                .setShowActionsInCompactView(0, 1, 2))
            .build()
        startForeground(1, notification)
    }

    enum class Actions {
        START, STOP, START_MUSIC, STOP_MUSIC, NEXT_MUSIC, PREVIOUS_MUSIC
    }
}




