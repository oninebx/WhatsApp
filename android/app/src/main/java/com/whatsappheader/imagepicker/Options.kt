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

    var isFacingFront = false
        private set

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