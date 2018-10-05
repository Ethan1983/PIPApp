package com.sample.pipapp

import android.app.PictureInPictureParams
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            launchIntoPipMode()
        }
    }

    override fun onUserLeaveHint() {
        launchIntoPipMode( false )
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean, newConfig: Configuration) {

        button.setText (
                if( isInPictureInPictureMode ) R.string.pip_active else R.string.launch_pip_mode )

    }

    private fun launchIntoPipMode( showWarning : Boolean = true ) {

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N &&
                packageManager.hasSystemFeature( PackageManager.FEATURE_PICTURE_IN_PICTURE ) ) {

            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ) {

                enterPictureInPictureMode( PictureInPictureParams.Builder().build() )

            } else {

                @Suppress("DEPRECATION")
                enterPictureInPictureMode()

            }

        } else if( showWarning ) {
            toast( R.string.pip_unsupported )
        }
    }
}
