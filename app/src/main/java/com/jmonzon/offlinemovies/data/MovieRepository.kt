package com.jmonzon.offlinemovies.data

import com.jmonzon.offlinemovies.data.remote.ApiConstant
import com.jmonzon.offlinemovies.data.remote.MovieApiService
import com.jmonzon.offlinemovies.data.remote.RequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {

    private var movieApiService: MovieApiService

    init {
        //Include API_KEY in query
        var okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(RequestInterceptor())
        var client: OkHttpClient = okHttpClientBuilder.build()

        //REMOTE -> RETROFIT
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ApiConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        movieApiService = retrofit.create(MovieApiService::class.java)
    }


}