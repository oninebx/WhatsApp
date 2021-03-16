package com.whatsappheader.imagepicker

/*
 * <pre>
 *     Author: xuchunlei
 *     Contact: hitxcl@gmail.com / FaceBook:Ryan Xu
 *     Time: 2021/03/14
 *     Remark:  
 * </pre>
 */
data class Photo(val uri: String, val type: Int){
    var isSelected: Boolean = false
}
