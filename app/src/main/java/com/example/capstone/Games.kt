package com.example.capstone

class Games (
        var img : String? = null,
        var title : String? = null,
        var description : String? = null
) {
    companion object {
        val IMG_LINKS = arrayOf(
                "https://firebasestorage.googleapis.com/v0/b/sqdam-bot.appspot.com/o/coin_sqdam%201.png?alt=media&token=04875404-d4e6-4383-af40-ab8a83e6583a"
        )

        val TITLES = arrayOf(
                "Coinflip"
        )

        val DESCRIPTIONS = arrayOf(
                "Wordt het kop of munt? Test je geluk!"
        )
    }
}