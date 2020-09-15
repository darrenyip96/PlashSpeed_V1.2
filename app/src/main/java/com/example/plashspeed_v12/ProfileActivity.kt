package com.example.plashspeed_v12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        auth = FirebaseAuth.getInstance()

        logout_btn.setOnClickListener {
            auth.signOut()
            Toast.makeText(baseContext, "Successfully logged out",
            Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        imgView1.setOnClickListener {
            val intent = Intent(this, AboutusActivity::class.java)
            startActivity(intent)
        }

        //bottom navigation bar
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.profileActivity2
        bottomNavigationView.setOnNavigationItemSelectedListener(object:
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(@NonNull menuItem: MenuItem):Boolean {
                when (menuItem.itemId) {
                    R.id.profileActivity2 -> {
                        return true
                    }
                    R.id.homePage2 -> {
                        startActivity(Intent(applicationContext, RestaurantActivity::class.java))
                        overridePendingTransition(0, 0)
                        return true
                    }
                    R.id.orderMenu -> {
                        startActivity(Intent(applicationContext, OrderActivity::class.java))
                        overridePendingTransition(0, 0)
                        return true
                    }
                    R.id.cartMenu -> {
                        startActivity(Intent(applicationContext, CartActivity::class.java))
                        overridePendingTransition(0, 0)
                        return true
                    }
                    R.id.helpMessage2 -> {
                        startActivity(Intent(applicationContext, HelpMessage::class.java))
                        overridePendingTransition(0, 0)
                        return true
                    }
                }
                return false
            }
        })
    }
}