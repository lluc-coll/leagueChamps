package com.example.leaguechamps.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.leaguechamps.ui.models.Item
import com.example.leaguechamps.R
import com.example.leaguechamps.database.entities.FavEntity
import com.squareup.picasso.Picasso
import com.example.leaguechamps.ui.viewModel.LeagueViewModel

class ListOfItemsAdapter(private val items: List<Item>, private val listener: ListOfChampsAdapter.OnItemClickListener) : RecyclerView.Adapter<ListOfItemsAdapter.ListOfItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOfItemsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)

        return ListOfItemsViewHolder(view)
    }

    override fun onBindViewHolder(holderOfLists: ListOfItemsViewHolder, position: Int) {
        holderOfLists.bindData(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ListOfItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var itemIcon: ImageView
        private var itemName: TextView
        private var itemDesc: TextView
        private var itemGold: TextView
        private var itemFav: ImageView



        init {
            itemIcon = itemView.findViewById(R.id.item_icon)
            itemName = itemView.findViewById(R.id.item_name)
            itemDesc = itemView.findViewById(R.id.item_desc)
            itemGold = itemView.findViewById(R.id.item_gold)
            itemFav = itemView.findViewById(R.id.favIcon)
        }

        fun bindData(item: Item){
            Picasso.get().load(item.icon).into(itemIcon);
            itemName.text = item.name
            itemDesc.text = item.desc
            itemGold.text = item.gold.toString()
            val viewModel = LeagueViewModel()

            if(item.fav){
                itemFav.setImageResource(R.drawable.fav_true)
            }

            itemFav.setOnClickListener{
                if(item.fav){
                    item.fav = false
                    itemFav.setImageResource(R.drawable.fav_false)
                    viewModel.delFav(FavEntity(item.name, 1))
                }
                else{
                    item.fav = true
                    itemFav.setImageResource(R.drawable.fav_true)
                    viewModel.addFav(FavEntity(item.name, 1))
                }
            }
        }
        override fun onClick(v: View?) {
        }
    }
}