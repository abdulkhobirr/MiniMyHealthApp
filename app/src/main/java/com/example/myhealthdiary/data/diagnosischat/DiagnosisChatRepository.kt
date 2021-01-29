package com.example.myhealthdiary.data.diagnosischat

import com.example.myhealthdiary.data.diagnosischat.model.ChatResponse
import com.example.myhealthdiary.data.diagnosischat.model.PostChatBody
import io.reactivex.Single

interface DiagnosisChatRepository {
    fun postChat(body: PostChatBody): Single<ChatResponse>
}