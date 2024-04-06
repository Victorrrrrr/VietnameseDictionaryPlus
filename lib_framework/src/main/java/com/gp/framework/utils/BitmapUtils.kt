package com.gp.framework.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


object BitmapUtils {
//        Bitmap bitmap = ImageUtils.compressBitmap(OcrTranslateActivity.this, currentUri);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        int quality = 100;
//        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
//        byte[] datas = baos.toByteArray();
//        String bases64 = EncryptHelper.getBase64(datas);
//        int count = bases64.length();
//        while (count > 1.4 * 1024 * 1024) {
//            quality = quality - 10;
//            baos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
//            datas = baos.toByteArray();
//            bases64 = EncryptHelper.getBase64(datas);
//        }

    fun bitmapToBase64(bitmap: Bitmap?, isCompress : Boolean): String? {
        var result: String? = null
        var baos: ByteArrayOutputStream? = null
        try {
            if (bitmap != null) {
                baos = ByteArrayOutputStream()
                var quality = 100
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)
                baos.flush()
                baos.close()
                val bitmapBytes = baos.toByteArray()
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT)
                if (isCompress) {
                    var count = result.length
                    while(count > 1.4 * 1024 * 1024 && quality > 50) {
                        quality = quality - 10
                        baos = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)
                        result = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
                        count = result.length
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                if (baos != null) {
                    baos.flush()
                    baos.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return result
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    fun base64ToBitmap(base64Data: String?): Bitmap {
        val bytes: ByteArray = Base64.decode(base64Data, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    /**
     * url转bitmap
     * @param url
     * @return
     */
    fun urlToBitmap(url: String?): Bitmap? {
        val bitmap = arrayOf<Bitmap?>(null)
        Thread {
            var imageurl: URL? = null
            try {
                imageurl = URL(url)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
            try {
                val conn =
                    imageurl!!.openConnection() as HttpURLConnection
                conn.doInput = true
                conn.connect()
                val `is` = conn.inputStream
                bitmap[0] = BitmapFactory.decodeStream(`is`)
                `is`.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
        return bitmap[0]
    }


    fun uriToBitmap(context: Context, uri: Uri?) : Bitmap? {
        val bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri)
        return bitmap
    }


    fun filePathToBitmap(filePath : String) : Bitmap? {
        return BitmapFactory.decodeFile(filePath)
    }
}