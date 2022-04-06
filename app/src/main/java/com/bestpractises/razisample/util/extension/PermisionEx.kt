package com.bestpractises.razisample.util.extension


import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

fun Fragment.checkSelfPermissionCompat(permission: String) =
    ActivityCompat.checkSelfPermission(requireActivity(), permission)

fun Fragment.shouldShowRequestPermissionRationaleCompat(permission: String) =
    ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permission)

fun Fragment.requestPermissionsCompat(
    launcher: ActivityResultLauncher<String>,
    permissionsArray: String
) {
    launcher.launch(permissionsArray)
}
