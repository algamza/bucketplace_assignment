package com.github.algamza.bucketplace.view.feed

data class FeedData(
        var callback: Callback,
        var id: Int,
        var user: Int,
        var img: String,
        val description : String
) {
    interface Callback {
        fun onClickFeed(id: Int)
    }
}

