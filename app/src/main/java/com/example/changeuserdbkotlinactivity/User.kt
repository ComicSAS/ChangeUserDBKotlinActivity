package com.example.changeuserdbkotlinactivity

data class User(var name: String, var surname: String, var age: Int, var other: String) {
    fun toStringUser(): String {
        return "User(name='$name', surname='$surname', age=$age, other='$other')"
    }
}