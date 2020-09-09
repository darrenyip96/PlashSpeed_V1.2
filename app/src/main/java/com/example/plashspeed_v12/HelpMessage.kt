package com.example.plashspeed_v12

//import android.support.v7.app.AlertDialog;
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
    }
}