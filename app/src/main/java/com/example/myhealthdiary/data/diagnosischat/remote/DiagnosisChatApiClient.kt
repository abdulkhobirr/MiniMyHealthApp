package com.example.myhealthdiary.data.diagnosischat.remote

import com.example.myhealthdiary.data.diagnosischat.model.ChatResponse
import com.example.myhealthdiary.data.diagnosischat.model.PostChatBody
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface DiagnosisChatApiClient {
    @POST(".")
    fun postChat( @Body body: PostChatBody): Single<ChatResponse>
}