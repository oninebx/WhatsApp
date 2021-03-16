package com.whatsappheader.imagepicker

import android.provider.MediaStore

/*
 * <pre>
 *     Author: xuchunlei
 *     Contact: hitxcl@gmail.com / FaceBook:Ryan Xu
 *     Time: 2021/03/14
 *     Remark:  
 * </pre>
 */
class Constants private constructor(){
    companion object{
        const val VIDEO_SELECTION = (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)
        const val IMAGE_SELECTION = (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE)
        const val IMAGE_VIDEO_SELECTION = (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)

        var IMAGE_VIDEO_URI = MediaStore.Files.getContentUri("external")

        val IMAGE_VIDEO_PROJECTION = arrayOf(
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.PARENT,
                MediaStore.Files.FileColumns.DISPLAY_NAME,
                MediaStore.Files.FileColumns.DATE_ADDED,
                MediaStore.Files.FileColumns.DATE_MODIFIED,
                MediaStore.Files.FileColumns.MEDIA_TYPE,
                MediaStore.Files.FileColumns.MIME_TYPE,
                MediaStore.Files.FileColumns.TITLE)
        const val IMAGE_VIDEO_ORDERBY = MediaStore.Images.Media.DATE_MODIFIED + " DESC"

        var TAEGET_TAG: Int = -1
    }
}