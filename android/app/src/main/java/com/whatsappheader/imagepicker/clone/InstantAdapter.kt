package com.whatsappheader.imagepicker.clone
/*
 * <pre>
 *     Author: xuchunlei
 *     Contact: hitxcl@gmail.com / FaceBook:Ryan Xu
 *     Time: 2021/03/14
 *     Remark:
 * </pre>
 */
class InstantAdapter {

}


//import android.content.Context
//import android.net.Uri
//import android.view.View
//import android.widget.ImageView
//import com.bumptech.glide.Glide
//import java.io.File
//import java.util.*
//
///**
// * Created by akshay on 17/03/18.
// */
//class InstantImageAdapter(context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
//    private val list: ArrayList<Img>
//    private var onSelectionListener: OnSelectionListener? = null
//    private val glide: RequestManager
//    private val options: RequestOptions
//    private val size: Float
//    private val margin = 3
//    private val padding: Int
//    fun addOnSelectionListener(onSelectionListener: OnSelectionListener?) {
//        this.onSelectionListener = onSelectionListener
//    }
//
//    fun addImage(image: Img): InstantImageAdapter {
//        list.add(image)
//        notifyDataSetChanged()
//        return this
//    }
//
//    val itemList: ArrayList<Any>
//        get() = list
//
//    fun addImageList(images: ArrayList<Img>?) {
//        list.addAll(images!!)
//        notifyDataSetChanged()
//    }
//
//    fun clearList() {
//        list.clear()
//    }
//
//    fun select(selection: Boolean, pos: Int) {
//        if (pos < 100) {
//            list[pos].setSelected(selection)
//            notifyItemChanged(pos)
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return if (viewType == MainImageAdapter.HEADER) {
//            val view: View = LayoutInflater.from(parent.getContext()).inflate(R.layout.inital_image, parent, false)
//            HolderNone(view)
//        } else {
//            val view: View = LayoutInflater.from(parent.getContext()).inflate(R.layout.inital_image, parent, false)
//            Holder(view)
//        }
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        val image: Img = list[position]
//        return if (image.getContentUrl().isEmpty()) MainImageAdapter.HEADER else MainImageAdapter.ITEM
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val image: Img = list[position]
//        if (holder is Holder) {
//            val imageHolder = holder as Holder
//            val layoutParams: FrameLayout.LayoutParams = FrameLayout.LayoutParams(size.toInt(), size.toInt())
//            if (position == 0) {
//                layoutParams.setMargins(-(margin / 2), margin, margin, margin)
//            } else {
//                layoutParams.setMargins(margin, margin, margin, margin)
//            }
//            imageHolder.itemView.setLayoutParams(layoutParams)
//            imageHolder.selection.setPadding(padding, padding, padding, padding)
//            imageHolder.preview.layoutParams = layoutParams
//            if (image.getMediaType() === 1) {
//                glide.load(image.getContentUrl()).apply(options).into(imageHolder.preview)
//                imageHolder.isVideo.visibility = View.GONE
//            } else if (image.getMediaType() === 3) {
//                glide.asBitmap()
//                        .load(Uri.fromFile(File(image.getUrl())))
//                        .apply(options)
//                        .into(imageHolder.preview)
//                imageHolder.isVideo.visibility = View.VISIBLE
//            }
//            imageHolder.selection.visibility = if (image.getSelected()) View.VISIBLE else View.GONE
//        } else {
//            val noneHolder = holder as HolderNone
//            val layoutParams: FrameLayout.LayoutParams = FrameLayout.LayoutParams(0, 0)
//            noneHolder.itemView.setLayoutParams(layoutParams)
//            noneHolder.itemView.setVisibility(View.GONE)
//        }
//    }
//
//    val itemCount: Int
//        get() = list.size
//
//    inner class Holder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, OnLongClickListener {
//        val preview: ImageView
//        val selection: ImageView
//        val isVideo: ImageView
//        override fun onClick(view: View) {
//            val id: Int = this.getLayoutPosition()
//            onSelectionListener.onClick(list[id], view, id)
//        }
//
//        override fun onLongClick(view: View): Boolean {
//            val id: Int = this.getLayoutPosition()
//            onSelectionListener.onLongClick(list[id], view, id)
//            return true
//        }
//
//        init {
//            preview = itemView.findViewById(R.id.preview)
//            selection = itemView.findViewById(R.id.selection)
//            isVideo = itemView.findViewById(R.id.isVideo)
//            itemView.setOnClickListener(this)
//            itemView.setOnLongClickListener(this)
//        }
//    }
//
//    inner class HolderNone internal constructor(itemView: View?) : RecyclerView.ViewHolder(itemView)
//
//    init {
//        list = ArrayList<Img>()
//        size = Utility.convertDpToPixel(72, context) - 2
//        padding = (size / 3.5).toInt()
//        glide = Glide.with(context)
//        options = RequestOptions().override(256).transform(CenterCrop()).transform(FitCenter())
//    }
//}