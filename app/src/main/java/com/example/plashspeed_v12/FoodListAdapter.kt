package com.example.plashspeed_v12

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.internal.service.Common
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_item.view.*
import kotlinx.android.synthetic.main.restaurant_item.view.*
import org.greenrobot.eventbus.EventBus

class FoodListAdapter(var foodListItems: List<ProductModel>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    class FoodViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bind(productModel: ProductModel){
            itemView.food_name.text = productModel.name
            itemView.food_price.text = productModel.price.toString()
            Picasso.with(itemView.context).load(productModel.picture).into(itemView.food_image)
            itemView.setOnClickListener(this)
        }


        internal var listener:RestaurantItemClickListener?=null

        fun setListener(listener: RestaurantItemClickListener){
            this.listener = listener
        }

        override fun onClick(view: View?) {
            listener!!.onItemClick(view!!,adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent , false)
        return FoodViewHolder(view)

    }

    override fun getItemCount(): Int {
        return foodListItems.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FoodViewHolder).bind(foodListItems[position])

        holder.setListener(object:RestaurantItemClickListener{
            override fun onItemClick(view: View, pos: Int) {
                ComObject.foodSelected=foodListItems.get(pos)
                EventBus.getDefault().postSticky(FoodClick(true,foodListItems.get(pos)))
            }
        })

    }
}
