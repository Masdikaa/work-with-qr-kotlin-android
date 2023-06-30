package com.masdika.workwithqrcode.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.masdika.workwithqrcode.R
import com.masdika.workwithqrcode.databinding.ActivityMainBinding
import com.masdika.workwithqrcode.fragment.GeneratorFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initiate first fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, GeneratorFragment()).commit()

    }
}