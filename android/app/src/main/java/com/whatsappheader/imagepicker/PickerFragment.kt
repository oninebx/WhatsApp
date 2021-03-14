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
import androidx.recyclerview.widget.RecyclerView
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

    private var camera: CameraView? = null
    private var front: ImageView? = null
    private var options: Options? = null

    // photo
    private lateinit var instantPhotoList: RecyclerView
    private var instantAdapter: InstantPhotoAdapter? = null


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

        this.context?.let {
            instantAdapter = InstantPhotoAdapter(it)
            instantAdapter!!.setData(PhotoProvider.getPhotoList(it, options?.mode))
            instantPhotoList.adapter = instantAdapter
        }




    }

    private fun loadPhotos(){
        val instantPhotos: ArrayList<Photo> = ArrayList();

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