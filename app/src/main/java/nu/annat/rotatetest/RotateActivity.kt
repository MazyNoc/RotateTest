package nu.annat.rotatetest

import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.view.View

class RotateActivity : AppCompatActivity() {

    var current: ConstraintSet? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val constraint1 = ConstraintSet()
        constraint1.clone(this, R.layout.activity_main)
        current = constraint1

        val constraint2 = ConstraintSet()
        constraint2.clone(this, R.layout.activity_main_rotated)


        findViewById<View>(android.R.id.content).setOnClickListener {

            TransitionManager.beginDelayedTransition(findViewById(R.id.root))
             current = if (current == constraint1) {
                 findViewById<View>(R.id.text).rotation = 0f
                 findViewById<View>(R.id.text).animate().rotation(90f)
                constraint2
            } else {
                 findViewById<View>(R.id.text).rotation = 90f
                 findViewById<View>(R.id.text).animate().rotation(0f)
                constraint1
            }.also {
                 it.applyTo(findViewById(R.id.root))
             }

        }
    }
}
