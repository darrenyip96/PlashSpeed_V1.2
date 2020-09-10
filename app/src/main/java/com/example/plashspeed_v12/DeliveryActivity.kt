package com.example.plashspeed_v12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnCancel
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.PatternSyntaxException

class DeliveryActivity:  AppCompatActivity() {
    //private lateinit var database: FirebaseDatabase

    lateinit var addressText: EditText
    lateinit var confirm_btn: Button
    lateinit var addressText2: EditText
    lateinit var addressText3: EditText
    lateinit var nameText: EditText
    lateinit var numberText: EditText
    lateinit var remarksText: EditText



    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delivery_details)

        addressText = findViewById(R.id.addressText)
        addressText2 = findViewById(R.id.addressText2)
        addressText3 = findViewById(R.id.addressText3)
        confirm_btn = findViewById(R.id.confirm_btn)
        nameText = findViewById(R.id.nameText)
        numberText = findViewById(R.id.numberText)
        remarksText = findViewById(R.id.remarksText)

        confirm_btn.setOnClickListener{
            saveDetails()
            onBackPressed();
        }

    }

    private fun saveDetails(){
        val address = addressText.text.toString() + addressText2.text.toString() + addressText3.text.toString()
        val name = nameText.text.toString()
        val number = numberText.text.toString()
        val remarks = remarksText.text.toString()

        if(address.isEmpty()){
            addressText.error = "Please enter an address"
            return
        }else if(name.isEmpty()){
            nameText.error = "Please enter your name"
            return
        }else if(number.isEmpty()){
            numberText.error = "Please enter your number"
            return
        }else if(remarks.isEmpty()){
            remarksText.error = "Give us some remarks"
            return
        }

        //val details = DeliveryDetails(address, name, number, remarks)
        val ref = FirebaseDatabase.getInstance().getReference("DeliverDetails")

        //val detailsid = ref.push().key
        val details = DeliveryDetails(address, name, number, remarks)

        ref.child(name).setValue(details).addOnCompleteListener{
            Toast.makeText(applicationContext, "Details saved successfully", Toast.LENGTH_LONG).show()
        }


    }
}