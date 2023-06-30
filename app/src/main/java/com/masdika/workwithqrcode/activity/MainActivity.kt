package com.masdika.workwithqrcode.activity

import android.os.Bundle
import android.util.Log
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