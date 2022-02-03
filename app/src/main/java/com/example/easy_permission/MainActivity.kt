package com.example.easy_permission

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.easy_permission.Utils.Constants.isPermissionCameraGranted
import com.example.easy_permission.Utils.Constants.requestCodePermissionCamera
import com.example.easy_permission.databinding.ActivityMainBinding
import pub.devrel.easypermissions.EasyPermissions
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var binding: ActivityMainBinding
    private var permissionCamera = Manifest.permission.CAMERA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        verifyPermissionCamera(context, permissionCamera)
    }

    private fun verifyPermissionCamera (context: Context, permission: String) {
        isPermissionCameraGranted = EasyPermissions.hasPermissions(context, permission)
        if (isPermissionCameraGranted) {
            Toast.makeText(context, R.string.pmCameraWasGranted, Toast.LENGTH_SHORT).show()
        } else {
            requestPermissionCamera(permission)
        }
    }

    private fun requestPermissionCamera(permission: String) {
        val rationalePermission = R.string.pmCameraWasRationale
        Toast.makeText(context, R.string.pmCameraWasDenied, Toast.LENGTH_SHORT).show()
        timeWait(5)
        Toast.makeText(context, R.string.pmCameraWasGranted, Toast.LENGTH_SHORT).show()
        EasyPermissions.requestPermissions(this, "$rationalePermission", requestCodePermissionCamera, permission)
    }

    private fun timeWait (timeSecond: Long) {
        TimeUnit.MILLISECONDS.sleep(timeSecond)
    }

}


