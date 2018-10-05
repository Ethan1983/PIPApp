package com.sample.pipapp

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

internal fun Context.toast( @StringRes messageResId : Int, duration : Int = Toast.LENGTH_SHORT ) =
        Toast.makeText( this, messageResId, duration ).show()