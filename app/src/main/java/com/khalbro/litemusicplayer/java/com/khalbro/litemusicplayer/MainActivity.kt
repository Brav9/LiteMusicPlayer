package com.khalbro.litemusicplayer.java.com.khalbro.litemusicplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
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

        binding.tvSongTitle.text = songTitle()

        binding.vSongCover.setBackgroundResource(songCover())

        binding.btnPrevious.setOnClickListener {
            previousAudio()
            binding.tvSongTitle.text = songTitle()
            binding.vSongCover.setBackgroundResource(songCover())
        }

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
            binding.tvSongTitle.text = songTitle()
            binding.vSongCover.setBackgroundResource(songCover())
        }
    }

    private fun songTitle(): String {
        return tracks[currentTrackPosition]
    }

    private fun songCover(): Int {
        return when (tracks[currentTrackPosition]) {
            "game_of_thrones.mp3" -> R.drawable.game_of_thrones
            "imperial_marsh.mp3" -> R.drawable.imperial_marsh
            "harry_potter.mp3" -> R.drawable.harry_potter

            else -> {
                R.drawable.game_of_thrones
            }
        }
    }

    private fun previousAudio() {
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
                playAudio()
                songTitle()
            } else {
                isPlaying = true
                songTitle()
            }
        }
    }

    private fun nextAudio() {
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
                playAudio()
                Log.d("Ololo", "nextAudio:$currentTrackPosition ")
            } else {
                isPlaying = true
                Log.d("Ololo", "nextAudio:$currentTrackPosition ")
            }
        }
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
        }
    }
}








