package com.example.schooluser.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.activity_main.*
import android.Manifest
import android.content.ContentUris
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import com.vanniktech.rxpermission.Permission
import com.vanniktech.rxpermission.RealRxPermission
import kotlinx.android.synthetic.main.item_song.*
import kotlinx.android.synthetic.main.item_song.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.rx2.await

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val songs = getSong()
        if (songs.isNotEmpty())
            for (song in songs) {
                val mView = LayoutInflater.from(this).inflate(R.layout.item_song, null)
                mView.name_song.text = song.getTitle()
                mView.name_artist.text = song.getArtist()

                 mView.music_click.setOnClickListener {

                    GlobalScope.launch(Dispatchers.Default) {

                        // разрешения
                        val isGranted = RealRxPermission.getInstance(baseContext)
                                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .await()
                                .state() == Permission.State.GRANTED

                        if (isGranted) {
                            val intent = Intent(baseContext, MusicActivity::class.java)
                            intent.putExtra("filepath", song.getFilepath())
                            intent.putExtra("artist", song.getArtist())
                            intent.putExtra("title", song.getTitle())
                            intent.putExtra("album", song.getAlbum())
                            startActivity(intent)
                        }
                    }
                }

                list.addView(mView)
            }
    }


    //    Функция для поиска всей музыки на устройстве
    fun getSong(): List<Song> {
//    Лист с музыкой
        val list = mutableListOf<Song>()
//    Путь для поиска
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//    Все колонный где мы будем находить данные
        val cursorColumns = arrayOf(MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ALBUM_ID)
//    Бежит по файлам и смотрит является ли файл музыкой
        val where = MediaStore.Audio.Media.IS_MUSIC + "=1"
//
        val cursor = contentResolver.query(uri, cursorColumns, where, null, null)
//    Пока наш курсор перемещается он смотрит данные
        while (cursor?.moveToNext() == true) {
//            Поулчение артиста
            val artists = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
//            Путь до файла
            val data = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
//            Название файла
            val title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))
//            Идентефекатор альбома
            val albumId = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID))
//            Работа с обложкой(путь и замена)
            val albumImages = Uri.parse("content://media/external/audio/albumart")
//            Выбор обложки из всех обложек
            val albumImage = ContentUris.withAppendedId(albumImages, albumId)

            val song = Song()
            song.setAlbum(albumImage)
            song.setArtist(artists)
            song.setFilepath(data)
            song.setTitle(title)
            list.add(song)
        }
        cursor?.close()
        return list
    }

}