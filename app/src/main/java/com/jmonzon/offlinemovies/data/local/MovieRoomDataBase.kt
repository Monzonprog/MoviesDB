package com.jmonzon.offlinemovies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jmonzon.offlinemovies.data.local.dao.MovieDao
import com.jmonzon.offlinemovies.data.local.dao.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieRoomDataBase: RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
}