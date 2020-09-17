package com.example.plashspeed_v12


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.homepage.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class RestaurantActivity:AppCompatActivity() {


    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var user = FirebaseAuth.getInstance().currentUser
    val userEmail = user?.email

    private var restaurantList: List<RestaurantModel> = ArrayList()
    private val restaurantListAdapter:RestaurantListAdapter = RestaurantListAdapter(restaurantList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        loadRestaurantData()

        home_restaurants_list.layoutManager = LinearLayoutManager(this)
        home_restaurants_list.adapter = restaurantListAdapter

        txtUserEmail.text = userEmail

        //bottom navigation bar
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.homePage2
        bottomNavigationView.setOnNavigationItemSelectedListener(object:
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(@NonNull menuItem: MenuItem):Boolean {
                when (menuItem.itemId) {
                    R.id.profileActivity2 -> {
                        startActivity(Intent(applicationContext, ProfileActivity::class.java))
                        overridePendingTransition(0, 0)
                        return true
                    }
                    R.id.homePage2 -> {
                        return true
                    }
                    R.id.orderMenu -> {
                        startActivity(Intent(applicationContext, OrderActivity::class.java))
                        overridePendingTransition(0, 0)
                        return true
                    }
                    R.id.cartMenu -> {
                        startActivity(Intent(applicationContext, CartActivity::class.java))
                        overridePendingTransition(0, 0)
                        return true
                    }
                    R.id.helpMessage2 -> {
                        startActivity(Intent(applicationContext, HelpMessage::class.java))
                        overridePendingTransition(0, 0)
                        return true
                    }
                }
                return false
            }
        })

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

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    @Subscribe(sticky=true,threadMode = ThreadMode.MAIN)
    fun onCategorySelected(event:RestaurantClick){
        if(event.isSuccess){
            //Toast.makeText(this,"Click to "+event.restaurant.rName,Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, FoodListActivity::class.java))

        }
    }




}


