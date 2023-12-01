package com.example.caculate_v3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.caculate_v3.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var biding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(biding.root)

        val intent = intent
        val message = intent.getStringExtra("textView")

        biding.textView.text = message;

    }
}