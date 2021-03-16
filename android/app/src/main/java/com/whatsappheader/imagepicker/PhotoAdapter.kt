package com.whatsappheader.imagepicker

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.whatsappheader.R

/*
 * <pre>
 *     Author: xuchunlei
 *     Contact: hitxcl@gmail.com / FaceBook:Ryan Xu
 *     Time: 2021/03/16
 *     Remark:  
 * </pre>
 */
class PhotoAdapter(context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<Photo>
    private val glide: RequestManager
    private val options: RequestOptions

    init {
        data = mutableListOf()
        glide = Glide.with(context)
        options = RequestOptions()
                .format(DecodeFormat.PREFER_RGB_565)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val photo: Photo = data[position]
        Log.e("PhotoAdapter", "load position ---> " + data[position])
        if(holder is Holder) {
            when(photo.type){
                1 -> {
                    glide.load(photo.uri).apply(options).into(holder.preview)
                    holder.videoMark.visibility = View.GONE
                }
                else -> {

                }
            }
            holder.selectMark.visibility = if(photo.isSelected) View.VISIBLE else View.GONE

        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addData(data: MutableList<Photo>) {
        this.data.addAll(data!!)
        notifyDataSetChanged()
    }

    inner class Holder internal constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        internal val preview: ImageView
        internal val videoMark: ImageView
        internal val selectMark: ImageView


        init {
            preview = itemView.findViewById(R.id.photo_preview)
            videoMark = itemView.findViewById(R.id.photo_video_mark)
            selectMark = itemView.findViewById(R.id.photo_select_mark)
        }
    }
}