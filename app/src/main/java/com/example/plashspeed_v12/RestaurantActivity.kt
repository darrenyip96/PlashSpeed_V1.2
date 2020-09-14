package com.example.plashspeed_v12


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.homepage.*

class RestaurantActivity:AppCompatActivity() {


    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var restaurantList: List<RestaurantModel> = ArrayList()
    private val restaurantListAdapter:RestaurantListAdapter = RestaurantListAdapter(restaurantList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        loadRestaurantData()

        home_restaurants_list.layoutManager = LinearLayoutManager(this)
        home_restaurants_list.adapter = restaurantListAdapter

    }


    //read from firestore
    fun getRestaurantList(): Task<QuerySnapshot> {
        return firebaseFirestore.collection("Restaurants").get()
    }



    fun loadRestaurantData() {
        getRestaurantList().addOnCompleteListener{
            if(it.isSuccessful){
                restaurantList = it.result!!.toObjects(RestaurantModel::class.java)
                restaurantListAdapter.restaurantListItems = restaurantList
                restaurantListAdapter.notifyDataSetChanged()
            }
        }
    }
}


