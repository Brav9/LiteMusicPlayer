package com.khalbro.litemusicplayer.java.com.khalbro.litemusicplayer

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import com.khalbro.litemusicplayer.R

class SongsStorage : AppCompatActivity() {

    var tracks = emptyList<String>()

    private val covers = mapOf(
        "game_of_thrones.mp3" to R.drawable.game_of_thrones,
        "imperial_marsh.mp3" to R.drawable.imperial_marsh,
        "harry_potter.mp3" to R.drawable.harry_potter
    )

    fun loadTracks(assetManager: AssetManager): List<String> {
        val files: Array<out String> = assetManager.list("tracks").orEmpty()
        tracks = files.toList()
        return tracks
    }

    fun getSongCover(trackIndex: Int): Int? {
        return covers[getSongTitle(trackIndex)]
    }

     fun getSongTitle(trackIndex: Int): String {
        return tracks[trackIndex]
    }
}