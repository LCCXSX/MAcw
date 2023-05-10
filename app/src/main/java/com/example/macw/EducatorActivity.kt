package com.example.macw

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class EducatorActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("daozhele","run educator activity")
        setContentView(R.layout.educator_main)

        val navView: BottomNavigationView = findViewById(R.id.educator_view)
        val navController = findNavController(R.id.nav_host_fragment_educator_main)
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.educator_nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}