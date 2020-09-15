package com.example.plashspeed_v12

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.cart.*


class CartActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart)

        fun deleteCart(){


            val ref = FirebaseDatabase.getInstance().reference.child("test")
            ref.removeValue()


        }

        fun saveToOrder(){
            val orderstatus = "Delivered"

            val refref = FirebaseDatabase.getInstance().getReference("Orders")

            //val orderid = refref.push().key

            val idid = (0..999).random()

            val orderid = idid.toString()

            val orderdetails = OrderSummary(orderid,orderstatus)

            refref.child(orderid).setValue(orderdetails)

        }





        var database = FirebaseDatabase.getInstance().reference.child("Cart")

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
                    var foodName = i.child("FoodName").getValue()

                    sb1.append("      $foodName \n \n")
                }
                cartText.setText(sb1)

                var sb2 = StringBuilder()
                for (i in p0.children) {

                    var price = i.child("Price").getValue()

                    sb2.append("            $price \n \n")
                }
                priceText.setText(sb2)

                var sb3 = StringBuilder()
                for (i in p0.children) {

                    var quantity = i.child("Quantity").getValue()


                    sb3.append("            $quantity \n \n")
                }
                quanText.setText(sb3)

                var sb4 = StringBuilder()
                for (i in p0.children) {

                    var total = i.child("Total").getValue()

                    sb4.append("           $total\n \n")
                }
                totalText.setText(sb4)

            }

        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)


        val paymentMethod = arrayOf("Cash On Delivery", "Credit/Debit card")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, paymentMethod)

        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object :


            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = spinner.selectedItem.toString()

                if (selectedItem == "Credit/Debit card") {
                    val intent = Intent(this@CartActivity, PaymentActivity::class.java)
                    startActivity(intent)

                } else if (selectedItem == "Cash on Delivery") {

                }

            }
        }

        btnaddress.setOnClickListener {
            val intent = Intent(this, DeliveryActivity::class.java)
            startActivity(intent)


        }

        val builder = AlertDialog.Builder(this)
        btnOrder.setOnClickListener {
            builder.setTitle("OrderPlaced")
            builder.setMessage("The order have been received, food will delivered to you soon.")
            builder.setCancelable(true)
            builder.setPositiveButton(
                "OK",
                DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                    startActivity(intent)

                })

            builder.show()
            saveToOrder()
            deleteCart()
        }



        //bottom navigation bar
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.cartMenu
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