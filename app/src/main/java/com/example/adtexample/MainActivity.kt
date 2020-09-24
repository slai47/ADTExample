package com.example.adtexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adtexample.ui.CharacterListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val frag = supportFragmentManager.findFragmentById(R.id.container)
        if(frag == null) { // Navigation here would be better with a nav host but doing this for time
            val listFragment = CharacterListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.container, listFragment).commit()
        }
    }
}