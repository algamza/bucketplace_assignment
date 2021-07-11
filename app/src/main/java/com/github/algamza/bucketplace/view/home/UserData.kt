package com.github.algamza.bucketplace.view.home

data class UserData(
        var callback: Callback,
        val id: Int,
        val nickname: String,
        val introduction: String
) {
    interface Callback {
        fun onClickUser(id: Int)
    }
}
