package com.example.cameraapp

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val PERMISSION_CODE = 1000;
    private val IMAGE_CAPTURE_CODE = 1001
    var image_uri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button = findViewById<Button>(R.id.btn)

        //btn click button click event
        button.setOnClickListener{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){

                    // request permission
                    val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE )
                }
                else{
                    // allow camera
                    openCamera()
                }
            }

            else{
                openCamera()
            }
        }

    }

    private fun openCamera(){
        val value = ContentValues()
        value.put(MediaStore.Images.Media.TITLE, "New Pecture")
        value.put(MediaStore.Images.Media.DESCRIPTION, "From the Camers")
        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, value)
        //camera intent start the start the camera activity
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

    //handle permission results
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        when(requestCode){
            PERMISSION_CODE -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                    // permission allowed
                    openCamera()
                }
                else{
                    // permission denied
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show()

                }
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var imageView = findViewById<ImageView>(R.id.image)

        // called when photo is made
        if(resultCode == Activity.RESULT_OK){
            //set token image
            imageView.setImageURI(image_uri)
            super.onActivityResult(requestCode, resultCode, data)

        }
    }

}