package com.example.myapplication2

import android.content.Intent
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.parcelize.Parcelize

class MainActivity : AppCompatActivity() {

    private lateinit var topAppBar: MaterialToolbar
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    private lateinit var mainRecycler: RecyclerView

    private val filmsDataBase = listOf(
        Film("Александр", R.drawable.poster_1, "Спустя 40 лет после гибели Александра пожилой Птолемей, один из ближайших соратников Македонского, ставший после его смерти наместником Египта, решает рассказать и записать историю побед великого полководца.\n" +
                " В течение восьми лет войско Александра Великого двигалось на Восток, к берегам мирового Океана.\n"),
        Film("Храброе сердце", R.drawable.poster_2, "Действие фильма начинается в 1280 году в Шотландии. \n" +
                "Это история легендарного национального героя Уильяма Уоллеса, посвятившего себя борьбе с англичанами при короле Эдварде Длинноногом.\n"),
        Film("Гладиатор", R.drawable.poster_3, "В великой Римской империи не было военачальника, равного генералу Максимусу. \n" +
                "Непобедимые легионы, которыми командовал этот благородный воин, боготворили его и могли последовать за ним даже в ад."),
        Film("Одиссей", R.drawable.poster_1, "Год за годом греки сражались под стенами Трои. Тысячи воинов полегли убитыми. Никто не знал, на чьей стороне боги. \n" +
                "Греки роптали. Тогда их хитроумный царь Одиссей придумал, как проникнуть в Трою."),
        Film("Троя", R.drawable.poster_2, "1193 год до нашей эры. Парис украл прекрасную Елену, жену царя Спарты Менелая. За честь Менелая вступается его брат – царь Агамемнон."),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setListeners()

        val itemClickListener = object : OnItemClickListener {
            override fun click(film: Film) {
                val bundle = Bundle()
                bundle.putParcelable("film", film)
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }

        filmsAdapter = FilmListRecyclerAdapter(itemClickListener)

        mainRecycler = findViewById(R.id.main_recycler)

        mainRecycler.apply {
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        filmsAdapter.addItems(filmsDataBase)
    }

    private fun initViews() {
        topAppBar = findViewById(R.id.topAppBar)
        bottomNavigation = findViewById(R.id.bottom_navigation)
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

        bottomNavigation.setOnNavigationItemSelectedListener {

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
}

@Parcelize
data class Film(
    val title: String,
    val poster: Int,
    val description: String
): Parcelable