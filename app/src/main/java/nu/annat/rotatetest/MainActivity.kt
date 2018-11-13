package nu.annat.rotatetest

import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "Create")
        findViewById<TextView>(R.id.text).setOnClickListener {
            // test to animate once, making sure the animation constraint anim works.
            animate(Configuration.ORIENTATION_LANDSCAPE)
        }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d("orientation", newConfig.orientation.toString())

        animate(newConfig.orientation)

    }

    private fun animate(orientation: Int) {
        Handler(Looper.getMainLooper()).postDelayed({
            val constraint1 = ConstraintSet()
            constraint1.clone(this, R.layout.activity_main)
            val constraint2 = ConstraintSet()
            constraint2.clone(this, R.layout.activity_main_land)

            TransitionManager.beginDelayedTransition(findViewById(R.id.root))
            val constraint =
                if (orientation == Configuration.ORIENTATION_PORTRAIT) constraint1 else constraint2
            constraint.applyTo(findViewById(R.id.root))


        }, 400) // without the delay it wont show at all
    }

}
