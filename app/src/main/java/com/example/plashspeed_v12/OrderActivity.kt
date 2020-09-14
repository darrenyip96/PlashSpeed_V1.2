package com.example.plashspeed_v12

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cart.*
import kotlinx.android.synthetic.main.help_page.*
import kotlinx.android.synthetic.main.my_order.*

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_order)

        var database = FirebaseDatabase.getInstance().reference.child("Orders")

        var getdata = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                /* var sb = StringBuilder()
                 for (i in p0.children) {
                     var foodName = i.child("FoodName").getValue()
                     var price = i.child("Price").getValue()
                     var quantity = i.child("Quantity").getValue()
                     var total = i.child("Total").getValue()

                     sb.append("   ${i.key}     $foodName           $price                     $quantity                $total\n")
                 }
                 cartText.setText(sb)*/
                var sb1 = StringBuilder()
                for (i in p0.children) {
                    var orderid = i.child("id").getValue()

                    sb1.append("Order #$orderid \n \n")
                }
                orderid_text.setText(sb1)

                var sb2 = StringBuilder()
                for (i in p0.children) {

                    var orderstatus = i.child("status").getValue()

                    sb2.append("$orderstatus \n \n")
                }
                status_text.setText(sb2)


            }

        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)

        //bottom navigation bar
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.orderMenu
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




