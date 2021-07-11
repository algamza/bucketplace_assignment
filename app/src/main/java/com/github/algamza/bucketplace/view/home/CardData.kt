package com.github.algamza.bucketplace.view.home

data class CardData(
        var callback: Callback,
        val id: Int,
        val user_id: Int,
        val img_url: String,
        val description: String
) {
    interface Callback {
        fun onClickCard(id: Int)
    }
}
