package com.jmonzon.offlinemovies.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jmonzon.offlinemovies.R
import com.jmonzon.offlinemovies.data.local.dao.entity.MovieEntity
import com.jmonzon.offlinemovies.viewModel.MovieViewModel

class MovieFragment : Fragment() {

    private var columnCount = 2
    private val ARG_COLUMN_COUNT = "column-count"
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyMovieRecyclerViewAdapter
    private lateinit var movieList: List<MovieEntity>
    private lateinit var movieViewModel: MovieViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        recyclerView = view.findViewById(R.id.list)
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                loadMovies(context)
            }
        }
        return view
    }

    private fun loadMovies(context: Context) {
        movieViewModel.getPopularMovies().observe(viewLifecycleOwner, Observer {
            if (it.status.toString() !== "LOADING") {
                movieList = it.data!!
                adapter = MyMovieRecyclerViewAdapter(context, movieList)
                recyclerView.adapter = MyMovieRecyclerViewAdapter(context, movieList)
                adapter.setData(movieList)
            }

        })
    }


}
