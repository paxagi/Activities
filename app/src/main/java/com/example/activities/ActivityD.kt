package com.example.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class ActivityD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d)

        val flTop = findViewById<FrameLayout>(R.id.flTop)
        val flBottom = findViewById<FrameLayout>(R.id.flBottom)

        val topFragment = TopFragment()
        val bottomFragment = BottomFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flTop, topFragment)
            replace(R.id.flBottom, bottomFragment)
            commit()
        }

        var isOrder = false
        for (fl in arrayOf(flTop, flBottom)) {
            fl.setOnClickListener {
                swapFragmentsAKABookkeeping(
                    R.id.flTop, topFragment,
                    R.id.flBottom, bottomFragment,
                    isOrder
                ).also { isOrder = !isOrder }
            }
        }
    }

    private fun swapFragmentsAKABookkeeping(
        poopa: Int, poopaFragment: Fragment,
        loopa: Int, loopaFragment: Fragment,
        isOrder: Boolean
    ) =
        supportFragmentManager.apply {
            popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            beginTransaction().remove(poopaFragment).remove(loopaFragment).commit()
            executePendingTransactions()
        }.beginTransaction().apply {
            val fragment1 = if (isOrder) poopaFragment else loopaFragment
            val fragment2 = if (isOrder) loopaFragment else poopaFragment
            replace(poopa, fragment1)
            replace(loopa, fragment2)
            commit()
        }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean = MenuListener().create(this, menu)
    override fun onOptionsItemSelected(item: MenuItem): Boolean = MenuListener().itemSelected(this, item)
}