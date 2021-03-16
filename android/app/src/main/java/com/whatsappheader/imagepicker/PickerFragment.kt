package com.whatsappheader.imagepicker

import android.content.res.Resources
import android.database.Cursor
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.facebook.react.bridge.ReactContext
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.otaliastudios.cameraview.CameraView
import com.otaliastudios.cameraview.controls.Facing
import com.whatsappheader.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PickerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PickerFragment : Fragment() {

    // views
    private var camera: CameraView? = null
    private var front: ImageView? = null

    private var options: Options? = null

    // photo
    private lateinit var instantPhotoList: RecyclerView
    private var instantAdapter: InstantPhotoAdapter? = null
    private lateinit var photoList: RecyclerView
    private var photoAdapter: PhotoAdapter? = null


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            options = it.getSerializable(OPTIONS) as Options
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i("PickerFragment", "onCreateView")
        var view = inflater.inflate(R.layout.fragment_picker, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        // camera
        camera = view.findViewById(R.id.picker_camera)

        // front
        front = view.findViewById(R.id.picker_front)
        front!!.setOnClickListener{
            if(options?.isFacingFront == true){
                options?.setFacingFront(false)
                camera?.facing = Facing.BACK
            }else{
                camera?.facing = Facing.FRONT
                options?.setFacingFront(true)
            }
        }
        // recyclerView
        instantPhotoList = view.findViewById(R.id.picker_photo_instant_list)
        instantPhotoList.addItemDecoration(object: RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.top = this@PickerFragment.resources.getDimensionPixelOffset(R.dimen.spacing_2)
            }
        })
        photoList = view.findViewById(R.id.picker_photo_list)


        this.context?.let {
            instantAdapter = InstantPhotoAdapter(it)
            var data = PhotoProvider.getPhotoList(it, options?.mode)
            instantAdapter!!.setData(data)
            instantPhotoList.adapter = instantAdapter

            photoAdapter = PhotoAdapter(it)
            photoAdapter!!.addData(data)
            photoList.adapter = photoAdapter

            var bottomContainer: View = view.findViewById(R.id.picker_bottom_buttons_container)
            setupBottomSheet(view.findViewById<View>(R.id.picker_bottom_sheet), bottomContainer)
        }

    }

    private fun setupBottomSheet(bottomSheet: View, bottomContainer: View) {

        var arrow = bottomSheet.findViewById<ImageView>(R.id.picker_pull_arrow)
        var topBar:View = bottomSheet.findViewById(R.id.picker_top_bar)


        var behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                photoList.alpha = slideOffset
                topBar.alpha = slideOffset
                instantPhotoList.alpha = 1 - slideOffset
                arrow.alpha = 1 - slideOffset
                bottomContainer.alpha = 1 - slideOffset

                if(slideOffset == 1.0f && instantPhotoList.isVisible){
                    instantPhotoList.visibility = View.GONE
                    arrow.visibility = View.GONE
                    bottomContainer.visibility = View.GONE
                }else if(instantPhotoList.isGone && slideOffset < 1.0f) {
                    instantPhotoList.visibility = View.VISIBLE
                    arrow.visibility = View.VISIBLE
                    bottomContainer.visibility = View.VISIBLE
                }

                if (slideOffset > 0 && photoList.visibility == View.INVISIBLE) {
                    photoList.visibility = View.VISIBLE
                    topBar.visibility = View.VISIBLE

                } else if (photoList.visibility == View.VISIBLE && slideOffset == 0f) {
                    photoList.visibility = View.INVISIBLE
                    topBar.visibility = View.GONE
                }
            }
        })
    }


    override fun onResume() {
        super.onResume()
        camera?.open()
        Log.i("PickerFragment", "onResume");
    }

    override fun onPause() {
        super.onPause()
        camera?.close()
        Log.i("PickerFragment", "onPause");
    }

    override fun onStart() {
        super.onStart()
        Log.i("PickerFragment", "onStart");
    }

    override fun onStop() {
        super.onStop()
        Log.i("PickerFragment", "onStop");
    }

    override fun onDestroyView() {
        super.onDestroyView()
        camera?.destroy()
    }

    companion object {
        private const val OPTIONS = "options"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PickerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(options: Options) =
                PickerFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(OPTIONS, options)
                    }
                }
    }
}