package com.jmonzon.offlinemovies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jmonzon.offlinemovies.R
import com.jmonzon.offlinemovies.data.local.dao.entity.MovieEntity

class MovieFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1
    private val ARG_COLUMN_COUNT = "column-count"
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieList: List<MovieEntity>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyMovieRecyclerViewAdapter(context, movieList)
                recyclerView.adapter = adapter
            }
        }
        return view
    }


}
