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
import com.facebook.react.ReactActivity
import com.facebook.react.ReactApplication
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.WritableNativeMap
import com.facebook.react.uimanager.events.RCTEventEmitter
import com.whatsappheader.R
import kotlin.reflect.typeOf

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
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_instant_photo, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val photo: Photo = data[position]
        val imageHolder = holder as Holder
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

    override fun getItemCount(): Int {
        return data.size
    }

    inner class Holder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal val preview: ImageView
        internal val videoMark: ImageView
        internal val selectMark: ImageView


        init {
            preview = itemView.findViewById(R.id.instant_photo_preview)
            videoMark = itemView.findViewById(R.id.instant_photo_video_mark)
            selectMark = itemView.findViewById(R.id.instant_photo_select_mark)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            v?.let {
                val params = WritableNativeMap()
                params.putString("image", data[0].uri)

                ((v.context as ReactActivity).applicationContext as ReactApplication)
                        .reactNativeHost
                        .reactInstanceManager
                        .currentReactContext?.let {
                            it.getJSModule(RCTEventEmitter::class.java)
                                .receiveEvent(
                                        Constants.TAEGET_TAG,
                                        "onPickFinished",
                                        params
                                )
                        }

            }
        }

    }

}