package br.com.wellintonvieira.businesscard.util

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import br.com.wellintonvieira.businesscard.R
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

object BussinesCardUtil {

    fun share(context: Context, card: View){
        val bitmap = getScreenShotFromView(card)
        bitmap?.let {
            saveMediaStorage(context, bitmap)
        }
    }

    private fun getScreenShotFromView(card: View): Bitmap? {
        var screenshot: Bitmap? = null
        try {
            screenshot = Bitmap.createBitmap(
                card.measuredWidth,
                card.measuredHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(screenshot)
            card.draw(canvas)
        } catch (exception: Exception) {
            Log.e("","Bitmap converter error ${exception.message}")
        }
        return screenshot
    }

    private fun saveMediaStorage(context: Context, bitmap: Bitmap) {
        val fileName = "${System.currentTimeMillis()}.jpg"
        var outputStream: OutputStream? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            context.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                outputStream = imageUri?.let {
                    shareIntent(context, imageUri)
                    resolver.openOutputStream(it)
                }
            }
        } else {
            val imageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val image = File(imageDir, fileName)
            shareIntent(context, Uri.fromFile(image))
            outputStream = FileOutputStream(image)

            outputStream?.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                Toast.makeText(context, context.getString(R.string.image_share_success), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun shareIntent(context: Context, imageUri: Uri) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, imageUri)
            type = "image/jpeg"
        }
        context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.image_share)))
    }
}