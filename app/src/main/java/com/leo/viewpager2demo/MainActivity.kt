package com.leo.viewpager2demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.leo.viewpager2demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.btnBaseUse.setOnClickListener {
            startActivity(Intent(this,KotlinUseActivity::class.java))
        }

        mBinding.btnGalleryUse.setOnClickListener {
            startActivity(Intent(this,GalleryUseActivity::class.java))
        }

        mBinding.btn3dUse.setOnClickListener {
            startActivity(Intent(this,Transformer3DActivity::class.java))
        }

    }

}