package com.jmonzon.offlinemovies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jmonzon.offlinemovies.data.MovieRepository
import com.jmonzon.offlinemovies.data.local.dao.entity.MovieEntity
import com.jmonzon.offlinemovies.data.network.Resource

class MovieViewModel : ViewModel(){

    companion object {
        private var movieRepository = MovieRepository()
        var popularMovies: LiveData<Resource<List<MovieEntity>>> = movieRepository.getPopularMovies()

    }

    fun getPopularMovies(): LiveData<Resource<List<MovieEntity>>>{
        return popularMovies
    }
}