package com.example.capstone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_coinflip.*
import kotlinx.android.synthetic.main.item_announcement.view.*
import kotlinx.android.synthetic.main.item_game.view.*
import kotlinx.coroutines.withContext

class GamesAdapter(private val games: List<Games>, private val clickListener: (Games) -> Unit):
    RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(games[position], clickListener)
    }

    override fun getItemCount(): Int {
        return games.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun dataBind(games: Games, clickListener: (Games) -> Unit) {
            itemView.tvGameName.text = games.title
            itemView.tvGameDescription.text = games.description
            Glide.with(itemView)
                .load(games.img)
                .into(itemView.ivGameIcon)
            itemView.setOnClickListener{
                clickListener(games)
            }
        }
    }
}