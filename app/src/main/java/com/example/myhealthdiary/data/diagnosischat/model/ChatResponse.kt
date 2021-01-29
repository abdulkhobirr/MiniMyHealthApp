package com.example.myhealthdiary.data.diagnosischat.model

import com.google.gson.annotations.SerializedName

data class ChatResponse (
    @SerializedName("ans")
    val jawaban: String?,
    @SerializedName("question")
    val pertanyaan: String?,
    @SerializedName("type")
    val type: String?
)