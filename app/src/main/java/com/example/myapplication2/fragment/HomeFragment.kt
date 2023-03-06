package com.example.myapplication2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.*
import java.util.*

class HomeFragment : Fragment() {

    private val filmsDataBase = listOf(
        Film("Alexandr", R.drawable.poster_1, "Спустя 40 лет после гибели Александра пожилой Птолемей, один из ближайших соратников Македонского, ставший после его смерти наместником Египта, решает рассказать и записать историю побед великого полководца.\n" +
                " В течение восьми лет войско Александра Великого двигалось на Восток, к берегам мирового Океана.\n"),
        Film("Chrabroe serdce", R.drawable.poster_2, "Действие фильма начинается в 1280 году в Шотландии. \n" +
                "Это история легендарного национального героя Уильяма Уоллеса, посвятившего себя борьбе с англичанами при короле Эдварде Длинноногом.\n"),
        Film("Gladiator", R.drawable.poster_3, "В великой Римской империи не было военачальника, равного генералу Максимусу. \n" +
                "Непобедимые легионы, которыми командовал этот благородный воин, боготворили его и могли последовать за ним даже в ад."),
        Film("Odissey", R.drawable.poster_1, "Год за годом греки сражались под стенами Трои. Тысячи воинов полегли убитыми. Никто не знал, на чьей стороне боги. \n" +
                "Греки роптали. Тогда их хитроумный царь Одиссей придумал, как проникнуть в Трою."),
        Film("Troya", R.drawable.poster_2, "1193 год до нашей эры. Парис украл прекрасную Елену, жену царя Спарты Менелая. За честь Менелая вступается его брат – царь Агамемнон."),
    )

    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private lateinit var mainRecycler: RecyclerView
    private lateinit var search_view: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        mainRecycler = view.findViewById(R.id.main_recycler)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val itemClickListener = object : OnItemClickListener {
            override fun click(film: Film) {
                (requireActivity() as MainActivity).launchDetailsFragment(film)
            }
        }

        filmsAdapter = FilmListRecyclerAdapter(itemClickListener)

        mainRecycler.apply {
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        filmsAdapter.addItems(filmsDataBase)

        search_view = view.findViewById(R.id.search_view)

        search_view.setOnClickListener {
            search_view.isIconified = false
        }


        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    if (newText.isEmpty()) {
                        filmsAdapter.addItems(filmsDataBase)
                        return true
                    }
                }

                val result = filmsDataBase.filter {
                    newText?.let { it1 ->
                        it.title.toLowerCase(Locale.getDefault())
                            .contains(it1.toLowerCase(Locale.getDefault()))
                    } == true
                }
                filmsAdapter.addItems(result)
                return true
            }
        })
    }
}



