package com.khalbro.litemusicplayer.java.com.khalbro.litemusicplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.khalbro.litemusicplayer.R
import com.khalbro.litemusicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var player: MediaPlayer? = null
    private var isPlaying: Boolean = false
    private val songsStorage = SongsStorage()
    private var currentTrackPosition: Int = 0
    private lateinit var tracks: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tracks = songsStorage.getSongs(assets)
        var isClicked = true

        binding.btnPlayPause.setOnClickListener {
            playAudio()
            tracks[currentTrackPosition]
            Log.d("Ololo", "onCreate:${tracks[currentTrackPosition]} ")
            if (isClicked) {
                isClicked = false
                binding.btnPlayPause.text = getString(R.string.btn_pause)

            } else {
                binding.btnPlayPause.text = getString(R.string.btn_play)
                isClicked = true
            }
        }

        binding.btnNext.setOnClickListener {
            nextAudio()
        }
    }

    private fun nextAudio() {
        val newPosition = currentTrackPosition + 1
        currentTrackPosition = if (newPosition > tracks.size - 1) {
            0
        } else {
            newPosition
        }
        playAudio()
//        when (getRandomSong()) {
//            SongNumber.ONE -> {
//                player!!.pause()
//                player = MediaPlayer.create(this, R.raw.harry_potter)
//                player!!.start()
//            }
//
//            SongNumber.TWO -> {
//                player!!.pause()
//                player = MediaPlayer.create(this, R.raw.imperial_marsh)
//                player!!.start()
//            }
//
//            SongNumber.TREE -> {
//                player!!.pause()
//                player = MediaPlayer.create(this, R.raw.game_of_thrones)
//                player!!.start()
//            }
//        }
    }

    private fun playAudio() {

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

//    private fun getRawSong(): MediaPlayer {
//
//        return when (getRandomSong()) {
//            SongNumber.ONE -> MediaPlayer.create(this, R.raw.harry_potter)
//            SongNumber.TWO -> MediaPlayer.create(this, R.raw.imperial_marsh)
//            SongNumber.TREE -> MediaPlayer.create(this, R.raw.game_of_thrones)
//        }
//    }
//
//    private fun getRandomSong(): SongNumber {
//        val random = SongNumber.values()
//        return random.random()
//    }

            //    private fun getRandomSongNumber(): List<Songs> {
//       return emptyList()
//    }
        }
    }
}

//data class Songs(val songNumber: SongNumber, val songTitle: String) {
//    val songs = listOf(
//        Songs(SongNumber.ONE, SongNumber.ONE.songTitle),
//        Songs(SongNumber.TWO, SongNumber.TWO.songTitle),
//        Songs(SongNumber.TREE, SongNumber.TREE.songTitle)
//    )
//}








