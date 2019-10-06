package com.kabelash.kotlinrepo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.repo_row.view.*
import com.squareup.picasso.Picasso

/*
* Created by Kabelash on 30.09.2019
* */

class MainAdapter(val feed: Array<Feed>): RecyclerView.Adapter<CustomViewHolder>(){

    override fun getItemCount(): Int {
        return feed.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val rowCell = layoutInflater.inflate(R.layout.repo_row, parent, false)
        return CustomViewHolder(rowCell)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val fd1 = feed[position].name
        val fd2 = feed[position].owner.login
        val fd3 = feed[position].owner.avatar_url
        val fd4 = feed[position].created_at
        holder.view.titleText.text = fd1.toString() //Set repo title
        holder.view.authorText.text = "Author: "+ fd2.toString() //Set repo's auther
        holder.view.dateText.text = "Date & Time: "+ fd4.toString() //Set Date & time

        //Used Picasso to set image url
        Picasso.get()
            .load(fd3)
            .resize(50, 50)
            .centerCrop()
            .into(holder.view.proImg)

    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {


}