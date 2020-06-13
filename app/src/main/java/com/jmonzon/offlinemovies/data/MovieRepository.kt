package com.jmonzon.offlinemovies.data

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.jmonzon.offlinemovies.app.MyApp
import com.jmonzon.offlinemovies.data.local.MovieRoomDataBase
import com.jmonzon.offlinemovies.data.local.dao.MovieDao
import com.jmonzon.offlinemovies.data.local.dao.entity.MovieEntity
import com.jmonzon.offlinemovies.data.network.NetworkBoundResource
import com.jmonzon.offlinemovies.data.network.Resource
import com.jmonzon.offlinemovies.data.remote.ApiConstant
import com.jmonzon.offlinemovies.data.remote.MovieApiService
import com.jmonzon.offlinemovies.data.remote.RequestInterceptor
import com.jmonzon.offlinemovies.data.remote.model.MoviesResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {

    private var movieApiService: MovieApiService
    private var movieDao: MovieDao

    init {
        //Local from Room
        var movieRoomDataBase: MovieRoomDataBase = Room.databaseBuilder(
            MyApp.instance,
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

    fun getPopularMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, MoviesResponse>() {
            //Type return by room, type return by Retrofit
            override fun saveCallResult(@NonNull item: MoviesResponse) {
                movieDao.saveMovies(item.results)
            }

            override fun loadFromDb(): LiveData<List<MovieEntity>> {
                return movieDao.loadMovies()
            }

            override fun createCall(): Call<MoviesResponse> {
                return movieApiService.loadPopularMovies()
            }

        }.asLiveData()

    }
}

