package com.khalbro.litemusicplayer.java.com.khalbro.litemusicplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.khalbro.litemusicplayer.R

object PlayerController {


    private var currentTrackPosition: Int = 0
    private val songsStorage = SongsStorage
    var currentTrackLiveData: MutableLiveData<Int> = MutableLiveData()
    var currentSongTitleLiveData: MutableLiveData<String> = MutableLiveData()
    var currentSongCoverLiveData: MutableLiveData<Int> = MutableLiveData()


    fun previousMusicIndex() {
        val newPosition = currentTrackPosition - 1
        currentTrackPosition = if (newPosition < 0) {
            2
        } else {
            newPosition
        }
        currentTrackLiveData.value = newPosition
    }

    fun nextMusicIndex() {
        val newPosition = currentTrackPosition + 1
        currentTrackPosition = if (newPosition > songsStorage.tracks.size - 1) {
            0
        } else {
            newPosition

        }
        currentTrackLiveData.value = newPosition
    }

    fun getSongTitle(): String {
        val currentSongTitle = songsStorage.getSongTitle(currentTrackPosition)
        currentSongTitleLiveData.value = currentSongTitle
        return currentSongTitle
    }

    fun getSongCover(): Int {
        when (songsStorage.tracks[currentTrackPosition]) {
            "game_of_thrones.mp3" -> currentSongCoverLiveData.value = R.drawable.game_of_thrones
            "imperial_marsh.mp3" -> currentSongCoverLiveData.value = R.drawable.imperial_marsh
            "harry_potter.mp3" -> currentSongCoverLiveData.value = R.drawable.harry_potter
            else -> {
                currentSongCoverLiveData.value = R.drawable.game_of_thrones
            }
        }
        return currentSongCoverLiveData.value!!
    }

    fun selectMusicTrack(): String {
        val currentSongTitle = songsStorage.tracks[currentTrackPosition]
        currentSongTitleLiveData.value = songsStorage.tracks[currentTrackPosition]
        return currentSongTitle
    }
}