package com.example.plashspeed_v12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnCancel
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.PatternSyntaxException


class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()




        fun signUpUser(){
            if(email_register.text.toString().isEmpty()){

                email_register.error = "Please enter email"
                email_register.requestFocus()
                return
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email_register.text.toString()).matches()){
                email_register.error="Please enter valid email"
                email_register.requestFocus()
                return
            }

            if(password_register.text.toString().isEmpty()){
                password_register.error="Please enter email"
                password_register.requestFocus()
                return
            }
            if(password_register2.text.toString().isEmpty()){
                password_register2.error="Please re-enter email"
                password_register2.requestFocus()
                return
            }

            auth.createUserWithEmailAndPassword(email_register.text.toString(), password_register.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(baseContext, "Sign Up successful.",
                            Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Sign Up failed.",
                            Toast.LENGTH_SHORT).show()
                    }

                }

            /*auth.createUserWithEmailAndPassword(email_register.text.toString(), password_register.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(baseContext, "Sign Up successful.",
                            Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Sign Up failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }*/
        }

        btnCancel.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        register_button.setOnClickListener {
            signUpUser()
        }

     



    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {

    }


}
