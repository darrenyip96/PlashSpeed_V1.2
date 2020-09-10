package com.example.plashspeed_v12

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.credit_card_page.*

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.credit_card_page)
        val builder = AlertDialog.Builder(this)
        val intent = Intent(this, MainActivity::class.java)

        payment_button.setOnClickListener{
            builder.setTitle("Confirm?")
            builder.setMessage("Your payment details have been saved! Please proceed to the next page")
            builder.setCancelable(true)
            builder.setPositiveButton("OK", DialogInterface.OnClickListener{ dialogInterface: DialogInterface, i: Int ->
                startActivity(intent)
            })

            builder.show()

        }
    }
}