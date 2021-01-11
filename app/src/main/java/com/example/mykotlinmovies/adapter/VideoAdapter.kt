package com.example.mykotlinmovies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinmovies.R
import com.example.mykotlinmovies.pojo.Video
import kotlinx.android.synthetic.main.video_item.view.*

class VideoAdapter() : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private lateinit var onVideoClickListener: OnVideoClickListener

    fun setOnVideoClickListener(onVideoClickListener: OnVideoClickListener){
        this.onVideoClickListener = onVideoClickListener
    }

    var videos: ArrayList<Video> = ArrayList()
        get() = field
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.videoTitle.text = videos[position].name
    }

    override fun getItemCount(): Int = videos.size


    interface OnVideoClickListener{
        fun onVideoClick(position: Int)
    }

    inner class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val videoTitle: TextView = itemView.findViewById(R.id.video_title_item)

        init {
            itemView.setOnClickListener {
                if (onVideoClickListener != null){
                    onVideoClickListener.onVideoClick(adapterPosition)
                }
            }
        }
    }
}