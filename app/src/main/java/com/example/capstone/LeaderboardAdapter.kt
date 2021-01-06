package com.example.capstone

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_leaderboard.view.*

class LeaderboardAdapter(private val leaderboard: List<Leaderboard>) :
    RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_leaderboard, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(leaderboard[position])
    }

    override fun getItemCount(): Int {
        return leaderboard.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun dataBind(leaderboard: Leaderboard) {
            itemView.tvRank.text ="#" + (adapterPosition + 1).toString()
            itemView.tvUsername.text = leaderboard.username
            itemView.tvMoney.text = "$" + leaderboard.money
//            Picasso.get().load(leaderboard.img).into(itemView.ivIcon)
        }
    }
}