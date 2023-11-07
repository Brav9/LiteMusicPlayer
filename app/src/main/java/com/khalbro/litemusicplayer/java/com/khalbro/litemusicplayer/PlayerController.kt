package com.khalbro.litemusicplayer.java.com.khalbro.litemusicplayer

import com.khalbro.litemusicplayer.R

object PlayerController {

    private var currentTrackPosition: Int = 0
    private val songsStorage = SongsStorage

    fun previousMusicIndex() {
        val newPosition = currentTrackPosition - 1
        currentTrackPosition = if (newPosition < 0) {
            2
        } else {
            newPosition
        }
    }

    fun nextMusicIndex() {
        val newPosition = currentTrackPosition + 1
        currentTrackPosition = if (newPosition > songsStorage.tracks.size - 1) {
            0
        } else {
            newPosition
        }
    }

     fun getSongTitle(): String {
        return songsStorage.getSongTitle(currentTrackPosition)
    }

    fun getSongCover(): Int {
        return when (songsStorage.tracks[currentTrackPosition]) {
            "game_of_thrones.mp3" -> R.drawable.game_of_thrones
            "imperial_marsh.mp3" -> R.drawable.imperial_marsh
            "harry_potter.mp3" -> R.drawable.harry_potter
            else -> {
                R.drawable.game_of_thrones
            }
        }
    }

    fun selectMusicTrack(): String {
        return songsStorage.tracks[currentTrackPosition]
    }
}