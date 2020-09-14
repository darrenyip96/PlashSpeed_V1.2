package com.example.plashspeed_v12

//import android.support.v7.app.AlertDialog;
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.help_page.*

class HelpMessage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.help_page)
        val builder = AlertDialog.Builder(this)
        val intent = Intent(this, MainActivity::class.java)

        btnSubmit.setOnClickListener{
            builder.setTitle("Confirmation!")
            builder.setMessage("Your details had been saved, we will contact you soon.")
            builder.setCancelable(true)
            builder.setPositiveButton("OK", DialogInterface.OnClickListener{ dialogInterface: DialogInterface, i: Int ->
                startActivity(intent)
            })

            builder.show()
            //val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)

        }

        //bottom navigation bar
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.helpMessage2
        bottomNavigationView.setOnNavigationItemSelectedListener(object:
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(@NonNull menuItem: MenuItem):Boolean {
                when (menuItem.itemId) {
                    R.id.profileActivity2 -> {
                        startActivity(Intent(applicationContext, ProfileActivity::class.java))
                        overridePendingTransition(0, 0)
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
                        return true
                    }
                }
                return false
            }
        })
    }
}