package com.example.myhealthdiary.data.diagnosischat.remote

import com.example.myhealthdiary.data.diagnosischat.model.ChatResponse
import com.example.myhealthdiary.data.diagnosischat.model.PostChatBody
import io.reactivex.Single

class DiagnosisChatApi (private val apiClient: DiagnosisChatApiClient): DiagnosisChatApiClient {
    override fun postChat(body: PostChatBody): Single<ChatResponse> {
        return apiClient.postChat(body)
    }
}