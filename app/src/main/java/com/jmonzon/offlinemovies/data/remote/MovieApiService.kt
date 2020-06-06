package com.jmonzon.offlinemovies.data.remote

import com.jmonzon.offlinemovies.data.remote.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiService {

    @GET("/movie/popular")
    fun loadPopularMovies(): Call<MoviesResponse>
}