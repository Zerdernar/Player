package com.example.schooluser.myapplication

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_music.*

class MusicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
//        Получаем то что отправили из MainActivity
        val path = intent.getStringExtra("filepath")
        val title = intent.getStringExtra("title")
        val artist = intent.getStringExtra("artist")
//
        name_composition.text = title
        feename_composition.text = artist
        val player = MediaPlayer()
        player.setDataSource(path)
        player.prepare()
        var flag = false

//        Кнопка вперед
        forward.setOnClickListener {
            player.seekTo(player.currentPosition + 5000)
        }
        //        Кнопка вперед(долго)
        forward.setOnLongClickListener {
            player.seekTo(player.currentPosition + 5000)
            return@setOnLongClickListener true
        }

        //        Кнопка назад
        back.setOnClickListener {
            player.seekTo(player.currentPosition - 5000)
        }

        //        Кнопка назад(долго)
        back.setOnLongClickListener {
            player.seekTo(player.currentPosition - 5000)
            return@setOnLongClickListener true
        }

//        Кнопка стоп(долго)
        k_play.setOnLongClickListener {
            if (flag == true) {
                player.stop()
                player.prepare()
                player.start()
                return@setOnLongClickListener true
            } else {
                player.stop()
                player.prepare()
                return@setOnLongClickListener true
            }
        }
//                Кнопка Плей
        k_play.setOnClickListener {
            if (flag == true) {
                player.pause()
                flag = false
                k_play.setImageResource(R.drawable.play)
            } else {
                player.start()
                flag = true
                k_play.setImageResource(R.drawable.pause)
            }
        }
    }

}
