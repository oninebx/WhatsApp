package com.whatsappheader.imagepicker

import android.graphics.Region
import java.io.Serializable

/*
 * <pre>
 *     Author: xuchunlei
 *     Contact: hitxcl@gmail.com / FaceBook:Ryan Xu
 *     Time: 2021/03/14
 *     Remark:  
 * </pre>
 */
class Options private constructor(): Serializable {

    enum class Mode {
        ALL,
        PICTURE,
        VIDEO
    }

    var mode = Mode.ALL
    private set

    var isFacingFront = false
        private set


    fun setMode(mode: Mode): Options {
        this.mode = mode
        return this
    }

    fun setFacingFront(value: Boolean) : Options {
        isFacingFront = value
        return this
    }

    companion object {
        fun init() : Options {
            return Options()
        }
    }

}