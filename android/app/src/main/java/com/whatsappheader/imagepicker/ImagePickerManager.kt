package com.whatsappheader.imagepicker

import android.view.Choreographer
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager

/*
 * <pre>
 *     Author: xuchunlei
 *     Contact: hitxcl@gmail.com / FaceBook:Ryan Xu
 *     Time: 2021/03/13
 *     Remark:  
 * </pre>
 */
class ImagePickerManager : ViewGroupManager<FrameLayout> {

    private var context: ReactApplicationContext

    companion object {
        private val NAME = "ImagePicker"
        const val COMMAND_CREATE = 1
    }

    constructor(context: ReactApplicationContext) {
        this.context = context
    }

    override fun getName() = NAME

    override fun createViewInstance(reactContext: ThemedReactContext): FrameLayout {
        return FrameLayout(reactContext)
    }

    override fun getCommandsMap(): MutableMap<String, Int> {
        return MapBuilder.of(
                "create", COMMAND_CREATE
        )
    }

    override fun receiveCommand(root: FrameLayout, commandId: String?, args: ReadableArray?) {
        super.receiveCommand(root, commandId, args)
        var holderId = args?.getInt(0)
        var commandValue = commandId?.toInt()
        when(commandValue){
            COMMAND_CREATE -> holderId?.let { createFragment(root, holderId) }
            else -> {}
        }
    }
    

    private fun createFragment(parent: FrameLayout, holderId: Int){
        var parentView = parent.findViewById<FrameLayout>(holderId).parent as ViewGroup
        setupLayout(parentView)

        val fragment = PickerFragment.newInstance(Options.init())
        (this.context.currentActivity as AppCompatActivity).supportFragmentManager
                .beginTransaction().replace(
                        holderId,
                        fragment,
                        holderId.toString()
                ).commit()
    }

    private fun setupLayout(view: ViewGroup) {
        Choreographer.getInstance().postFrameCallback(object : Choreographer.FrameCallback {
            override fun doFrame(frameTimeNanos: Long) {
                layoutChildren(view)
                view.viewTreeObserver.dispatchOnGlobalLayout()
                Choreographer.getInstance().postFrameCallback(this)
            }
        })
    }

    private fun layoutChildren(view: ViewGroup) {
        for (i in 0 until view.childCount) {
            val child = view.getChildAt(i)
            child.measure(
                    View.MeasureSpec.makeMeasureSpec(view.measuredWidth, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(view.measuredHeight, View.MeasureSpec.EXACTLY)
            )
            child.layout(0, 0, child.measuredWidth, child.measuredHeight)
        }
    }

}