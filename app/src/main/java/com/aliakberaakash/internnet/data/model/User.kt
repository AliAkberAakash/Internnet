package com.aliakberaakash.internnet.data.model

data class User (
    val email : String = "",
    val userName : String = "",
    val image : String = "",
    var myPosts : MutableList<String> = mutableListOf(),
    var appliedJobs: MutableList<String> = mutableListOf(),
)