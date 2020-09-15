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
import kotlinx.android.synthetic.main.payment.*

class PaymentMethod: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment)

        var database = FirebaseDatabase.getInstance().reference.child("PaymentMethods")

        var getdata = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {

                var sb1 = StringBuilder()
                for (i in p0.children) {
                    var name = i.child("name").getValue()
                    var card = i.child("cvc").getValue()

                    sb1.append("           $name                       $card \n \n \n" )

                }
                banktext.setText(sb1)



            }

        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)

        btnAdd.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)

        }

        banktext.setOnClickListener {
           onBackPressed()
        }


    }
}