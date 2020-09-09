package com.example.plashspeed_v12

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.help_page.*
//import android.support.v7.app.AlertDialog;
import androidx.appcompat.app.AlertDialog

class HelpMessage : AppCompatActivity() {

    fun onSubmit(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.help_page)
        val builder = AlertDialog.Builder(this)

        btnSubmit.setOnClickListener{
            builder.setTitle("Confirmation!")
            builder.setMessage("Your details had been saved, we will contact you soon.")
            //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = closeContextMenu()))
            builder.show()

        }
    }
}