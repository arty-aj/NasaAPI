package com.example.apiskotlin.model

data class ApodResponse (
    val title: String,
    val hdurl: String,
    val explanation: String,
    val date: String,
    val copyright: String
    )