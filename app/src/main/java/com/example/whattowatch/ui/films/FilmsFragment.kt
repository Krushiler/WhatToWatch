package com.example.whattowatch.ui.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}