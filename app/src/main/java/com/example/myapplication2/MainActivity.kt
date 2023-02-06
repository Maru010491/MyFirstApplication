package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var topAppBar: MaterialToolbar
    private lateinit var bottom_navigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setListeners()
    }

    private fun initViews() {
        topAppBar = findViewById(R.id.topAppBar)
        bottom_navigation = findViewById(R.id.bottom_navigation)
    }

    private fun setListeners() {
        topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_main_setting -> {
                    Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        bottom_navigation.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.app_bar_favourites -> {
                    Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.app_bar_late -> {
                    Toast.makeText(this, "Посмотреть похже", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.app_bar_podborka -> {
                    Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
    val filmsDataBase = listOf(
        Film("Александр", R.drawable.alexandr, "Спустя 40 лет после гибели Александра пожилой Птолемей, один из ближайших соратников Македонского, ставший после его смерти наместником Египта, решает рассказать и записать историю побед великого полководца.\n" +
                " В течение восьми лет войско Александра Великого двигалось на Восток, к берегам мирового Океана.\n"),
        Film("Храброе сердце", R.drawable.chrabroeserdce, "Действие фильма начинается в 1280 году в Шотландии. \n" +
                "Это история легендарного национального героя Уильяма Уоллеса, посвятившего себя борьбе с англичанами при короле Эдварде Длинноногом.\n"),
        Film("Гладиатор", R.drawable.gladiator, "В великой Римской империи не было военачальника, равного генералу Максимусу. \n" +
                "Непобедимые легионы, которыми командовал этот благородный воин, боготворили его и могли последовать за ним даже в ад."),
        Film("Одиссей", R.drawable.odissei, "Год за годом греки сражались под стенами Трои. Тысячи воинов полегли убитыми. Никто не знал, на чьей стороне боги. \n" +
                "Греки роптали. Тогда их хитроумный царь Одиссей придумал, как проникнуть в Трою."),
        Film("Троя", R.drawable.troya, "1193 год до нашей эры. Парис украл прекрасную Елену, жену царя Спарты Менелая. За честь Менелая вступается его брат – царь Агамемнон."),

        )
}
data class Film(
    val title: String,
    val poster: Int,
    val description: String
)
