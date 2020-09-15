package com.example.plashspeed_v12

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.internal.service.Common
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.restaurant_item.view.*
import org.greenrobot.eventbus.EventBus

class RestaurantListAdapter(var restaurantListItems: List<RestaurantModel>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    class RestaurantsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bind(restaurantModel: RestaurantModel){
            itemView.restaurant_name.text = restaurantModel.rName
            itemView.restaurantRatingBar.numStars = restaurantModel.ratings
            Picasso.with(itemView.context).load(restaurantModel.picture).into(itemView.restaurant_image)
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item, parent , false)
        return RestaurantsViewHolder(view)

    }

    override fun getItemCount(): Int {
        return restaurantListItems.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RestaurantsViewHolder).bind(restaurantListItems[position])

        holder.setListener(object:RestaurantItemClickListener{
            override fun onItemClick(view: View, pos: Int) {
                ComObject.restaurantSelected=restaurantListItems.get(pos)
                EventBus.getDefault().postSticky(RestaurantClick(true,restaurantListItems.get(pos)))
            }
        })
    }
}
