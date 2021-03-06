package com.example.plashspeed_v12


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_details.*
import org.greenrobot.eventbus.EventBus


class SelectedFoodActivity:AppCompatActivity() {


    //private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var foodList: List<ProductModel> = ArrayList()
    private val foodListAdapter: FoodListAdapter = FoodListAdapter(foodList)
    //var user = FirebaseAuth.getInstance().currentUser
    //val userEmail = user?.email

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_details)



        //food_list.layoutManager = LinearLayoutManager(this)
        //food_list.adapter = foodListAdapter

        selected_food_name.text = ComObject.foodSelected!!.name
        selected_food_desciption.text = ComObject.foodSelected!!.foodDescription
        selected_food_price.text = ComObject.foodSelected!!.price.toString()
        Picasso.with(this).load(ComObject.foodSelected!!.picture).into(selected_food_image)

        //txtUserEmail.text = userEmail

        btnAddtoCart.setOnClickListener{
            saveToCart()
            //val intent = Intent(this, CartActivity::class.java)
            //startActivity(intent)

        }

        btnBack.setOnClickListener{
            EventBus.getDefault().removeAllStickyEvents()

            val intent = Intent(applicationContext, RestaurantActivity::class.java)
            //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            //intent.putExtra("EXIT", true)
            startActivity(intent)
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
        val details = SaveCartDetails(FoodName, Price, Quantity, Total)


        val cartidid = (0..999).random()

        val cartid = cartidid.toString()
        ref.child(cartid).setValue(details).addOnCompleteListener{
                Toast.makeText(applicationContext, "Saved to cart successfully", Toast.LENGTH_LONG).show()
                //val intent = Intent(this, CartActivity::class.java)
                //startActivity(intent)

        }


    }
}