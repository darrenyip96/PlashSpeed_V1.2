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

    }




}




