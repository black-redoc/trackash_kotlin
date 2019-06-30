package com.black.trackash.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Parcelable
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.black.trackash.R
import com.black.trackash.utils.doubleExchange
import kotlinx.android.synthetic.main.activity_historic.toHome
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.coroutines.runBlocking
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.io.File
import java.io.IOException
import kotlin.math.min

const val REQUEST_IMAGE_GALLERY = 2

class ProfileActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val viewModelFactory: ExtractViewModelFactory by instance()

    private lateinit var viewModel: ExtractViewModel

    private lateinit var currentPhotoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExtractViewModel::class.java)

        initializeUI()
    }

    private fun initializeUI() {
        toHome.setOnClickListener { finish() }
        bindUI()
        enableEditListeners()
    }

    private fun bindUI() = runBlocking {
        val extract = viewModel.last.await()
        extract.observe(this@ProfileActivity, Observer {
            if (it == null) return@Observer
            txtExpenses.setText(it.expenses.doubleExchange())
            txtIncomes.setText(it.incomes.doubleExchange())
            totalCash.setText(it.total.doubleExchange())
        })
    }

    private fun dispatchTakePictureIntent(): Intent {
        return Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            run {
                // Ensure that there's a camera activity to handle the intent
                takePictureIntent.resolveActivity(packageManager)?.also {
                    // Create the file where the photo should go
                    val photoFile = try {
                        createImageFile()
                    } catch (ex: IOException) {
                       null
                    }
                    // Continue only if the FIle was successfully created
                    photoFile?.also {
                        val photoURI = FileProvider.getUriForFile(
                            this,
                            "com.black.trackash.ui.ProfileActivity",it)
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI!!)
                        //startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode == Activity.RESULT_OK) {
            data != null -> imageProfile.setImageURI(data!!.data)
            else -> setPic()
        }
    }


    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = LocalDateTime.now(ZoneId.of("UTC-05:00"))
            .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            // Save a file: path for use with ACTION_VIEW Intents
            currentPhotoPath = absolutePath
        }
    }

    private fun galleryAddPic(): Intent {
        return Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also { actionPick ->
            run {
                actionPick.type = "image/*"
                //startActivityForResult(intent, REQUEST_IMAGE_GALLERY)
            }
        }
    }

    private fun setPic() {
        // Get the dimensions of the View
        val targetW = imageProfile.width
        val targetH = imageProfile.height

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(currentPhotoPath, this)
            val photoW = outWidth
            val photoH = outHeight

            // Determine how much to scale down the image
            val scaleFactor = min(photoW / targetW, photoH / targetH)

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
            inPurgeable = true
        }
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions)?.also { bitmap ->
            imageProfile.setImageBitmap(bitmap)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down)
    }

    private fun enableEditListeners() {
        takePicture.setOnClickListener {
            Intent.createChooser(Intent(galleryAddPic()),"Chooser").also { chooser ->
                chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(dispatchTakePictureIntent()))
                startActivityForResult(chooser, REQUEST_IMAGE_GALLERY)
            }
        }
    }

}
