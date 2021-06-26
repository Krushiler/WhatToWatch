package com.example.whattowatch.ui.films

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whattowatch.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmsFragment : Fragment() {

    val viewModel : FilmsViewModel by viewModel()

    companion object {
        fun newInstance() = FilmsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_films, container, false)
    }

    lateinit var filmsRecyclerView: RecyclerView

    val films : MutableList<Film> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filmsRecyclerView = view.findViewById(R.id.filmRecyclerView)
        val filmsRVLayoutManager = LinearLayoutManager(activity?.applicationContext)
        val filmsAdapter = FilmsAdapter(films, activity?.applicationContext)

        filmsRecyclerView.layoutManager = filmsRVLayoutManager
        filmsRecyclerView.adapter = filmsAdapter

        filmsRecyclerView.addItemDecoration(FilmItemMargin(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50f, resources.displayMetrics).toInt()))

        filmsRecyclerView.addOnScrollListener(object : LoadScrollListener(filmsRVLayoutManager){
            override fun onLoadMore() {
                viewModel.getFilms()
            }
        })

        viewModel.filmsLiveData.observe(viewLifecycleOwner){
            films.clear()
            films.addAll(it)
            filmsAdapter.notifyDataSetChanged()
        }


    }

}