package com.example.whattowatch.ui.films

import android.opengl.Visibility
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.RenderProcessGoneDetail
import android.widget.ProgressBar
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

    val films : MutableList<Film?> = mutableListOf()

    lateinit var firstProgressBar:ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstProgressBar = view.findViewById(R.id.firstProgressBar)
        filmsRecyclerView = view.findViewById(R.id.filmRecyclerView)
        val filmsRVLayoutManager = LinearLayoutManager(activity?.applicationContext)
        val filmsAdapter = FilmsAdapter(films, activity?.applicationContext)

        filmsRecyclerView.layoutManager = filmsRVLayoutManager
        filmsRecyclerView.adapter = filmsAdapter

        filmsRecyclerView.addItemDecoration(FilmItemMargin(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50f, resources.displayMetrics).toInt()))

        val loadScrollListener = object : LoadScrollListener(filmsRVLayoutManager) {
            override fun onLoadMore() {
                films.add(null)
                filmsAdapter.notifyDataSetChanged()
                viewModel.getFilms()
            }

        }

        filmsRecyclerView.addOnScrollListener(loadScrollListener)

        viewModel.filmsLiveData.observe(viewLifecycleOwner){
            films.clear()
            films.addAll(it)
            loadScrollListener.setLoaded()
            firstProgressBar.visibility = View.GONE
            filmsAdapter.notifyDataSetChanged()
        }


    }

}