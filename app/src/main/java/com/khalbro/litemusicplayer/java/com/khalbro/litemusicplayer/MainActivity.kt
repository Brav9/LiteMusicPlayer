package com.khalbro.litemusicplayer.java.com.khalbro.litemusicplayer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.khalbro.litemusicplayer.R
import com.khalbro.litemusicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val playerController = PlayerController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var isClicked = true

        val songCoverObserver = Observer<Int> { newSongTitle ->
            binding.vSongCover.setBackgroundResource(newSongTitle)
        }

        val songTitleObserver = Observer<String> { newSongTitle ->
            binding.tvSongTitle.text = newSongTitle
        }

        playerController.currentSongTitleLiveData.observe(this, songTitleObserver)
        playerController.currentSongCoverLiveData.observe(this, songCoverObserver)


        binding.btnPrevious.setOnClickListener {
            previousMusicService()
        }

        binding.btnPlayPause.setOnClickListener {
            isClicked = if (isClicked) {
                startService()
                playStopMusicService()
                binding.btnPlayPause.text = getString(R.string.btn_pause)
                false
            } else {
                binding.btnPlayPause.text = getString(R.string.btn_play)
                playStopMusicService()
                stopService()
                true
            }
        }

        binding.btnNext.setOnClickListener {
            nextMusicService()
        }
    }

    private fun previousMusicService() {
        Intent(applicationContext, MusicService::class.java).also {
            it.action = MusicService.Actions.PREVIOUS_MUSIC.toString()
            startService(it)
        }
    }

    private fun nextMusicService() {
        Intent(applicationContext, MusicService::class.java).also {
            it.action = MusicService.Actions.NEXT_MUSIC.toString()
            startService(it)
        }
    }


    private fun startService() {
        Intent(applicationContext, MusicService::class.java).also {
            it.action = MusicService.Actions.START.toString()
            startService(it)
        }
    }

    private fun stopService() {
        Intent(applicationContext, MusicService::class.java).also {
            it.action = MusicService.Actions.STOP.toString()
            startService(it)
        }
    }

    private fun playStopMusicService() {
        Intent(applicationContext, MusicService::class.java).also {
            it.action = MusicService.Actions.PLAY_STOP_MUSIC.toString()
            startService(it)
        }
    }
}








