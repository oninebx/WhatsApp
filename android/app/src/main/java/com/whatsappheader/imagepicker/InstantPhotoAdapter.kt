package com.whatsappheader.imagepicker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestOptions
import com.whatsappheader.R

/*
 * <pre>
 *     Author: xuchunlei
 *     Contact: hitxcl@gmail.com / FaceBook:Ryan Xu
 *     Time: 2021/03/14
 *     Remark:  
 * </pre>
 */
class InstantPhotoAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<Photo>
    private val glide: RequestManager
    private val options: RequestOptions

    init{
        data = mutableListOf()
        glide = Glide.with(context)
        options = RequestOptions().override(256).transform(CenterCrop()).transform(FitCenter())
    }


    fun setData(photos: MutableList<Photo>?) {
        data.addAll(photos!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val photo: Photo = data[position]
        val imageHolder = holder as Holder
        when(photo.type){
            1 -> { glide.load(photo.uri).apply(options).into(holder.preview) }
            else -> {

            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class Holder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val preview: ImageView

        init {
            preview = itemView.findViewById(R.id.photo_preview)
        }
    }

}