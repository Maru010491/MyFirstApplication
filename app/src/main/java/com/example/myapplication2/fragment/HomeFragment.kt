package com.example.myapplication2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.*


class HomeFragment : Fragment() {

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

    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private lateinit var mainRecycler: RecyclerView

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
    }
}