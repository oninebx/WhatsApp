package com.whatsappheader.imagepicker.clone
/*
 * <pre>
 *     Author: xuchunlei
 *     Contact: hitxcl@gmail.com / FaceBook:Ryan Xu
 *     Time: 2021/03/16
 *     Remark:
 * </pre>
 */
class PhotoAdapter {
}
//import android.content.Context
//import android.net.Uri
//import android.view.View
//import android.widget.ImageView
//import com.fxn.interfaces.OnSelectionListener
//import java.io.File
//import java.util.*
//
///**
// * Created by akshay on 17/03/18.
// */
//class MainImageAdapter(context: Context?, spanCount: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder?>(), HeaderItemDecoration.StickyHeaderInterface, SectionIndexer {
//    private val list: ArrayList<Img>
//    private var onSelectionListener: OnSelectionListener? = null
//    private val layoutParams: FrameLayout.LayoutParams
//    private val glide: RequestManager
//    private val options: RequestOptions
//    val itemList: ArrayList<Any>
//        get() = list
//
//    fun addImage(image: Img): MainImageAdapter {
//        list.add(image)
//        notifyDataSetChanged()
//        return this
//    }
//
//    fun addOnSelectionListener(onSelectionListener: OnSelectionListener?) {
//        this.onSelectionListener = onSelectionListener
//    }
//
//    fun addImageList(images: ArrayList<Img>?) {
//        list.addAll(images!!)
//        notifyDataSetChanged()
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        if (list.size <= position) {
//            return 0
//        }
//        val i: Img = list[position]
//        return if (i.getContentUrl().equalsIgnoreCase("")) HEADER else ITEM
//    }
//
//    fun clearList() {
//        list.clear()
//    }
//
//    fun select(selection: Boolean, pos: Int) {
//        list[pos].setSelected(selection)
//        notifyItemChanged(pos)
//    }
//
//    override fun getItemId(position: Int): Long {
//        return list[position].getContentUrl().hashCode()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return if (viewType == HEADER) {
//            HeaderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header_row, parent, false))
//        } else {
//            val view: View = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_image, parent, false)
//            Holder(view)
//        }
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val image: Img = list[position]
//        if (holder is Holder) {
//            val imageHolder = holder as Holder
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
//        } else if (holder is HeaderHolder) {
//            val headerHolder = holder as HeaderHolder
//            headerHolder.header.setText(image.getHeaderDate())
//        }
//    }
//
//    val itemCount: Int
//        get() = list.size
//
//    fun getHeaderPositionForItem(position: Int): Int {
//        var itemPosition = position
//        var headerPosition = 0
//        do {
//            if (isHeader(itemPosition)) {
//                headerPosition = itemPosition
//                break
//            }
//            itemPosition -= 1
//        } while (itemPosition >= 0)
//        return headerPosition
//    }
//
//    fun getHeaderLayout(headerPosition: Int): Int {
//        return R.layout.header_row
//    }
//
//    fun bindHeaderData(header: View, headerPosition: Int) {
//        val image: Img = list[headerPosition]
//        (header.findViewById<View>(R.id.header) as TextView).setText(image.getHeaderDate())
//    }
//
//    fun isHeader(itemPosition: Int): Boolean {
//        return getItemViewType(itemPosition) == 1
//    }
//
//    fun getSectionText(position: Int): String {
//        return list[position].getHeaderDate()
//    }
//
//    fun getSectionMonthYearText(position: Int): String {
//        return if (list.size <= position) {
//            ""
//        } else list[position].getHeaderDate()
//    }
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
//            preview.layoutParams = layoutParams
//        }
//    }
//
//    inner class HeaderHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val header: TextView
//
//        init {
//            header = itemView.findViewById<TextView>(R.id.header)
//        }
//    }
//
//    companion object {
//        const val HEADER = 1
//        const val ITEM = 2
//        var SPAN_COUNT = 0
//        private const val MARGIN = 4
//    }
//
//    init {
//        list = ArrayList<Img>()
//        SPAN_COUNT = spanCount
//        val size: Int = Utility.WIDTH / SPAN_COUNT - MARGIN / 2
//        layoutParams = FrameLayout.LayoutParams(size, size)
//        layoutParams.setMargins(MARGIN, MARGIN - MARGIN / 2, MARGIN, MARGIN - MARGIN / 2)
//        options = RequestOptions().override(size - 50)
//                .format(DecodeFormat.PREFER_RGB_565)
//                .centerCrop()
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//        glide = Glide.with(context)
//    }
//}