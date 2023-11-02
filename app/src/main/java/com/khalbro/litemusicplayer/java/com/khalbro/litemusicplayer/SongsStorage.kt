package com.khalbro.litemusicplayer.java.com.khalbro.litemusicplayer

import android.content.res.AssetManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SongsStorage : AppCompatActivity() {
    fun getSongs(assetManager: AssetManager): List<String> {
        val files: Array<out String> = assetManager.list("tracks").orEmpty()
        Log.d("Ololo", "getSongs: ${files.size}")
        return files.toList()
    }
}