package com.jmonzon.offlinemovies.data.remote.model

import com.jmonzon.offlinemovies.data.local.MovieEntity

data class MoviesResponse(
    private var results: List<MovieEntity>
)