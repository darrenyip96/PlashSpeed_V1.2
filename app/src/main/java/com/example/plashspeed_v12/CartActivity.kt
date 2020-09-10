package com.example.plashspeed_v12

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cart.*
import kotlinx.android.synthetic.main.help_page.*

class CartActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart)


        var database = FirebaseDatabase.getInstance().reference.child("Cart")

        var getdata = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                var sb = StringBuilder()
                for (i in p0.children) {
                    var foodName = i.child("FoodName").getValue()
                    var price = i.child("Price").getValue()
                    var quantity = i.child("Quantity").getValue()
                    var total = i.child("Total").getValue()

                    sb.append("   ${i.key}     $foodName           $price                     $quantity                $total\n")
                }
                cartText.setText(sb)
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

        }

    }

}