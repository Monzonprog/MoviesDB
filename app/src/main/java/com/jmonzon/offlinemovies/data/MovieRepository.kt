package com.jmonzon.offlinemovies.data

import com.jmonzon.offlinemovies.data.remote.ApiConstant
import com.jmonzon.offlinemovies.data.remote.MovieApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {

    private var movieApiService: MovieApiService

    init {
        //REMOTE -> RETROFIT
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ApiConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        movieApiService = retrofit.create(MovieApiService::class.java)
    }


}