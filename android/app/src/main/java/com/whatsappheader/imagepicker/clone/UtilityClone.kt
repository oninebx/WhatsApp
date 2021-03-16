package com.whatsappheader.imagepicker.clone

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

/*
 * <pre>
 *     Author: xuchunlei
 *     Contact: hitxcl@gmail.com / FaceBook:Ryan Xu
 *     Time: 2021/03/16
 *     Remark:
 * </pre>
 */
internal object UtilityClone {
    fun manipulateVisibility(activity: AppCompatActivity?, slideOffset: Float, arrow_up: View,
                             instantRecyclerView: RecyclerView, recyclerView: RecyclerView,
                             status_bar_bg: View, topbar: View, clickme: View, sendButton: View, longSelection: Boolean) {
//        instantRecyclerView.alpha = 1 - slideOffset
//        arrow_up.alpha = 1 - slideOffset
//        clickme.alpha = 1 - slideOffset
//        if (longSelection) {
//            sendButton.alpha = 1 - slideOffset
//        }
//        topbar.alpha = slideOffset
//        recyclerView.alpha = slideOffset
//        if (1 - slideOffset == 0f && instantRecyclerView.visibility == View.VISIBLE) {
//            instantRecyclerView.visibility = View.GONE
//            arrow_up.visibility = View.GONE
//            clickme.visibility = View.GONE
//        } else if (instantRecyclerView.visibility == View.GONE && 1 - slideOffset > 0) {
//            instantRecyclerView.visibility = View.VISIBLE
//            arrow_up.visibility = View.VISIBLE
//            clickme.visibility = View.VISIBLE
//            if (longSelection) {
//                sendButton.clearAnimation()
//                sendButton.visibility = View.VISIBLE
//            }
//        }
//        if (slideOffset > 0 && recyclerView.visibility == View.INVISIBLE) {
//            recyclerView.visibility = View.VISIBLE
//            status_bar_bg.animate().translationY(0f).setDuration(200).start()
//            topbar.visibility = View.VISIBLE
//            Utility.showStatusBar(activity)
//        } else if (recyclerView.visibility == View.VISIBLE && slideOffset == 0f) {
//            Utility.hideStatusBar(activity)
//            recyclerView.visibility = View.INVISIBLE
//            topbar.visibility = View.GONE
//            status_bar_bg.animate().translationY(-status_bar_bg.height.toFloat()).setDuration(550).start()
//        }
    }
}