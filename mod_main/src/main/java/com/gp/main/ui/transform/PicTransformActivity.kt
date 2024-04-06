package com.gp.main.ui.transform

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.core.os.EnvironmentCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.dp2px
import com.gp.framework.toast.TipsToast
import com.gp.framework.utils.BitmapUtils
import com.gp.framework.utils.CameraUtils
import com.gp.framework.utils.PermissionUtils
import com.gp.lib_widget.databinding.DialogModifyBinding
import com.gp.main.databinding.ActivityPicTransformBinding
import com.gp.main.ui.main.MainViewModel
import com.gp.widget.AlertDialog
import com.youdao.sdk.app.Language
import com.youdao.sdk.ydonlinetranslate.OCRTranslateResult
import com.youdao.sdk.ydonlinetranslate.OcrTranslate
import com.youdao.sdk.ydonlinetranslate.OcrTranslateListener
import com.youdao.sdk.ydonlinetranslate.OcrTranslateParameters
import com.youdao.sdk.ydonlinetranslate.Region
import com.youdao.sdk.ydonlinetranslate.SpeechTranslateHelper.TranslateErrorCode
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class PicTransformActivity : BaseMvvmActivity<ActivityPicTransformBinding, MainViewModel>() {

    companion object{
        const val SELECT_PHOTO_CODE = 2000
        const val TAKE_PHOTO_CODE = 2001
    }

    private var mCameraImagePath : String = ""

    private var mCameraUri : Uri? = null

    private var from : Language? = null
    private var to : Language? = null

    //修改用户信息弹窗
    private var modifyUserInfoDialog: AlertDialog? = null

    override fun initView(savedInstanceState: Bundle?) {

        showTypeDialog()

    }


    /**
     * 显示选择翻译类型弹窗
     */
    private fun showTypeDialog() {
        val dialogModifyBinding: DialogModifyBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            com.gp.lib_widget.R.layout.dialog_modify,
            null,
            false
        )


        val builder = AlertDialog.Builder(this)
            .addDefaultAnimation()
            .setCancelable(false)
            .setContentView(dialogModifyBinding.root)
            .setWidthAndHeight(dp2px( 300f), LinearLayout.LayoutParams.WRAP_CONTENT)
            .setOnClickListener(com.gp.lib_widget.R.id.tv_vie_to_zh) {
                from = Language.VIETNAMESE
                to = Language.CHINESE
                cameraPhoto()
            }.setOnClickListener(com.gp.lib_widget.R.id.tv_zh_to_vie) {
                from = Language.CHINESE
                to = Language.VIETNAMESE
                cameraPhoto()
            }
            .setOnClickListener(com.gp.lib_widget.R.id.tv_back) {
                modifyUserInfoDialog?.dismiss()
                onBackPressed()
            }
        modifyUserInfoDialog = builder.create()
        modifyUserInfoDialog?.show()
    }


    /**
     * 相册拍照
     */
    private fun cameraPhoto() {
        if (!isAndroid6()) {
            openCamera()
            return
        }
        if(!hasPermission(PermissionUtils.CAMERA)) {
            requestPermission(PermissionUtils.CAMERA)
            return
        }
        openCamera()
    }

    /**
     * 调起相机拍照
     */
    private fun openCamera() {
        val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // 判断是否有相机
        if (captureIntent.resolveActivity(packageManager) != null) {
            var photoFile: File? = null
            var photoUri: Uri? = null
            if (isAndroid10()) {
                // 适配android 10 创建图片地址uri,用于保存拍照后的照片 Android 10以后使用这种方法
                photoUri = contentResolver.insert(
                    if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) MediaStore.Images.Media.EXTERNAL_CONTENT_URI else MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                    ContentValues()
                )
            } else {
                photoFile = createImageFile()
                if (photoFile != null) {
                    mCameraImagePath = photoFile.absolutePath
                    photoUri = if (isAndroid7()) {
                        //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
                        FileProvider.getUriForFile(
                            this,
                            "$packageName.fileprovider", photoFile
                        )
                    } else {
                        Uri.fromFile(photoFile)
                    }
                }
            }
            mCameraUri = photoUri
            if (photoUri != null) {
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                startActivityForResult(captureIntent, TAKE_PHOTO_CODE)
            }
        }
    }


    /**
     * 创建保存图片的文件
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun createImageFile(): File? {
        val imageName: String = SimpleDateFormat(
            "yyyyMMdd_HHmmss",
            Locale.getDefault()
        ).format(Date()) + ".jpg"
        //        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File storageDir = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);
        val storageDir = File(
            Environment.getExternalStorageDirectory().absolutePath
                    + File.separator + "Pictures" + File.separator + "abc"
        )
        if (!storageDir.exists()) storageDir.mkdirs()
        val tempFile = File(storageDir, imageName)
        return if (Environment.MEDIA_MOUNTED != EnvironmentCompat.getStorageState(tempFile)) {
            null
        } else tempFile
    }


    /**
     * 权限请求结果
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermissionUtils.REQUEST_STORAGE_CODE -> {
                //文件读写权限
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    TipsToast.showTips("您拒绝了读写文件权限，无法打开相册，抱歉。")
                    return
                }
                openAlbum()
            }

            PermissionUtils.REQUEST_CAMERA_CODE -> {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    TipsToast.showTips("您拒绝了相机权限，无法打开相机，抱歉。");
                    return
                }
                openCamera();
            }

            else -> {}

        }
    }


    /**
     * 页面返回结果
     */
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        modifyUserInfoDialog?.dismiss()
        if (resultCode != RESULT_OK) {
            TipsToast.showTips("未知原因")
            return
        }
        when (requestCode) {
            PermissionUtils.REQUEST_MANAGE_EXTERNAL_STORAGE_CODE -> {
                //从外部存储管理页面返回
                if (!isStorageManager()) {
                    TipsToast.showTips("未打开外部存储管理开关，无法打开相册，抱歉")
                    return
                }
                if (!hasPermission(PermissionUtils.READ_EXTERNAL_STORAGE)) {
                    requestPermission(PermissionUtils.READ_EXTERNAL_STORAGE)
                    return
                }
                //打开相册
                openAlbum()
            }
            TAKE_PHOTO_CODE -> {
                //相机中拍照返回
                var bitmap = if(!isAndroid10()) {
                    BitmapUtils.filePathToBitmap(mCameraImagePath)
                } else {
                    BitmapUtils.uriToBitmap(this, mCameraUri)
                }
                val bitmapOrigin= bitmap?.copy(Bitmap.Config.ARGB_8888, true)
//                mBinding.ivCameraPic.setImageBitmap(bitmapOrigin)
                val baos = ByteArrayOutputStream()
                bitmapOrigin?.compress(Bitmap.CompressFormat.PNG, 100, baos)
                val bytes = baos.toByteArray()
                Glide.with(this).load(bytes).centerInside()
                    .into(mBinding.ivCameraPic)

                val base64 = BitmapUtils.bitmapToBase64(bitmap, true)
                if (base64 != null) {
                    startTranslate(base64, from!!, to!!)
                } else {
                    TipsToast.showTips("xxx")
                }
            }

        }
    }


    fun startTranslate(base64: String, from: Language, to: Language) {
        val ocrP = OcrTranslateParameters.Builder()
            .timeout(6000)
            .from(from)
            .to(to)
            .build()

        OcrTranslate.getInstance(ocrP).lookup(base64, "requestid", translateListener)
        showLoading("开始识别，请稍等~~~")
    }

    val handler : Handler = Handler()
    val translateListener = object : OcrTranslateListener {
        override fun onError(error: TranslateErrorCode, requestId: String) {
            handler.post {
                dismissLoading()
                TipsToast.showTips("${error.code}  ${error.name}")
                mBinding.tvTrans.text = "翻译失败"
            }

        }
        override fun onResult(
            result: OCRTranslateResult,
            input: String,
            requestId: String
        ) {
            handler.post {
                dismissLoading()
                TipsToast.showTips("success")
//                original.setText("翻译完成:")
                val text = getResult(result)
                val spannableString = SpannableString(text)
                var start = 0
                while (start < text!!.length && start >= 0) {
                    val s = text.indexOf("段落", start)
                    val end = text.indexOf("：", s) + 1
                    start = if (s >= 0) {
                        val colorSpan = ForegroundColorSpan(Color.parseColor("#808080"))
                        val sizeSpan = AbsoluteSizeSpan(35)
                        val underlineSpan = UnderlineSpan()
                        spannableString.setSpan(sizeSpan, s, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
                        spannableString.setSpan(colorSpan, s, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
                        spannableString.setSpan(
                            underlineSpan,
                            s,
                            end - 1,
                            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                        )
                        end
                    } else {
                        break
                    }
                }
                mBinding.tvTrans.setVisibility(View.VISIBLE)
                mBinding.tvTrans.setText(spannableString)
            }
        }
    }

    private fun getResult(result: OCRTranslateResult): String? {
        val sb = StringBuilder()
        var i = 1
        //按文本识别
        val regions: List<Region> = result.regions
        for (region in regions) {
            sb.append("段落" + i++ + "：\n")
            sb.append(region.getContext()).append("\n")
            sb.append("翻译：")
            sb.append(region.getTranContent()).append("\n")
            sb.append("\n")
        }
        return sb.toString()
    }


    /**
     * 打开相册
     */
    private fun openAlbum() {
        startActivityForResult(CameraUtils.getSelectPhotoIntent(), SELECT_PHOTO_CODE)
    }

}