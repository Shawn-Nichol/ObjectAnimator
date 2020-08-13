package com.example.myanimation

import android.animation.*
import android.animation.ValueAnimator.REVERSE
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import com.example.myanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var TAG: String = "MyMainActivity"
    private lateinit var binding: ActivityMainBinding

    lateinit var android: ImageView

    private lateinit var cwRotationAnimator: ObjectAnimator
    private lateinit var ccwRotationAnimator: ObjectAnimator
    private lateinit var rotationAnimator: ObjectAnimator
    private lateinit var colorAnimator: ObjectAnimator
    private lateinit var scaleXAnimator: ObjectAnimator
    private lateinit var scaleYAnimator: ObjectAnimator
    private lateinit var scaleAnimator: ObjectAnimator
    private lateinit var fadeInAnimator: ObjectAnimator
    private lateinit var fadeOutAnimator: ObjectAnimator
    private lateinit var fadeAnimator: ObjectAnimator

    // PropertyValuesHolder object: This class hold information about a property and the values that
    // property take on during an animation. PropertyValuesHolder object can be used to create animations
    // with ValueAnimator or ObjectAnimator that operate on several different properties in parallel.
    val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 10f)
    val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 10f)



    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate")
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // This adds the expression from the xml, without it onClick doesn't work.
        binding.animation = this
        android = binding.android
        cwRotationDetails()
        ccwRotationDetails()
        rotationAnimationDetails()
        colorAnimationDetails()
        scaleXDetails()
        scaleYDetails()
        scaleDetails()
        fadeInDetails()
        fadeOutDetails()
        fadeDetails()

    }


    private fun cwRotationDetails() {
        cwRotationAnimator = ObjectAnimator.ofFloat(android, View.ROTATION, -720f, 0f).apply {
            duration = 2000
        }
    }

    private  fun ccwRotationDetails() {
        ccwRotationAnimator = ObjectAnimator.ofFloat(android, View.ROTATION, 720f, 0f).apply {
            duration = 2000
        }
    }

    private fun rotationAnimationDetails() {
        rotationAnimator = ObjectAnimator.ofFloat(android, View.ROTATION, -720f, 0f).apply {
            duration = 2000
            repeatCount = 3 // Starts at zero
            repeatMode = REVERSE // Reverse the animation once

        }
    }

    private fun colorAnimationDetails() {
        colorAnimator = ObjectAnimator.ofArgb(android.parent, "backgroundColor", Color.WHITE, Color.RED).apply {
            duration = 2000
            repeatCount = 3
            // When the animation reaches th end and repeatCount is INFINITE or a positive value,
            // the animation reverses direction on every iteration
            repeatMode = REVERSE
        }
    }

    private fun scaleXDetails() {
        // ofPropertyValuesHolder: Constructs and returns a ValueAnimator that animates between the values
        // specified in the PropertyValues holder object.
        scaleXAnimator = ObjectAnimator.ofPropertyValuesHolder(android, scaleX).apply {
            duration = 2000
            repeatMode = REVERSE
            repeatCount = 3 // repeat count needs to be set to odd if you want it to revert back

        }
    }

    private fun scaleYDetails() {
        scaleYAnimator = ObjectAnimator.ofPropertyValuesHolder(android, scaleY).apply {
            duration = 2000
            repeatMode = REVERSE
            repeatCount = 3
        }
    }

    private fun scaleDetails() {
        scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(android, scaleX, scaleY).apply {
            duration = 2000
            repeatMode = REVERSE
            repeatCount = 3
        }
    }

    private fun fadeInDetails() {
        fadeInAnimator = ObjectAnimator.ofFloat(android, View.ALPHA, 1F).apply {
            duration = 2000
        }

    }

    private fun fadeOutDetails() {
        fadeOutAnimator = ObjectAnimator.ofFloat(android, View.ALPHA, 0f).apply {
            duration = 2000
        }
    }

    private fun fadeDetails() {
        fadeAnimator = ObjectAnimator.ofFloat(android, View.ALPHA, 1f).apply {
            duration = 2000
        }
    }

    fun clickCwRotate() {
        Log.i(TAG, "cw rotation")
        checkAnimation(cwRotationAnimator)
    }

    fun clickCcwRotate() {
        Log.i(TAG, "ccw rotation")
        checkAnimation(ccwRotationAnimator)
    }

    fun clickRotate() {
        Log.i(TAG, "rotate image")
        checkAnimation(rotationAnimator)
    }

    fun clickColor() {
        Log.i(TAG, "Color")
        checkAnimation(colorAnimator)
    }

    fun clickScaleX() {
        Log.i(TAG, "Scale Increase")
        checkAnimation(scaleXAnimator)
    }

    fun clickScaleY() {
        Log.i(TAG, "Scale Decrease")
        checkAnimation(scaleYAnimator)
    }

    fun clickScale() {
        Log.i(TAG, "Scale")
        checkAnimation(scaleAnimator)
    }

    fun clickFadeIn() {
        Log.i(TAG, "Fade In")
        if(android.alpha == 0f) checkAnimation(fadeInAnimator)
    }

    fun clickFadeOut() {
        Log.i(TAG, "Fade out")
        if(android.alpha == 1f) checkAnimation(fadeOutAnimator)
    }

    fun clickFade() {
        Log.i(TAG, "Fade")

        if(android.alpha == 1f) {
            Log.i(TAG, "android is visible")

            fadeAnimator = ObjectAnimator.ofFloat(android, View.ALPHA, 0f).apply {
                duration = 2000
            }
        } else {
            Log.i(TAG, "android isn't visible")
            fadeAnimator = ObjectAnimator.ofFloat(android, View.ALPHA, 1f).apply {
                duration = 2000
            }
        }
        checkAnimation(fadeAnimator)
    }


    fun clickRiot() {
        // The newAndroid images will all be drawn in this viewgroup.
        val container = android.parent as ViewGroup
        val containerX = container.width
        val containerY = container.height

        // The width and height of the original image.
        var androidW: Float = android.width.toFloat()
        var androidH: Float = android.height.toFloat()


        var newAndroid = AppCompatImageView(this)
        // The new image, that will be falling from the top
        newAndroid.setImageResource(R.drawable.ic_android_black_24dp)

        newAndroid.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT)
        // add the android image to the container.
        container.addView(newAndroid)

        // determines the size of the android image
        val scale = Math.random().toFloat() * 10f + .1f
        newAndroid.scaleX = scale
        newAndroid.scaleY = scale

        // The new width and height of the android image.
        androidW *= newAndroid.scaleX
        androidH *= newAndroid.scaleY

        // Translation dertermines where on the X axis the android image will fall from.
        newAndroid.translationX = Math.random().toFloat() * containerX - androidW / 2

        // Animator objects
        val mover = ObjectAnimator.ofFloat(newAndroid, View.TRANSLATION_Y, -androidH, containerY + androidH)
        mover.interpolator = AccelerateInterpolator(1f)
        val rotator = ObjectAnimator.ofFloat(newAndroid, View.ROTATION, (Math.random() * 1000).toFloat())
        rotator.interpolator = LinearInterpolator()


        // AnimatorSet: plays a set of Animator objects in the specified order. Animation can be set
        // to play together, in sequence, or after a specified delay.
        val set = AnimatorSet().apply {
            playTogether(mover, rotator)
            duration = (Math.random() * 1500 + 500).toLong()

            // this listener, is listening for when the animation on the androidView is done.
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    // When the animation ends remove the view from the ViewGroup.
                    super.onAnimationEnd(animation)
                    container.removeView(newAndroid)
                }
            })
        }
        set.start()
    }



    private fun checkAnimation(animator: ObjectAnimator) {
        if(animator.isStarted) Log.i(TAG, "$animator is running")
        else animator.start()
    }
}
