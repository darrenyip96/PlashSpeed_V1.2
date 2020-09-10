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
class PaymentActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var vsmcText: EditText
    lateinit var paymentButton: Button
    lateinit var nameText: EditText
    lateinit var cardText: EditText
    lateinit var cvcText: EditText



    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.credit_card_page)

        auth = FirebaseAuth.getInstance()

        vsmcText = findViewById(R.id.vsmcText)
        nameText = findViewById(R.id.nameText)
        cvcText = findViewById(R.id.cvcText)
        paymentButton= findViewById(R.id.paymentButton)
        cardText = findViewById(R.id.cardText)

        paymentButton.setOnClickListener{
            savePaymentMethod()
        }

    }

    private fun savePaymentMethod(){

        val vsmc = vsmcText.text.toString()
        val name = nameText.text.toString()
        val cvc = cvcText.text.toString()
        val card = cardText.text.toString()



        if(name.isEmpty()){
            nameText.error = "Please enter the Name on Card"
            return
        }else if(cvc.isEmpty()){
            cvcText.error = "Please enter the cvc"
            return
        }else if(card.isEmpty()){
            cardText.error = "Please enter the card number"
            return
        }else if(vsmc.isEmpty()){
            vsmcText.error = "Please enter the card details"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("PaymentMethods")

        val methods = paymentClass(vsmc, name, card, cvc)

        ref.child(name).setValue(methods).addOnCompleteListener{
            Toast.makeText(applicationContext, "Payment Method Saved Successfully", Toast.LENGTH_LONG).show()
        }


    }

}