package com.ktm.kshape

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.ktm.kshape.databinding.ActivityMainBinding
import com.ktm.kshape.fragment.CircleFragment
import com.ktm.kshape.fragment.MixFragment
import com.ktm.kshape.fragment.SquareFragment
import com.ktm.kshape.fragment.TriangleFragment
import com.ktm.kshape.util.BitmapPool
import com.ktm.kshape.util.ColorPool
import com.ktm.kshape.util.screenWidth


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenWidth = getSystemService(WindowManager::class.java)
            .currentWindowMetrics.bounds.width()

        binding.bottomNavigation.setOnItemSelectedListener {
            replaceFragment(it.itemId)
            true
        }

        replaceFragment(R.id.item_square)

        // pre-fetch color and bitmap to later use
        ColorPool.fetchColor(requestCount = 40)
        BitmapPool.fetchBitmap(this, requestCount = 20)
    }

    private fun replaceFragment(itemId: Int) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            val containerViewId = R.id.fragment_container_view
            when (itemId) {
                R.id.item_circle -> {
                    replace<CircleFragment>(containerViewId)
                }

                R.id.item_square -> {
                    replace<SquareFragment>(containerViewId)
                }

                R.id.item_triangle -> {
                    replace<TriangleFragment>(containerViewId)
                }

                R.id.item_mix -> {
                    replace<MixFragment>(containerViewId)
                }
            }
        }
    }
}