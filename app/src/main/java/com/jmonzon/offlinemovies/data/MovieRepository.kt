package com.jmonzon.offlinemovies.data

import androidx.room.Room
import com.jmonzon.offlinemovies.app.MyApp
import com.jmonzon.offlinemovies.data.local.MovieRoomDataBase
import com.jmonzon.offlinemovies.data.local.dao.MovieDao
import com.jmonzon.offlinemovies.data.remote.ApiConstant
import com.jmonzon.offlinemovies.data.remote.MovieApiService
import com.jmonzon.offlinemovies.data.remote.RequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {

    private var movieApiService: MovieApiService
    private var movieDao: MovieDao

    init {
        //Local from Room
        var movieRoomDataBase: MovieRoomDataBase = Room.databaseBuilder(
            MyApp.getContext(),
            MovieRoomDataBase::class.java,
            "db_movies"
        ).build()

        //Include API_KEY in query
        val okHttpClientBuilder: OkHttpClient.Builder =
            OkHttpClient.Builder().addInterceptor(RequestInterceptor())
        val client: OkHttpClient = okHttpClientBuilder.build()
        movieDao = movieRoomDataBase.getMovieDao()

        //REMOTE -> RETROFIT
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ApiConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        movieApiService = retrofit.create(MovieApiService::class.java)
    }


}