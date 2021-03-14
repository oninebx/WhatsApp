package com.whatsappheader.imagepicker

import android.view.View
import android.widget.FrameLayout
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.ViewManager

/*
 * <pre>
 *     Author: xuchunlei
 *     Contact: hitxcl@gmail.com / FaceBook:Ryan Xu
 *     Time: 2021/03/13
 *     Remark:  
 * </pre>
 */
class ImagePickerPackage :  ReactPackage{
    override fun createNativeModules(reactContext: ReactApplicationContext): MutableList<NativeModule> {
        return mutableListOf()
    }

    override fun createViewManagers(reactContext: ReactApplicationContext): MutableList<ViewGroupManager<FrameLayout>> {
        return mutableListOf(
                ImagePickerManager(reactContext)
        )
    }
}