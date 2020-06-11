package com.jmonzon.offlinemovies.data.remote.model

import com.jmonzon.offlinemovies.data.local.dao.entity.MovieEntity

data class MoviesResponse(
    var results: List<MovieEntity>
)