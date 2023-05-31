package com.example.myapplication2.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.AnimationHelper
import com.example.myapplication2.Film
import com.example.myapplication2.FilmListRecyclerAdapter
import com.example.myapplication2.MainActivity
import com.example.myapplication2.OnItemClickListener
import com.example.myapplication2.R
import com.example.myapplication2.TopSpacingItemDecoration
import java.util.Locale


class HomeFragment : Fragment() {

    private val filmsDataBase = listOf(
        Film("Александр", R.drawable.poster_1, "Спустя 40 лет после гибели Александра пожилой Птолемей, один из ближайших соратников Македонского, ставший после его смерти наместником Египта, решает рассказать и записать историю побед великого полководца.\n" +
                " В течение восьми лет войско Александра Великого двигалось на Восток, к берегам мирового Океана.\n", 5f),
        Film("Храброе сердце", R.drawable.poster_2, "Действие фильма начинается в 1280 году в Шотландии. \n" +
                "Это история легендарного национального героя Уильяма Уоллеса, посвятившего себя борьбе с англичанами при короле Эдварде Длинноногом.\n",8.7f),
        Film("Гладиатор", R.drawable.poster_3, "В великой Римской империи не было военачальника, равного генералу Максимусу. \n" +
                "Непобедимые легионы, которыми командовал этот благородный воин, боготворили его и могли последовать за ним даже в ад.", 4.1f),
        Film("Одиссей", R.drawable.poster_1, "Год за годом греки сражались под стенами Трои. Тысячи воинов полегли убитыми. Никто не знал, на чьей стороне боги. \n" +
                "Греки роптали. Тогда их хитроумный царь Одиссей придумал, как проникнуть в Трою.", 6.3f),
        Film("Троя", R.drawable.poster_2, "1193 год до нашей эры. Парис украл прекрасную Елену, жену царя Спарты Менелая. За честь Менелая вступается его брат – царь Агамемнон.", 10f),
    )

    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private lateinit var mainRecycler: RecyclerView
    private lateinit var search_view: SearchView
    private lateinit var home_fragment_root: ViewGroup

    private val itemClickListener = object : OnItemClickListener {
        override fun click(film: Film) {
            (requireActivity() as MainActivity).launchDetailsFragment(film)
        }
    }

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        home_fragment_root = view.findViewById(R.id.home_fragment_root)
        return view
       }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search_view = view.findViewById(R.id.search_view)
        mainRecycler = view.findViewById(R.id.main_recycler)

        AnimationHelper.AnimationHelper
            .performFragmentCircularRevealAnimation(
                home_fragment_root,
                requireActivity(),
                1
            )

        filmsAdapter = FilmListRecyclerAdapter(itemClickListener)
        filmsAdapter.addItems(filmsDataBase)

        search_view.setOnClickListener {
            search_view.isIconified = false
        }

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    filmsAdapter.addItems(filmsDataBase)
                    return true
                }
                val result = filmsDataBase.filter {
                    it.title.toLowerCase(Locale.getDefault())
                        .contains(newText.toLowerCase(Locale.getDefault()))
                }
                filmsAdapter.addItems(result)
                return true
            }
        })
        initRecycler()

    }

    private fun initRecycler() {
        mainRecycler.apply {
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        filmsAdapter.addItems(filmsDataBase)
    }
}