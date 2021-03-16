package com.whatsappheader.imagepicker

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

import com.whatsappheader.imagepicker.Options.Mode.*

/*
 * <pre>
 *     Author: xuchunlei
 *     Contact: hitxcl@gmail.com / FaceBook:Ryan Xu
 *     Time: 2021/03/14
 *     Remark:  
 * </pre>
 */
class PhotoProvider private constructor(){

    companion object {

        fun getPhotoList(context: Context, mode: Options.Mode?): MutableList<Photo> {
            val result: MutableList<Photo> = mutableListOf()
            val cursor: Cursor? = context?.let { PhotoProvider.getImageVideoCursor(it, mode) }
            var maxCount = cursor?.let { cursor -> if(cursor.count < 50) cursor.count - 1 else 50 } ?: let {0}


            cursor?.let {
                val dataIndex = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA)
                val mediaTypeIndex = cursor.getColumnIndex(MediaStore.Files.FileColumns.MEDIA_TYPE)
                for(i in 0 until maxCount){
                    cursor.moveToNext()
                    val photo = Photo(cursor.getString(dataIndex), cursor.getInt(mediaTypeIndex))
                    result.add(photo)
                }
                cursor.close()
            }
            return result
        }

        private fun getImageVideoCursor(context: Context, mode: Options.Mode?): Cursor? {
            val selection: String = when (mode) {
                VIDEO -> Constants.VIDEO_SELECTION
                PICTURE -> Constants.IMAGE_SELECTION
                ALL -> Constants.IMAGE_VIDEO_SELECTION
                else -> Constants.IMAGE_SELECTION
            }
            return context.contentResolver
                    .query(Constants.IMAGE_VIDEO_URI, Constants.IMAGE_VIDEO_PROJECTION,
                            selection, null, Constants.IMAGE_VIDEO_ORDERBY)
        }
    }

}