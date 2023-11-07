package com.khalbro.litemusicplayer.java.com.khalbro.litemusicplayer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        binding.tvSongTitle.text = playerController.getSongTitle()
        binding.vSongCover.setBackgroundResource(playerController.getSongCover())

        binding.btnPrevious.setOnClickListener {
            previousMusicService()

            binding.tvSongTitle.text = playerController.getSongTitle()
            binding.vSongCover.setBackgroundResource(playerController.getSongCover())
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
            binding.tvSongTitle.text = playerController.getSongTitle()
            binding.vSongCover.setBackgroundResource(playerController.getSongCover())
        }
    }

    private fun previousMusicService() {
        binding.tvSongTitle.text = playerController.getSongTitle()
        binding.vSongCover.setBackgroundResource(playerController.getSongCover())
        Intent(applicationContext, MusicService::class.java).also {
            it.action = MusicService.Actions.PREVIOUS_MUSIC.toString()
            startService(it)
        }
    }

    private fun nextMusicService() {
        binding.tvSongTitle.text = playerController.getSongTitle()
        binding.vSongCover.setBackgroundResource(playerController.getSongCover())
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








