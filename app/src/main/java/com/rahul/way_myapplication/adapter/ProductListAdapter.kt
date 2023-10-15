package com.rahul.way_myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rahul.way_myapplication.R
import com.rahul.way_myapplication.data.model.ProductListDataClass
import com.rahul.way_myapplication.utils.AppUtils

class ProductListAdapter (private var context: Context?, private var itemList: ProductListDataClass) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvTagLine: TextView = itemView.findViewById(R.id.tvTagLine)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvRating: TextView = itemView.findViewById(R.id.tvRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        item.let {productList ->
            holder.tvName.text = productList.name
            holder.tvTagLine.text = productList.tagline
            holder.tvRating.text = AppUtils.roundToNearestHalf(productList.rating).toString()
            holder.tvDate.text = productList.date
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun refreshRecyclerView(mContext: Context, updatedItemList: ProductListDataClass) {
        context = mContext
        itemList = updatedItemList
        notifyDataSetChanged()
    }
}
