package com.example.myhealthdiary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhealthdiary.data.diagnosischat.DiagnosisChatRepository
import com.example.myhealthdiary.data.diagnosischat.model.ChatResponse
import com.example.myhealthdiary.data.diagnosischat.model.PostChatBody
import io.reactivex.disposables.CompositeDisposable
import com.example.myhealthdiary.utils.viewmodel.Result
import com.example.myhealthdiary.utils.viewmodel.addTo
import com.example.myhealthdiary.utils.viewmodel.genericErrorHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class DiagnosisChatViewModel(
        private val repository: DiagnosisChatRepository,
        private val disposable: CompositeDisposable
): ViewModel() {
    val chat = MutableLiveData<Result<ChatResponse>>()

    init {
        chat.value = Result.default()
    }

    fun postChat(body: PostChatBody){
        chat.value = Result.loading()
        repository.postChat(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
               chat.value = Result.success(it)
            },{
               genericErrorHandler(it, chat)
            })
            .addTo(disposable)
    }

    override fun onCleared() {
        if (!disposable.isDisposed) disposable.dispose()
        super.onCleared()
    }
}