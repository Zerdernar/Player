package com.example.schooluser.myapplication

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_music.*

class MusicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        val path = intent.getStringExtra("path")
        val player = MediaPlayer()
        player.setDataSource(path)
        player.prepare()
        var flag = false

//                Кнопка Плей
        k_play.setOnClickListener {
            if (flag == true) {
                player.pause()
                flag = false
                k_play.setImageResource(R.drawable.play)
            } else{
                player.start()
                flag = true
                k_play.setImageResource(R.drawable.pause)
            }
        }
    }

}
