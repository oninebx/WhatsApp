package com.whatsappheader.imagepicker

class test {

}

//package com.fxn.pix
//
//import android.animation.Animator
//import android.database.Cursor
//import android.graphics.Color
//import android.net.Uri
//import android.os.Environment
//import android.os.Handler
//import android.view.View
//import android.view.animation.Animation
//import android.widget.ImageView
//import android.widget.ProgressBar
//import androidx.appcompat.app.AppCompatActivity
//import androidx.coordinatorlayout.widget.CoordinatorLayout
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentActivity
//import com.fxn.adapters.InstantImageAdapter
//import com.google.android.material.bottomsheet.BottomSheetBehavior
//import com.otaliastudios.cameraview.controls.Audio
//import com.otaliastudios.cameraview.controls.Mode
//import java.io.File
//import java.text.SimpleDateFormat
//import java.util.*
//
//class Pix : AppCompatActivity(), OnTouchListener {
//    private var camera: CameraView? = null
//    private var status_bar_height = 0
//
//    //private int BottomBarHeight = 0;
//    private var colorPrimaryDark = 0
//    private var zoom = 0.0f
//    private var dist = 0.0f
//    private val handler = Handler()
//    private val video_counter_handler = Handler()
//    private var video_counter_runnable: Runnable? = null
//    private val mFastScrollStateChangeListener: FastScrollStateChangeListener? = null
//    private var recyclerView: RecyclerView? = null
//    private var instantRecyclerView: RecyclerView? = null
//    private var mBottomSheetBehavior: BottomSheetBehavior<*>? = null
//    private var initaliseadapter: InstantImageAdapter? = null
//    private var status_bar_bg: View? = null
//    private var mScrollbar: View? = null
//    private var topbar: View? = null
//    private var bottomButtons: View? = null
//    private var sendButton: View? = null
//    private var mBubbleView: TextView? = null
//    private var img_count: TextView? = null
//    private var mHandleView: ImageView? = null
//    private var selection_back: ImageView? = null
//    private var selection_check: ImageView? = null
//    private var video_counter_progressbar: ProgressBar? = null
//    private var mScrollbarAnimator: ViewPropertyAnimator? = null
//    private var mBubbleAnimator: ViewPropertyAnimator? = null
//    private val selectionList: MutableSet<Img> = HashSet<Img>()
//    private val mScrollbarHider = Runnable { hideScrollbar() }
//    private var mainImageAdapter: MainImageAdapter? = null
//    private var mViewHeight = 0f
//    private val mHideScrollbar = true
//    private var LongSelection = false
//    private var options: Options? = null
//    private var selection_count: TextView? = null
//    private val mScrollListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
//        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//            if (!mHandleView!!.isSelected && recyclerView.isEnabled()) {
//                setViewPositions(getScrollProportion(recyclerView))
//            }
//        }
//
//        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//            super.onScrollStateChanged(recyclerView, newState)
//            if (recyclerView.isEnabled()) {
//                when (newState) {
//                    RecyclerView.SCROLL_STATE_DRAGGING -> {
//                        handler.removeCallbacks(mScrollbarHider)
//                        if (mScrollbar!!.visibility != View.VISIBLE) {
//                            Utility.cancelAnimation(mScrollbarAnimator)
//                            if (!Utility.isViewVisible(mScrollbar) && (recyclerView.computeVerticalScrollRange()
//                                            - mViewHeight > 0)) {
//                                mScrollbarAnimator = Utility.showScrollbar(mScrollbar, this@Pix)
//                            }
//                        }
//                    }
//                    RecyclerView.SCROLL_STATE_IDLE -> if (mHideScrollbar && !mHandleView!!.isSelected) {
//                        handler.postDelayed(mScrollbarHider, sScrollbarHideDelay.toLong())
//                    }
//                    else -> {
//                    }
//                }
//            }
//        }
//    }
//    private var flash: FrameLayout? = null
//    private var front: ImageView? = null
//    private var clickme: ImageView? = null
//    private var flashDrawable = 0
//    private val onCameraTouchListner: OnTouchListener = object : OnTouchListener {
//        override fun onTouch(v: View, event: MotionEvent): Boolean {
//            if (event.getPointerCount() > 1) {
//                when (event.getAction() and MotionEvent.ACTION_MASK) {
//                    MotionEvent.ACTION_POINTER_DOWN -> dist = Utility.getFingerSpacing(event)
//                    MotionEvent.ACTION_MOVE -> {
//                        val maxZoom = 1f
//                        val newDist: Float = Utility.getFingerSpacing(event)
//                        if (newDist > dist) {
//                            //zoom in
//                            if (zoom < maxZoom) {
//                                zoom = zoom + 0.01f
//                            }
//                        } else if (newDist < dist && zoom > 0) {
//                            //zoom out
//                            zoom = zoom - 0.01f
//                        }
//                        dist = newDist
//                        camera.setZoom(zoom)
//                    }
//                    else -> {
//                    }
//                }
//            }
//            return false
//        }
//    }
//    private val onSelectionListener: OnSelectionListener = object : OnSelectionListener() {
//        fun onClick(img: Img, view: View?, position: Int) {
//            if (LongSelection) {
//                if (selectionList.contains(img)) {
//                    selectionList.remove(img)
//                    initaliseadapter.select(false, position)
//                    mainImageAdapter.select(false, position)
//                } else {
//                    if (options.getCount() <= selectionList.size) {
//                        Toast.makeText(this@Pix, String.format(resources.getString(R.string.selection_limiter_pix),
//                                selectionList.size), Toast.LENGTH_SHORT).show()
//                        return
//                    }
//                    img.setPosition(position)
//                    selectionList.add(img)
//                    initaliseadapter.select(true, position)
//                    mainImageAdapter.select(true, position)
//                }
//                if (selectionList.size == 0) {
//                    LongSelection = false
//                    selection_check!!.visibility = View.VISIBLE
//                    DrawableCompat.setTint(selection_back!!.drawable, colorPrimaryDark)
//                    topbar!!.setBackgroundColor(Color.parseColor("#ffffff"))
//                    val anim: Animation = ScaleAnimation(
//                            1f, 0f,  // Start and end values for the X axis scaling
//                            1f, 0f,  // Start and end values for the Y axis scaling
//                            Animation.RELATIVE_TO_SELF, 0.5f,  // Pivot point of X scaling
//                            Animation.RELATIVE_TO_SELF, 0.5f) // Pivot point of Y scaling
//                    anim.fillAfter = true // Needed to keep the result of the animation
//                    anim.duration = 300
//                    anim.setAnimationListener(object : Animation.AnimationListener {
//                        override fun onAnimationStart(animation: Animation) {}
//                        override fun onAnimationEnd(animation: Animation) {
//                            sendButton!!.visibility = View.GONE
//                            sendButton!!.clearAnimation()
//                        }
//
//                        override fun onAnimationRepeat(animation: Animation) {}
//                    })
//                    sendButton!!.startAnimation(anim)
//                }
//                selection_count.setText(selectionList.size.toString() + " " +
//                        resources.getString(R.string.pix_selected))
//                img_count.setText(selectionList.size.toString())
//            } else {
//                img.setPosition(position)
//                selectionList.add(img)
//                returnObjects()
//                DrawableCompat.setTint(selection_back!!.drawable, colorPrimaryDark)
//                topbar!!.setBackgroundColor(Color.parseColor("#ffffff"))
//            }
//        }
//
//        fun onLongClick(img: Img, view: View?, position: Int) {
//            if (options.getCount() > 1) {
//                Utility.vibe(this@Pix, 50)
//                LongSelection = true
//                if (selectionList.size == 0 && (mBottomSheetBehavior!!.state
//                                != BottomSheetBehavior.STATE_EXPANDED)) {
//                    sendButton!!.visibility = View.VISIBLE
//                    val anim: Animation = ScaleAnimation(
//                            0f, 1f,  // Start and end values for the X axis scaling
//                            0f, 1f,  // Start and end values for the Y axis scaling
//                            Animation.RELATIVE_TO_SELF, 0.5f,  // Pivot point of X scaling
//                            Animation.RELATIVE_TO_SELF, 0.5f) // Pivot point of Y scaling
//                    anim.fillAfter = true // Needed to keep the result of the animation
//                    anim.duration = 300
//                    sendButton!!.startAnimation(anim)
//                }
//                if (selectionList.contains(img)) {
//                    selectionList.remove(img)
//                    initaliseadapter.select(false, position)
//                    mainImageAdapter.select(false, position)
//                } else {
//                    if (options.getCount() <= selectionList.size) {
//                        Toast.makeText(this@Pix, String.format(resources.getString(R.string.selection_limiter_pix),
//                                selectionList.size), Toast.LENGTH_SHORT).show()
//                        return
//                    }
//                    img.setPosition(position)
//                    selectionList.add(img)
//                    initaliseadapter.select(true, position)
//                    mainImageAdapter.select(true, position)
//                }
//                selection_check!!.visibility = View.GONE
//                topbar!!.setBackgroundColor(colorPrimaryDark)
//                selection_count.setText(selectionList.size.toString() + " " + resources.getString(R.string.pix_selected))
//                img_count.setText(selectionList.size.toString())
//                DrawableCompat.setTint(selection_back!!.drawable, Color.parseColor("#ffffff"))
//            }
//        }
//    }
//    private var video_counter_progress = 0
//    private fun hideScrollbar() {
//        val transX = resources.getDimensionPixelSize(R.dimen.fastscroll_scrollbar_padding_end).toFloat()
//        mScrollbarAnimator = mScrollbar!!.animate().translationX(transX).alpha(0f)
//                .setDuration(Constants.sScrollbarAnimDuration)
//                .setListener(object : AnimatorListenerAdapter() {
//                    override fun onAnimationEnd(animation: Animator) {
//                        super.onAnimationEnd(animation)
//                        mScrollbar!!.visibility = View.GONE
//                        mScrollbarAnimator = null
//                    }
//
//                    override fun onAnimationCancel(animation: Animator) {
//                        super.onAnimationCancel(animation)
//                        mScrollbar!!.visibility = View.GONE
//                        mScrollbarAnimator = null
//                    }
//                })
//    }
//
//    fun returnObjects() {
//        val list = ArrayList<String>()
//        for (i in selectionList) {
//            list.add(i.getUrl())
//            // Log.e("Pix images", "img " + i.getUrl());
//        }
//        val resultIntent = Intent()
//        resultIntent.putStringArrayListExtra(IMAGE_RESULTS, list)
//        setResult(Activity.RESULT_OK, resultIntent)
//        finish()
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Utility.setupStatusBarHidden(this)
//        Utility.hideStatusBar(this)
//        setContentView(R.layout.activity_main_lib)
//        initialize()
//    }
//
//    override fun onRestart() {
//        super.onRestart()
//        camera.open()
//        camera.setMode(Mode.PICTURE)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        camera.open()
//        camera.setMode(Mode.PICTURE)
//    }
//
//    override fun onPause() {
//        camera.close()
//        super.onPause()
//    }
//
//    private fun initialize() {
//        val params: WindowManager.LayoutParams = window.attributes
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            params.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
//        }
//        Utility.getScreenSize(this)
//        if (supportActionBar != null) {
//            supportActionBar!!.hide()
//        }
//        try {
//            options = intent.getSerializableExtra(OPTIONS) as Options
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        maxVideoDuration = options.getVideoDurationLimitinSeconds() * 1000 //conversion in  milli seconds
//        var modeText: Int = R.string.pix_bottom_message_with_video
//        if (options.getMode() === Options.Mode.Picture) {
//            modeText = R.string.pix_bottom_message_without_video
//        } else if (options.getMode() === Options.Mode.Video) {
//            modeText = R.string.pix_bottom_message_with_only_video
//        }
//        (findViewById<View>(R.id.message_bottom) as TextView).setText(modeText)
//        status_bar_height = Utility.getStatusBarSizePort(this@Pix)
//        requestedOrientation = options.getScreenOrientation()
//        colorPrimaryDark = ResourcesCompat.getColor(resources, R.color.colorPrimaryPix, theme)
//        camera = findViewById<CameraView>(R.id.camera_view)
//        camera.setMode(Mode.PICTURE)
//        if (options.getMode() === Options.Mode.Picture) {
//            camera.setAudio(Audio.OFF)
//        }
//        val width: SizeSelector = SizeSelectors.minWidth(Utility.WIDTH)
//        val height: SizeSelector = SizeSelectors.minHeight(Utility.HEIGHT)
//        val dimensions: SizeSelector = SizeSelectors.and(width, height) // Matches sizes bigger than width X height
//        val ratio: SizeSelector = SizeSelectors.aspectRatio(AspectRatio.of(1, 2), 0f) // Matches 1:2 sizes.
//        val ratio3: SizeSelector = SizeSelectors.aspectRatio(AspectRatio.of(2, 3), 0f) // Matches 2:3 sizes.
//        val ratio2: SizeSelector = SizeSelectors.aspectRatio(AspectRatio.of(9, 16), 0f) // Matches 9:16 sizes.
//        val result: SizeSelector = SizeSelectors.or(
//                SizeSelectors.and(ratio, dimensions),
//                SizeSelectors.and(ratio2, dimensions),
//                SizeSelectors.and(ratio3, dimensions)
//        )
//        camera.setPictureSize(result)
//        camera.setVideoSize(result)
//        camera.setLifecycleOwner(this@Pix)
//        if (options.isFrontfacing()) {
//            camera.setFacing(Facing.FRONT)
//        } else {
//            camera.setFacing(Facing.BACK)
//        }
//        camera.addCameraListener(object : CameraListener() {
//            override fun onPictureTaken(result: PictureResult) {
//                var dir: File? = null
//                dir = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                    getExternalFilesDir(options.getPath())
//                } else {
//                    Environment.getExternalStoragePublicDirectory(options.getPath())
//                }
//                if (!dir!!.exists()) {
//                    dir.mkdirs()
//                }
//
//                // Log.e("result", "->" + dir.getAbsolutePath());
//                val photo = File(dir, "IMG_"
//                        + SimpleDateFormat("yyyyMMdd_HHmmSS", Locale.ENGLISH).format(Date())
//                        + ".jpg")
//                result.toFile(photo, object : FileCallback {
//                    override fun onFileReady(photo: File?) {
//                        Utility.vibe(this@Pix, 50)
//                        val img = Img("", "", photo!!.absolutePath, "", 1)
//                        selectionList.add(img)
//                        //Log.e("result photo", "->" + photo.getAbsolutePath());
//                        Utility.scanPhoto(this@Pix, photo)
//                        returnObjects()
//                    }
//                })
//            }
//
//            override fun onVideoTaken(result: VideoResult) {
//                // A Video was taken!
//                Utility.vibe(this@Pix, 50)
//                val img = Img("", "", result.getFile().getAbsolutePath(), "", 3)
//                selectionList.add(img)
//                Utility.scanPhoto(this@Pix, result.getFile())
//                camera.setMode(Mode.PICTURE)
//                returnObjects()
//            }
//
//            override fun onVideoRecordingStart() {
//                findViewById<View>(R.id.video_counter_layout).visibility = View.VISIBLE
//                video_counter_progress = 0
//                video_counter_progressbar!!.progress = 0
//                video_counter_runnable = object : Runnable {
//                    override fun run() {
//                        ++video_counter_progress
//                        video_counter_progressbar!!.progress = video_counter_progress
//                        val textView: TextView = findViewById<TextView>(R.id.video_counter)
//                        var counter = ""
//                        var min = 0
//                        var sec = "" + video_counter_progress
//                        if (video_counter_progress > 59) {
//                            min = video_counter_progress / 60
//                            sec = "" + (video_counter_progress - 60 * min)
//                        }
//                        if (sec.length == 1) {
//                            sec = "0$sec"
//                        }
//                        counter = "$min:$sec"
//                        textView.setText(counter)
//                        video_counter_handler.postDelayed(this, 1000)
//                    }
//                }
//                video_counter_handler.postDelayed(video_counter_runnable, 1000)
//                clickme!!.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).setInterpolator(AccelerateDecelerateInterpolator()).start()
//                flash.animate().alpha(0f).setDuration(300).setInterpolator(AccelerateDecelerateInterpolator()).start()
//                findViewById<View>(R.id.message_bottom).animate().alpha(0f).setDuration(300).setInterpolator(AccelerateDecelerateInterpolator()).start()
//                front!!.animate().alpha(0f).setDuration(300).setInterpolator(AccelerateDecelerateInterpolator()).start()
//            }
//
//            override fun onVideoRecordingEnd() {
//                findViewById<View>(R.id.video_counter_layout).visibility = View.GONE
//                video_counter_handler.removeCallbacks(video_counter_runnable)
//                clickme!!.animate().scaleX(1f).scaleY(1f).setDuration(300).setInterpolator(AccelerateDecelerateInterpolator()).start()
//                findViewById<View>(R.id.message_bottom).animate().scaleX(1f).scaleY(1f).setDuration(300).setInterpolator(AccelerateDecelerateInterpolator()).start()
//                flash.animate().alpha(1f).setDuration(300).setInterpolator(AccelerateDecelerateInterpolator()).start()
//                front!!.animate().alpha(1f).setDuration(300).setInterpolator(AccelerateDecelerateInterpolator()).start()
//            } // And much more
//        })
//        zoom = 0.0f
//        flash = findViewById<FrameLayout>(R.id.flash)
//        clickme = findViewById(R.id.clickme)
//        front = findViewById(R.id.front)
//        topbar = findViewById(R.id.topbar)
//        video_counter_progressbar = findViewById(R.id.video_pbr)
//        selection_count = findViewById<TextView>(R.id.selection_count)
//        selection_back = findViewById(R.id.selection_back)
//        selection_check = findViewById(R.id.selection_check)
//        selection_check.setVisibility(if (options.getCount() > 1) View.VISIBLE else View.GONE)
//        sendButton = findViewById(R.id.sendButton)
//        img_count = findViewById<TextView>(R.id.img_count)
//        mBubbleView = findViewById<TextView>(R.id.fastscroll_bubble)
//        mHandleView = findViewById(R.id.fastscroll_handle)
//        mScrollbar = findViewById(R.id.fastscroll_scrollbar)
//        mScrollbar.setVisibility(View.GONE)
//        mBubbleView.setVisibility(View.GONE)
//        bottomButtons = findViewById(R.id.bottomButtons)
//        TOPBAR_HEIGHT = Utility.convertDpToPixel(56, this@Pix)
//        status_bar_bg = findViewById(R.id.status_bar_bg)
//        status_bar_bg.getLayoutParams().height = status_bar_height
//        status_bar_bg.setTranslationY((-1 * status_bar_height).toFloat())
//        status_bar_bg.requestLayout()
//        instantRecyclerView = findViewById<RecyclerView>(R.id.instantRecyclerView)
//        val linearLayoutManager = LinearLayoutManager(this)
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL)
//        instantRecyclerView.setLayoutManager(linearLayoutManager)
//        initaliseadapter = InstantImageAdapter(this)
//        initaliseadapter.addOnSelectionListener(onSelectionListener)
//        instantRecyclerView.setAdapter(initaliseadapter)
//        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        recyclerView.addOnScrollListener(mScrollListener)
//        val mainFrameLayout: FrameLayout = findViewById<FrameLayout>(R.id.mainFrameLayout)
//        val main_content = findViewById<CoordinatorLayout>(R.id.main_content)
//        //BottomBarHeight = Utility.getSoftButtonsBarSizePort(this);
//        val lp1: FrameLayout.LayoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
//                FrameLayout.LayoutParams.MATCH_PARENT)
//        lp1.setMargins(0, status_bar_height, 0, 0)
//        main_content.layoutParams = lp1
//        val layoutParams: FrameLayout.LayoutParams = sendButton.getLayoutParams() as FrameLayout.LayoutParams
//        layoutParams.setMargins(0, 0, Utility.convertDpToPixel(16, this) as Int,
//                Utility.convertDpToPixel(174, this) as Int)
//        sendButton.setLayoutParams(layoutParams)
//        mainImageAdapter = MainImageAdapter(this, options.getSpanCount())
//        val mLayoutManager = GridLayoutManager(this, MainImageAdapter.SPAN_COUNT)
//        mLayoutManager.setSpanSizeLookup(object : SpanSizeLookup() {
//            override fun getSpanSize(position: Int): Int {
//                return if (mainImageAdapter.getItemViewType(position) === MainImageAdapter.HEADER) {
//                    MainImageAdapter.SPAN_COUNT
//                } else 1
//            }
//        })
//        recyclerView.setLayoutManager(mLayoutManager)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.setItemViewCacheSize(20)
//        recyclerView.setDrawingCacheEnabled(true)
//        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH)
//        mainImageAdapter.addOnSelectionListener(onSelectionListener)
//        mainImageAdapter.setHasStableIds(true)
//        recyclerView.setAdapter(mainImageAdapter)
//        recyclerView.addItemDecoration(HeaderItemDecoration(this, mainImageAdapter))
//        mHandleView.setOnTouchListener(this)
//        onClickMethods()
//        flashDrawable = R.drawable.ic_flash_off_black_24dp
//        if (options.getPreSelectedUrls().size() > options.getCount()) {
//            val large: Int = options.getPreSelectedUrls().size() - 1
//            val small: Int = options.getCount()
//            for (i in large downTo small - 1 + 1) {
//                options.getPreSelectedUrls().remove(i)
//            }
//        }
//        DrawableCompat.setTint(selection_back.getDrawable(), colorPrimaryDark)
//        updateImages()
//    }
//
//    private fun onClickMethods() {
//        clickme!!.setOnTouchListener(object : OnTouchListener {
//            override fun onTouch(v: View, event: MotionEvent): Boolean {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    findViewById<View>(R.id.clickmebg).visibility = View.GONE
//                    findViewById<View>(R.id.clickmebg).animate().scaleX(1f).scaleY(1f).setDuration(300).setInterpolator(AccelerateDecelerateInterpolator()).start()
//                    clickme!!.animate().scaleX(1f).scaleY(1f).setDuration(300).setInterpolator(AccelerateDecelerateInterpolator()).start()
//                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    findViewById<View>(R.id.clickmebg).visibility = View.VISIBLE
//                    findViewById<View>(R.id.clickmebg).animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).setInterpolator(AccelerateDecelerateInterpolator()).start()
//                    clickme!!.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).setInterpolator(AccelerateDecelerateInterpolator()).start()
//                }
//                if (event.getAction() == MotionEvent.ACTION_UP && camera.isTakingVideo()) {
//                    camera.stopVideo()
//                }
//                return false
//            }
//        })
//        clickme!!.setOnLongClickListener(object : OnLongClickListener {
//            override fun onLongClick(v: View): Boolean {
//                if (options.getMode() === Options.Mode.Picture) {
//                    return false
//                }
//                camera.setMode(Mode.VIDEO)
//                var dir: File? = null
//                dir = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                    getExternalFilesDir(options.getPath())
//                } else {
//                    Environment.getExternalStoragePublicDirectory(options.getPath())
//                }
//                if (!dir!!.exists()) {
//                    dir.mkdirs()
//                }
//                val video = File(dir, "VID_"
//                        + SimpleDateFormat("yyyyMMdd_HHmmSS", Locale.ENGLISH).format(Date())
//                        + ".mp4")
//                video_counter_progressbar!!.max = maxVideoDuration / 1000
//                video_counter_progressbar!!.invalidate()
//                camera.takeVideo(video, maxVideoDuration)
//                return true
//            }
//        })
//        clickme!!.setOnClickListener(View.OnClickListener {
//            if (selectionList.size >= options.getCount()) {
//                Toast.makeText(this@Pix,
//                        java.lang.String.format(resources.getString(R.string.cannot_click_image_pix),
//                                "" + options.getCount()), Toast.LENGTH_LONG).show()
//                return@OnClickListener
//            }
//            if (camera.getMode() == Mode.VIDEO) {
//                return@OnClickListener
//            }
//            if (options.getMode() === Options.Mode.Video) {
//                return@OnClickListener
//            }
//            val oj: ObjectAnimator = ObjectAnimator.ofFloat(camera, "alpha", 1f, 0f, 0f, 1f)
//            oj.setStartDelay(200L)
//            oj.setDuration(600L)
//            oj.start()
//            camera.takePicture()
//            return@OnClickListener
//        })
//        findViewById<View>(R.id.selection_ok).setOnClickListener { returnObjects() }
//        sendButton!!.setOnClickListener { returnObjects() }
//        selection_back!!.setOnClickListener { mBottomSheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED }
//        selection_check!!.setOnClickListener {
//            topbar!!.setBackgroundColor(colorPrimaryDark)
//            selection_count.setText(resources.getString(R.string.pix_tap_to_select))
//            img_count.setText(selectionList.size.toString())
//            DrawableCompat.setTint(selection_back!!.drawable, Color.parseColor("#ffffff"))
//            LongSelection = true
//            selection_check!!.visibility = View.GONE
//        }
//        val iv = flash.getChildAt(0) as ImageView
//        flash.setOnClickListener(View.OnClickListener {
//            val height: Int = flash.getHeight()
//            iv.animate()
//                    .translationY(height.toFloat())
//                    .setDuration(100)
//                    .setListener(object : AnimatorListenerAdapter() {
//                        override fun onAnimationEnd(animation: Animator) {
//                            super.onAnimationEnd(animation)
//                            iv.translationY = -(height / 2).toFloat()
//                            if (flashDrawable == R.drawable.ic_flash_auto_black_24dp) {
//                                flashDrawable = R.drawable.ic_flash_off_black_24dp
//                                iv.setImageResource(flashDrawable)
//                                camera.setFlash(Flash.OFF)
//                            } else if (flashDrawable == R.drawable.ic_flash_off_black_24dp) {
//                                flashDrawable = R.drawable.ic_flash_on_black_24dp
//                                iv.setImageResource(flashDrawable)
//                                camera.setFlash(Flash.ON)
//                            } else {
//                                flashDrawable = R.drawable.ic_flash_auto_black_24dp
//                                iv.setImageResource(flashDrawable)
//                                camera.setFlash(Flash.AUTO)
//                            }
//                            iv.animate().translationY(0f).setDuration(50).setListener(null).start()
//                        }
//                    })
//                    .start()
//        })
//        front!!.setOnClickListener {
//            val oa1: ObjectAnimator = ObjectAnimator.ofFloat(front, "scaleX", 1f, 0f).setDuration(150)
//            val oa2: ObjectAnimator = ObjectAnimator.ofFloat(front, "scaleX", 0f, 1f).setDuration(150)
//            oa1.addListener(object : AnimatorListenerAdapter() {
//                override fun onAnimationEnd(animation: Animator) {
//                    super.onAnimationEnd(animation)
//                    front!!.setImageResource(R.drawable.ic_photo_camera)
//                    oa2.start()
//                }
//            })
//            oa1.start()
//            if (options.isFrontfacing()) {
//                options.setFrontfacing(false)
//                camera.setFacing(Facing.BACK)
//            } else {
//                camera.setFacing(Facing.FRONT)
//                options.setFrontfacing(true)
//            }
//        }
//    }
//
//    private fun updateImages() {
//        mainImageAdapter.clearList()
//        val cursor: Cursor = Utility.getImageVideoCursor(this@Pix, options.getMode())
//                ?: return
//        val INSTANTLIST: ArrayList<Img> = ArrayList<Img>()
//        var header = ""
//        var limit = 100
//        if (cursor.count < limit) {
//            limit = cursor.count - 1
//        }
//        val data = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA)
//        val mediaType = cursor.getColumnIndex(MediaStore.Files.FileColumns.MEDIA_TYPE)
//        val contentUrl = cursor.getColumnIndex(MediaStore.Files.FileColumns._ID)
//        //int videoDate = cursor.getColumnIndex(MediaStore.Video.Media.DATE_TAKEN);
//        val imageDate = cursor.getColumnIndex(MediaStore.Images.Media.DATE_MODIFIED)
//        var calendar: Calendar
//        var pos = 0
//        for (i in 0 until limit) {
//            cursor.moveToNext()
//            val path = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                    "" + cursor.getInt(contentUrl))
//            calendar = Calendar.getInstance()
//            calendar.timeInMillis = cursor.getLong(imageDate) * 1000
//            //Log.e("time",i+"->"+new SimpleDateFormat("hh:mm:ss dd/MM/yyyy",Locale.ENGLISH).format(calendar.getTime()));
//            val dateDifference: String = Utility.getDateDifference(this@Pix, calendar)
//            if (!header.equals("" + dateDifference, ignoreCase = true)) {
//                header = "" + dateDifference
//                pos += 1
//                INSTANTLIST.add(Img("" + dateDifference, "", "", "", cursor.getInt(mediaType)))
//            }
//            val img = Img("" + header, "" + path, cursor.getString(data), "" + pos, cursor.getInt(mediaType))
//            img.setPosition(pos)
//            if (options.getPreSelectedUrls().contains(img.getUrl())) {
//                img.setSelected(true)
//                selectionList.add(img)
//            }
//            pos += 1
//            INSTANTLIST.add(img)
//        }
//        if (selectionList.size > 0) {
//            LongSelection = true
//            sendButton!!.visibility = View.VISIBLE
//            val anim: Animation = ScaleAnimation(
//                    0f, 1f,  // Start and end values for the X axis scaling
//                    0f, 1f,  // Start and end values for the Y axis scaling
//                    Animation.RELATIVE_TO_SELF, 0.5f,  // Pivot point of X scaling
//                    Animation.RELATIVE_TO_SELF, 0.5f) // Pivot point of Y scaling
//            anim.fillAfter = true // Needed to keep the result of the animation
//            anim.duration = 300
//            sendButton!!.startAnimation(anim)
//            selection_check!!.visibility = View.GONE
//            topbar!!.setBackgroundColor(colorPrimaryDark)
//            selection_count.setText(selectionList.size.toString() + " " +
//                    resources.getString(R.string.pix_selected))
//            img_count.setText(selectionList.size.toString())
//            DrawableCompat.setTint(selection_back!!.drawable, Color.parseColor("#ffffff"))
//        }
//        mainImageAdapter.addImageList(INSTANTLIST)
//        initaliseadapter.addImageList(INSTANTLIST)
//        imageVideoFetcher = object : ImageVideoFetcher(this@Pix) {
//            protected fun onPostExecute(modelList: ModelList) {
//                super.onPostExecute(modelList)
//                mainImageAdapter.addImageList(modelList.getLIST())
//                initaliseadapter.addImageList(modelList.getLIST())
//                selectionList.addAll(modelList.getSelection())
//                if (selectionList.size > 0) {
//                    LongSelection = true
//                    sendButton!!.visibility = View.VISIBLE
//                    val anim: Animation = ScaleAnimation(
//                            0f, 1f,  // Start and end values for the X axis scaling
//                            0f, 1f,  // Start and end values for the Y axis scaling
//                            Animation.RELATIVE_TO_SELF, 0.5f,  // Pivot point of X scaling
//                            Animation.RELATIVE_TO_SELF, 0.5f) // Pivot point of Y scaling
//                    anim.fillAfter = true // Needed to keep the result of the animation
//                    anim.duration = 300
//                    sendButton!!.startAnimation(anim)
//                    selection_check!!.visibility = View.GONE
//                    topbar!!.setBackgroundColor(colorPrimaryDark)
//                    selection_count.setText(selectionList.size.toString() + " " +
//                            resources.getString(R.string.pix_selected))
//                    img_count.setText(selectionList.size.toString())
//                    DrawableCompat.setTint(selection_back!!.drawable, Color.parseColor("#ffffff"))
//                }
//            }
//        }
//        imageVideoFetcher.setStartingCount(pos)
//        imageVideoFetcher.header = header
//        imageVideoFetcher.setPreSelectedUrls(options.getPreSelectedUrls())
//        imageVideoFetcher.execute(Utility.getImageVideoCursor(this@Pix, options.getMode()))
//        cursor.close()
//        setBottomSheetBehavior()
//    }
//
//    private fun setBottomSheetBehavior() {
//        val bottomSheet = findViewById<View>(R.id.bottom_sheet)
//        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
//        mBottomSheetBehavior.setPeekHeight(Utility.convertDpToPixel(194, this) as Int)
//        mBottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//            override fun onStateChanged(bottomSheet: View, newState: Int) {}
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                Utility.manipulateVisibility(this@Pix, slideOffset, findViewById(R.id.arrow_up),
//                        instantRecyclerView, recyclerView, status_bar_bg,
//                        topbar, bottomButtons, sendButton, LongSelection)
//                if (slideOffset == 1f) {
//                    Utility.showScrollbar(mScrollbar, this@Pix)
//                    mainImageAdapter.notifyDataSetChanged()
//                    mViewHeight = mScrollbar!!.measuredHeight.toFloat()
//                    handler.post { setViewPositions(getScrollProportion(recyclerView)) }
//                    sendButton!!.visibility = View.GONE
//                    //  fotoapparat.stop();
//                } else if (slideOffset == 0f) {
//                    initaliseadapter.notifyDataSetChanged()
//                    hideScrollbar()
//                    img_count.setText(selectionList.size.toString())
//                    camera.open()
//                }
//            }
//        })
//    }
//
//    private fun getScrollProportion(recyclerView: RecyclerView?): Float {
//        val verticalScrollOffset: Int = recyclerView.computeVerticalScrollOffset()
//        val verticalScrollRange: Int = recyclerView.computeVerticalScrollRange()
//        val rangeDiff = verticalScrollRange - mViewHeight
//        val proportion = verticalScrollOffset.toFloat() / if (rangeDiff > 0) rangeDiff else 1f
//        return mViewHeight * proportion
//    }
//
//    private fun setViewPositions(y: Float) {
//        val handleY: Int = Utility.getValueInRange(0, (mViewHeight - mHandleView!!.height).toInt(),
//                (y - mHandleView!!.height / 2).toInt())
//        mBubbleView.setY(handleY + Utility.convertDpToPixel(60, this))
//        mHandleView!!.y = handleY.toFloat()
//    }
//
//    private fun setRecyclerViewPosition(y: Float) {
//        if (recyclerView != null && recyclerView.getAdapter() != null) {
//            val itemCount: Int = recyclerView.getAdapter().getItemCount()
//            val proportion: Float
//            proportion = if (mHandleView!!.y == 0f) {
//                0f
//            } else if (mHandleView!!.y + mHandleView!!.height >= mViewHeight - sTrackSnapRange) {
//                1f
//            } else {
//                y / mViewHeight
//            }
//            val scrolledItemCount = Math.round(proportion * itemCount)
//            val targetPos: Int = Utility.getValueInRange(0, itemCount - 1, scrolledItemCount)
//            recyclerView.getLayoutManager().scrollToPosition(targetPos)
//            if (mainImageAdapter != null) {
//                val text: String = mainImageAdapter.getSectionMonthYearText(targetPos)
//                mBubbleView.setText(text)
//                if (text.equals("", ignoreCase = true)) {
//                    mBubbleView.setVisibility(View.GONE)
//                }
//            }
//        }
//    }
//
//    private fun showBubble() {
//        if (!Utility.isViewVisible(mBubbleView)) {
//            mBubbleView.setVisibility(View.VISIBLE)
//            mBubbleView.setAlpha(0f)
//            mBubbleAnimator = mBubbleView
//                    .animate()
//                    .alpha(1f)
//                    .setDuration(sBubbleAnimDuration.toLong())
//                    .setListener(object : AnimatorListenerAdapter() { // adapter required for new alpha value to stick
//                    })
//            mBubbleAnimator.start()
//        }
//    }
//
//    private fun hideBubble() {
//        if (Utility.isViewVisible(mBubbleView)) {
//            mBubbleAnimator = mBubbleView.animate().alpha(0f)
//                    .setDuration(sBubbleAnimDuration.toLong())
//                    .setListener(object : AnimatorListenerAdapter() {
//                        override fun onAnimationEnd(animation: Animator) {
//                            super.onAnimationEnd(animation)
//                            mBubbleView.setVisibility(View.GONE)
//                            mBubbleAnimator = null
//                        }
//
//                        override fun onAnimationCancel(animation: Animator) {
//                            super.onAnimationCancel(animation)
//                            mBubbleView.setVisibility(View.GONE)
//                            mBubbleAnimator = null
//                        }
//                    })
//            mBubbleAnimator.start()
//        }
//    }
//
//    override fun onTouch(view: View, event: MotionEvent): Boolean {
//        when (event.getAction()) {
//            MotionEvent.ACTION_DOWN -> {
//                if (event.getX() < mHandleView!!.x - ViewCompat.getPaddingStart(mHandleView)) {
//                    return false
//                }
//                mHandleView!!.isSelected = true
//                handler.removeCallbacks(mScrollbarHider)
//                Utility.cancelAnimation(mScrollbarAnimator)
//                Utility.cancelAnimation(mBubbleAnimator)
//                if (!Utility.isViewVisible(mScrollbar) && (recyclerView.computeVerticalScrollRange()
//                                - mViewHeight > 0)) {
//                    mScrollbarAnimator = Utility.showScrollbar(mScrollbar, this@Pix)
//                }
//                if (mainImageAdapter != null) {
//                    showBubble()
//                }
//                if (mFastScrollStateChangeListener != null) {
//                    mFastScrollStateChangeListener.onFastScrollStart(this)
//                }
//                val y: Float = event.getRawY()
//                setViewPositions(y - TOPBAR_HEIGHT)
//                setRecyclerViewPosition(y)
//                return true
//            }
//            MotionEvent.ACTION_MOVE -> {
//                val y: Float = event.getRawY()
//                setViewPositions(y - TOPBAR_HEIGHT)
//                setRecyclerViewPosition(y)
//                return true
//            }
//            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
//                mHandleView!!.isSelected = false
//                if (mHideScrollbar) {
//                    handler.postDelayed(mScrollbarHider, sScrollbarHideDelay.toLong())
//                }
//                hideBubble()
//                if (mFastScrollStateChangeListener != null) {
//                    mFastScrollStateChangeListener.onFastScrollStop(this)
//                }
//                return true
//            }
//        }
//        return super.onTouchEvent(event)
//    }
//
//    override fun onBackPressed() {
//        if (selectionList.size > 0) {
//            for (img in selectionList) {
//                options.setPreSelectedUrls(ArrayList<String>())
//                mainImageAdapter.getItemList().get(img.getPosition()).setSelected(false)
//                mainImageAdapter.notifyItemChanged(img.getPosition())
//                initaliseadapter.getItemList().get(img.getPosition()).setSelected(false)
//                initaliseadapter.notifyItemChanged(img.getPosition())
//            }
//            LongSelection = false
//            if (options.getCount() > 1) {
//                selection_check!!.visibility = View.VISIBLE
//            }
//            DrawableCompat.setTint(selection_back!!.drawable, colorPrimaryDark)
//            topbar!!.setBackgroundColor(Color.parseColor("#ffffff"))
//            val anim: Animation = ScaleAnimation(
//                    1f, 0f,  // Start and end values for the X axis scaling
//                    1f, 0f,  // Start and end values for the Y axis scaling
//                    Animation.RELATIVE_TO_SELF, 0.5f,  // Pivot point of X scaling
//                    Animation.RELATIVE_TO_SELF, 0.5f) // Pivot point of Y scaling
//            anim.fillAfter = true // Needed to keep the result of the animation
//            anim.duration = 300
//            anim.setAnimationListener(object : Animation.AnimationListener {
//                override fun onAnimationStart(animation: Animation) {}
//                override fun onAnimationEnd(animation: Animation) {
//                    sendButton!!.visibility = View.GONE
//                    sendButton!!.clearAnimation()
//                }
//
//                override fun onAnimationRepeat(animation: Animation) {}
//            })
//            sendButton!!.startAnimation(anim)
//            selectionList.clear()
//        } else if (mBottomSheetBehavior!!.state == BottomSheetBehavior.STATE_EXPANDED) {
//            mBottomSheetBehavior!!.setState(BottomSheetBehavior.STATE_COLLAPSED)
//        } else {
//            super.onBackPressed()
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        camera.destroy()
//    }
//
//    companion object {
//        private const val sBubbleAnimDuration = 1000
//        private const val sScrollbarHideDelay = 1000
//        private const val OPTIONS = "options"
//        private const val sTrackSnapRange = 5
//        var IMAGE_RESULTS = "image_results"
//        var TOPBAR_HEIGHT = 0f
//        private var maxVideoDuration = 40000
//        private var imageVideoFetcher: ImageVideoFetcher? = null
//        fun start(context: Fragment, options: Options) {
//            PermUtil.checkForCamaraWritePermissions(context, options.getMode(), object : WorkFinish() {
//                fun onWorkFinish(check: Boolean?) {
//                    val i = Intent(context.activity, Pix::class.java)
//                    i.putExtra(OPTIONS, options)
//                    context.startActivityForResult(i, options.getRequestCode())
//                }
//            })
//        }
//
//        fun start(context: Fragment?, requestCode: Int) {
//            start(context, Options.init().setRequestCode(requestCode).setCount(1))
//        }
//
//        fun start(context: FragmentActivity, options: Options) {
//            PermUtil.checkForCamaraWritePermissions(context, options.getMode(), object : WorkFinish() {
//                fun onWorkFinish(check: Boolean?) {
//                    val i = Intent(context, Pix::class.java)
//                    i.putExtra(OPTIONS, options)
//                    context.startActivityForResult(i, options.getRequestCode())
//                }
//            })
//        }
//
//        fun start(context: FragmentActivity?, requestCode: Int) {
//            start(context, Options.init().setRequestCode(requestCode).setCount(1))
//        }
//    }
//}