package com.example.plashspeed_v12


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_details.*
import kotlinx.android.synthetic.main.food_item.view.*
import kotlinx.android.synthetic.main.restaurant_foods.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SelectedFoodActivity:AppCompatActivity() {


    //private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var foodList: List<ProductModel> = ArrayList()
    private val foodListAdapter: FoodListAdapter = FoodListAdapter(foodList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_details)



        //food_list.layoutManager = LinearLayoutManager(this)
        //food_list.adapter = foodListAdapter

        selected_food_name.text = ComObject.foodSelected!!.name
        selected_food_desciption.text = ComObject.foodSelected!!.foodDescription
        selected_food_price.text = ComObject.foodSelected!!.price.toString()
        Picasso.with(this).load(ComObject.foodSelected!!.picture).into(selected_food_image)

        btnAddtoCart.setOnClickListener{
            saveToCart()
        }


    }

    private fun saveToCart(){

        val FoodName = ComObject.foodSelected!!.name
        val Price = ComObject.foodSelected!!.price.toString()
        val Quantity = number_button.number
        val Total = ComObject.foodSelected!!.price.times(number_button.number.toInt()).toString()



        //val details = DeliveryDetails(address, name, number, remarks)
        val ref = FirebaseDatabase.getInstance().getReference("Cart")

        //val detailsid = ref.push().key
        val details = DeliveryDetails(FoodName, Price, Quantity, Total)

        ref.child(Total).setValue(details).addOnCompleteListener{
            Toast.makeText(applicationContext, "Saved to cart successfully", Toast.LENGTH_LONG).show()
        }


    }
}