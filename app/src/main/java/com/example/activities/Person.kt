package com.example.activities

import java.io.Serializable

data class Person (
    val name: String,
    val surname: String,
    val birthday: String,
    val country: String,
) : Serializable