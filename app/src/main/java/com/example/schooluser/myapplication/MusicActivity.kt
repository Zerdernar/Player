package com.example.schooluser.myapplication

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_music.*

//Наш класс(называется как угодно у нас MainActivity)
class MusicActivity : AppCompatActivity() {

    //Перезапись функции onCreate с параметром(saved...
    override fun onCreate(savedInstanceState: Bundle?) {
//        Обращение к родительскому классу
        super.onCreate(savedInstanceState)
//    Цепляем xml(разметку)
        setContentView(R.layout.activity_music)
//        Получаем то что отправили из MainActivity
//    Получаем путь до файла(присваиваем)
        val path = intent.getStringExtra("filepath")
//    Получаем название титульника(присваиваем)(название)
        val title = intent.getStringExtra("title")
//    Получаем название артиста(присваиваем)
        val artist = intent.getStringExtra("artist")
//Вставляем полученные данные(из присвоенных)
//        Вставляем полученный титульник
        name_composition.text = title
//        Вставляем полученного артиста
        feename_composition.text = artist

//    Присваеваем стандартному андройдовскому MediaPlayer переменную
        val player = player()
        player.setDataSource(path)
        player.prepare()

//    Делаем переменную флаг для понятия кнопки play/pause
        var flag = false

//        Кнопка вперед
//      Название    клик
        forward.setOnClickListener {
//переменная плеера.Команда перемотки
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
            //            Если флаг поднят то
            if (flag == true) {
//                Останавливает плеер
                player.stop()
//                Возвращает трек в начало
                player.prepare()
//                Запускает плеер
                player.start()
//                Проверяет было ли долгое нажатие на самом деле
//                 (Без него не работает команда LongClick)
                return@setOnLongClickListener true
            }
//           В обратном случае
            else {
//                Останавливаем плеер
                player.stop()
//                Отматываем в начало
                player.prepare()
                //                Проверяет было ли долгое нажатие на самом деле
//                 (Без него не работает команда LongClick)
                return@setOnLongClickListener true
            }
        }
//                Кнопка Плей
        k_play.setOnClickListener {
            //            Чекаем флаг, если он поднят то
            if (flag == true) {
//                останавливаем проигрывание
                player.pause()
//                Опускаем флаг(для того что бы при следующем юзе команда поняла что он выключен)
                flag = false
//                Меняем изображение с кнопки пауза на плей
                k_play.setImageResource(R.drawable.play)
//                Если нет(если флаг опущен), то
            } else {
//                Включаем плеер
                player.start()
//                Поднимаем флаг
                flag = true
//                Меняем картинку с плей на паузу
                k_play.setImageResource(R.drawable.pause)
            }
        }
    }

}
