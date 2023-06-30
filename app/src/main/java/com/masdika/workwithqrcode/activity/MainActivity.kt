package com.masdika.workwithqrcode.activity

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.masdika.workwithqrcode.R
import com.masdika.workwithqrcode.databinding.ActivityMainBinding
import com.masdika.workwithqrcode.fragment.GeneratorFragment
import com.masdika.workwithqrcode.fragment.ScannerFragment
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contentView = findViewById<View>(androidx.appcompat.R.id.content)
        //KeyboardAppearListener
        var isKeyboardShowing = false
        fun onKeyboardVisibilityChanged(opened: Boolean) {
            Log.d("Keyboard", "$opened")
            if (opened) {
                binding.bottomBar.visibility = View.INVISIBLE
            } else {
                binding.bottomBar.visibility = View.VISIBLE
            }
        }
        // ContentView is the root view of the layout of this activity/fragment
        contentView.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            contentView.getWindowVisibleDisplayFrame(r)
            val screenHeight = contentView.rootView.height

            // r.bottom is the position above soft keypad or device button.
            // if keypad is shown, the r.bottom is smaller than that before.
            val keypadHeight = screenHeight - r.bottom
            Log.d("KeypadHeight", "$keypadHeight")

            if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                // keyboard is opened
                if (!isKeyboardShowing) {
                    isKeyboardShowing = true
                    onKeyboardVisibilityChanged(true)
                }
            } else {
                // keyboard is closed
                if (isKeyboardShowing) {
                    isKeyboardShowing = false
                    onKeyboardVisibilityChanged(false)
                }
            }
        }

        //Initiate first fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, GeneratorFragment()).commit()

        //BottomBar Listener
        binding.bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                Log.d("bottom_bar", "Selected index: $newIndex, title: ${newTab.title}")
                when (newTab.id) {
                    R.id.tab_generate -> {
                        //Fragment Generate QR Code
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, GeneratorFragment()).commit()
                    }

                    R.id.tab_scanner -> {
                        //Fragment Scanner QR Code
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, ScannerFragment()).commit()
                    }
                }
            }

            // An optional method that will be fired whenever an already selected tab has been selected again.
            override fun onTabReselected(index: Int, tab: AnimatedBottomBar.Tab) {
                Log.d("bottom_bar", "Reselected index: $index, title: ${tab.title}")
            }
        })
    }
}