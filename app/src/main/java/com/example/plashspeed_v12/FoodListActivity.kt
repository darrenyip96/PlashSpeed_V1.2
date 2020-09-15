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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_item.view.*
import kotlinx.android.synthetic.main.restaurant_foods.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class FoodListActivity:AppCompatActivity() {


    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var foodList: List<ProductModel> = ArrayList()
    private val foodListAdapter:FoodListAdapter = FoodListAdapter(foodList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant_foods)

        loadFoodData()

        food_list.layoutManager = LinearLayoutManager(this)
        food_list.adapter = foodListAdapter

        restaurant_name2.text = ComObject.restaurantSelected!!.rName
        restaurantDescription.text = ComObject.restaurantSelected!!.description
        restaurantRatingBar2.numStars = ComObject.restaurantSelected!!.ratings

        Picasso.with(this).load(ComObject.restaurantSelected!!.picture).into(restaurant_image2)


    }


    //read from firestore
    fun getFoodList(): Task<QuerySnapshot> {
        return firebaseFirestore.collection(ComObject.restaurantSelected!!.codeName).get()
    }



    fun loadFoodData() {
        getFoodList().addOnCompleteListener{
            if(it.isSuccessful){
                foodList= it.result!!.toObjects(ProductModel::class.java)
                foodListAdapter.foodListItems = foodList
                foodListAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    @Subscribe(sticky=true,threadMode = ThreadMode.MAIN)
    fun onFoodSelected(event:FoodClick){
        if(event.isSuccess){
            //Toast.makeText(this,"Click to "+event.restaurant.rName,Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, SelectedFoodActivity::class.java))
            overridePendingTransition(0, 0)
        }
    }


}