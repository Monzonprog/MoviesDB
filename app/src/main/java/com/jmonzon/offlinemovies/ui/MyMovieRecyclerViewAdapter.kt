package com.jmonzon.offlinemovies.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.jmonzon.offlinemovies.R
import com.jmonzon.offlinemovies.data.remote.ApiConstant
import com.jmonzon.offlinemovies.data.local.dao.entity.MovieEntity
import kotlinx.android.synthetic.main.fragment_movie.view.*

class MyMovieRecyclerViewAdapter(
    private val context: Context,
    private var mValues: List<MovieEntity>
) : RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        Glide.with(context)
            .load(ApiConstant.IMAGE_API_PREFIX + item.poster_path)
            .into(holder.imageViewCover)
    }

    fun setData (movies: List<MovieEntity>){
        mValues = movies
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (mValues.size !== 0){
            return mValues.size
        }else{
            return 0
        }
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val imageViewCover: ImageView = mView.imageViewCover
    }
}
