package com.example.schooluser.myapplication

import android.net.Uri

class Song {
    private var title = ""
    private var artist = ""
    private var filepath= ""
    private var albumpath: Uri? = null

    //    Получаем название и возвращаем
    fun setTitle(path: String) {
        title = path
    }

    fun getTitle(): String {
        return title
    }

    //    Получаем имя артиста и возвращаем
    fun setArtist(path: String) {
        artist = path
    }

    fun getArtist(): String {
        return artist
    }

    //    Получаем путь до файла и возвращаем
    fun setFilepath(path: String) {
        filepath = path
    }

    fun getFilepath(): String {
        return filepath
    }

    //    Получаем альбом и возвращаем
    fun setAlbum(path: Uri) {
        albumpath = path
    }

    fun getAlbum(): Uri? {
        return albumpath
    }

}
