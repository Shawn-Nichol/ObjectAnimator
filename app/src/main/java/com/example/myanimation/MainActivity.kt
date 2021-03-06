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
    private lateinit var xaxisAnimator: ObjectAnimator
    private lateinit var yaxisAnimator: ObjectAnimator


    // PropertyValuesHolder object: This class hold information about a property and the values that
    // property take on during an animation. PropertyValuesHolder object can be used to create animations
    // with ValueAnimator or ObjectAnimator that operate on several different properties in parallel.
    val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 10f)
    val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 10f)


    lateinit var container: ViewGroup
    var androidW: Float = 0f
    var androidH: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate")
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // This adds the expression from the xml, without it onClick doesn't work.
        binding.animation = this
        android = binding.android

        // Used for the newAndroid animation.
        container = android.parent as ViewGroup
        androidW = android.width.toFloat()
        androidH = android.height.toFloat()

        // Instantiates the animation, so the button can tell if the animation is running.
        cwRotationDetails()
        ccwRotationDetails()
        rotationAnimationDetails()
        colorAnimationDetails()
        xaxisDetails()
        yaxisDetails()
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

    private fun ccwRotationDetails() {
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
        colorAnimator =
            ObjectAnimator.ofArgb(android.parent, "backgroundColor", Color.WHITE, Color.RED).apply {
                duration = 2000
                repeatCount = 3
                // When the animation reaches th end and repeatCount is INFINITE or a positive value,
                // the animation reverses direction on every iteration
                repeatMode = REVERSE
            }
    }

    private fun xaxisDetails() {
        xaxisAnimator = ObjectAnimator.ofFloat(android, View.ROTATION_X, -720f, 0f).apply {
            duration = 2000
        }
    }

    private fun yaxisDetails() {
        yaxisAnimator = ObjectAnimator.ofFloat(android, View.ROTATION_Y, -720f, 0f).apply {
            duration = 2000
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

    private fun allDetails() {
        AnimatorSet().apply {
            play(cwRotationAnimator)
                .before(rotationAnimator)
                .with(scaleAnimator)
                .with(xaxisAnimator)
                .with(yaxisAnimator)
                .with(fadeAnimator)
                .after(ccwRotationAnimator)



                start()
        }


    }

    fun clickCwRotate() {
        Log.i(TAG, "cw rotation")
        checkAnimation(cwRotationAnimator)
    }

    fun clickCcwRotate() {
        Log.i(TAG, "ccw rotation")
        checkAnimation(cwRotationAnimator)
    }

    fun clickRotate() {
        Log.i(TAG, "rotate image")
        checkAnimation(rotationAnimator)
    }

    fun clickColor() {
        Log.i(TAG, "Color")
        checkAnimation(colorAnimator)
    }

    fun clickXaxis() {
        checkAnimation(xaxisAnimator)
    }

    fun clickYaxis() {
        checkAnimation(yaxisAnimator)
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
        if (android.alpha == 0f) checkAnimation(fadeInAnimator)
    }

    fun clickFadeOut() {
        Log.i(TAG, "Fade out")
        if (android.alpha == 1f) checkAnimation(fadeOutAnimator)
    }

    fun clickFade() {
        Log.i(TAG, "Fade")

        if (android.alpha == 1f) {
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

    fun clickAll() {
        Log.i(TAG, "ALL animation")
        allDetails()
    }

    fun createImage(): ImageView {
        var newAndroid = AppCompatImageView(this)
        // The new image, that will be falling from the top
        newAndroid.setImageResource(R.drawable.ic_android_black_24dp)

        newAndroid.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        // add the android image to the container.
        container.addView(newAndroid)

        // determines the size of the android image
        val scale = Math.random().toFloat() * 4f + .1f
        newAndroid.scaleX = scale
        newAndroid.scaleY = scale

        // The new width and height of the android image.
        androidW *= newAndroid.scaleX
        androidH *= newAndroid.scaleY

        return newAndroid
    }

    fun combineAnimation(mover: ObjectAnimator, rotator: ObjectAnimator, newAndroid: ImageView) {

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

    fun clickTtoB() {
        // The newAndroid images will all be drawn in this viewgroup.

        var x = 0
        while (x < 4) {
            val containerW = container.width
            val containerH = container.height

            val newAndroid: ImageView = createImage()

            // translationX: Sets the horizontal translation of X
            newAndroid.translationX = Math.random().toFloat() * containerW - androidW / 2

            // Creates the animation of the object falling down the screen.
            val mover = ObjectAnimator.ofFloat(
                newAndroid,
                View.TRANSLATION_Y,
                -androidH,
                containerH + androidH
            )
            mover.interpolator = AccelerateInterpolator(1f)

            // Rotate the newAndroid image.
            val rotator = ObjectAnimator.ofFloat(
                newAndroid,
                View.ROTATION,
                (Math.random() * 1000).toFloat()
            )
            rotator.interpolator = LinearInterpolator()

            combineAnimation(rotator, mover, newAndroid)
            x++
        }
    }

    fun clickBtoT() {
        var x = 0
        while (x < 4) {
            val containerW = container.width
            val containerH = container.height

            val newAndroid = createImage()

            // Move newAndroid position along the X axis.
            newAndroid.translationX = Math.random().toFloat() * containerW - androidW / 2

            // create rising animation
            val mover = ObjectAnimator.ofFloat(
                newAndroid,
                View.TRANSLATION_Y,
                containerH + androidH,
                -androidH
            )
            mover.interpolator = AccelerateInterpolator(1f)

            // Rotate the newAndroid image
            val rotator = ObjectAnimator.ofFloat(
                newAndroid,
                View.ROTATION,
                (Math.random() * 1000).toFloat()
            )

            rotator.interpolator = LinearInterpolator()
            combineAnimation(mover, rotator, newAndroid)
            x++
        }
    }

    fun clickLtoR() {
        var x = 0

        while (x < 4) {
            val containerW = container.width
            val containerH = container.height

            val newAndroid: ImageView = createImage()

            // Translation dertermines where on the X axis the android image will fall from.
            newAndroid.translationY = Math.random().toFloat() * containerH - androidH / 2

            // Creates the animation of the object falling down the screen.
            val mover = ObjectAnimator.ofFloat(
                newAndroid,
                View.TRANSLATION_X,
                -androidW,
                containerW + androidW
            )
            // Speed at which the animation moves
            mover.interpolator = AccelerateInterpolator(1f)

            // Rotate the newAndroid image.
            val rotator = ObjectAnimator.ofFloat(
                newAndroid,
                View.ROTATION,
                (Math.random() * 1000).toFloat()
            )
            rotator.interpolator = LinearInterpolator()

            combineAnimation(rotator, mover, newAndroid)
            x++
        }
    }

    fun clickRtoL() {
        var x = 0

        while (x < 4) {
            val containerW = container.width
            val containerH = container.height

            val newAndroid: ImageView = createImage()

            // Translation dertermines where on the Y axis the android image will start from.
            newAndroid.translationY = Math.random().toFloat() * containerH - androidH / 2

            // Creates the animation of the object falling down the screen.
            val mover = ObjectAnimator.ofFloat(
                newAndroid,
                View.TRANSLATION_X,
                +androidW + containerW,
                androidW
            )
            mover.interpolator = AccelerateInterpolator(1f)

            // Rotate the newAndroid image.
            val rotator = ObjectAnimator.ofFloat(
                newAndroid,
                View.ROTATION,
                (Math.random() * 1000).toFloat()
            )
            rotator.interpolator = LinearInterpolator()

            combineAnimation(rotator, mover, newAndroid)
            x++
        }
    }

    fun clickChaos() {
        var x = 0
        while (x < 4) {
            clickTtoB()
            clickLtoR()
            clickBtoT()
            clickRtoL()
            x++
        }
    }




    private fun checkAnimation(animator: ObjectAnimator) {
        if (animator.isStarted) Log.i(TAG, "$animator is running")
        else animator.start()
    }
}
