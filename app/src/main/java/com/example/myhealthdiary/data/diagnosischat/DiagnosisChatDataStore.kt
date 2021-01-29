package com.example.myhealthdiary.data.diagnosischat

import com.example.myhealthdiary.data.diagnosischat.model.ChatResponse
import com.example.myhealthdiary.data.diagnosischat.model.PostChatBody
import com.example.myhealthdiary.data.diagnosischat.remote.DiagnosisChatApi
import io.reactivex.Single

class DiagnosisChatDataStore (api: DiagnosisChatApi): DiagnosisChatRepository {
    val webService = api

    override fun postChat(body: PostChatBody): Single<ChatResponse> {
        return webService.postChat(body)
            .map { it }
    }
}