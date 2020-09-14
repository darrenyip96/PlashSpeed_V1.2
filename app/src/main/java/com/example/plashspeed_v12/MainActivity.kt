package com.example.plashspeed_v12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnCancel
import kotlinx.android.synthetic.main.activity_register.*

class MainActivity : AppCompatActivity() {



    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        login_button.setOnClickListener{
            doLogin()
        }


        btnCancel.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }


    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun doLogin() {
        if(email_et.text.toString().isEmpty()){
            email_et.error = "Please enter email"
            email_et.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email_et.text.toString()).matches()){
            email_et.error="Please enter valid email"
            email_et.requestFocus()
            return
        }

        if(password_et.text.toString().isEmpty()){
            password_et.error="Please enter email"
            password_et.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(email_et.text.toString(), password_et.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, RestaurantActivity::class.java)
                    startActivity(intent)
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(baseContext, "Login failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser != null){
            Toast.makeText(baseContext, "Login succeed.",
                Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(baseContext, "Login failed.",
                Toast.LENGTH_SHORT).show()
        }
    }
}