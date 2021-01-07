package com.example.capstone

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_coinflip.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

private const val STILL_LOGO = "https://firebasestorage.googleapis.com/v0/b/sqdam-bot.appspot.com/o/coin_sqdam%201.png?alt=media&token=04875404-d4e6-4383-af40-ab8a83e6583a"
private const val TAILS_LOGO = "https://firebasestorage.googleapis.com/v0/b/sqdam-bot.appspot.com/o/coin_sqdam.gif?alt=media&token=b9e4a1ac-c3d0-496d-96fc-923d5a5a805e"
private const val HEADS_LOGO = "https://firebasestorage.googleapis.com/v0/b/sqdam-bot.appspot.com/o/coin_ayman.gif?alt=media&token=65a8da42-f073-4bc1-a2ce-56cd8c14705b"
private const val HEADS = 0
private const val TAILS = 1

class CoinflipFragment : Fragment() {

    private var isFlipping: Int = 0
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coinflip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(context)
            .load(STILL_LOGO)
            .into(ivCoinflip)

        fabHead.setOnClickListener {
            showResult(view, HEADS, coinflip(HEADS))
        }
        fabTail.setOnClickListener {
            showResult(view, TAILS, coinflip(TAILS))
        }
    }

    private fun coinflip(chosenSide: Int): Boolean{

        val coinflipped: Int = Random.nextInt(0, 2)

        return coinflipped == chosenSide

    }

    private fun showResult(view: View, chosenSide: Int, hasWon: Boolean) {
        val amount = etBet.text.toString()
        val title: String
        val message: String

        if (amount.isNotEmpty()) {

            val logo: String = if(chosenSide == HEADS){
                HEADS_LOGO
            } else {
                TAILS_LOGO
            }

            fabHead.hide()
            fabTail.hide()

            if (hasWon) {
                title = getString(R.string.win)
                message = getString(R.string.win_message, amount.toInt()*2)

                Glide.with(context)
                        .load(logo)
                        .into(ivCoinflip)

                val handler = Handler()
                handler.postDelayed(Runnable {
                    context?.let { it1 -> MaterialAlertDialogBuilder(it1, R.style.AlertDialogTheme)
                            .setTitle(title)
                            .setMessage(message)
                            .setPositiveButton(getString(R.string.ok), null)
                            .show() }
                }, 7000)

                handler.postDelayed(Runnable {
                    Glide.with(context)
                            .load(STILL_LOGO)
                            .into(ivCoinflip)
                    fabHead.show()
                    fabTail.show()
                }, 8000)


            } else {
                title = getString(R.string.lose)
                message = getString(R.string.lose_message, amount.toInt())

                Glide.with(context)
                        .load(logo)
                        .into(ivCoinflip)
                val handler = Handler()
                handler.postDelayed(Runnable {
                    context?.let { it1 -> MaterialAlertDialogBuilder(it1, R.style.AlertDialogTheme)
                            .setTitle(title)
                            .setMessage(message)
                            .setPositiveButton(getString(R.string.ok), null)
                            .show() }
                }, 7000)

                handler.postDelayed(Runnable {
                    Glide.with(context)
                            .load(STILL_LOGO)
                            .into(ivCoinflip)
                    fabHead.show()
                    fabTail.show()
                }, 8000)
            }
        } else {
            Snackbar.make(view, getString(R.string.empty_bet), Snackbar.LENGTH_SHORT).show()
        }
    }

}