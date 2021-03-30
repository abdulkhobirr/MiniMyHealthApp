package com.example.myhealthdiary.utils.viewmodel

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonSyntaxException
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

fun Disposable.addTo(disposable: CompositeDisposable) {
    disposable.add(this)
}

fun <T> genericErrorHandler(e: Throwable, result: MutableLiveData<Result<T>>) {
    // TODO: 28/11/18 define a proper Error Message
    when (e) {
        is SocketTimeoutException -> result.value =
            Result.fail(e, "Koneksi Gagal", "Gagal menghubungkan ke server, silahkan coba lagi.")
        is IOException -> result.value =
            Result.fail(e, "Koneksi Gagal", "Gagal menghubungkan ke server, silahkan coba lagi.")
        is JsonSyntaxException -> result.value = Result.fail(
            e,
            "Terjadi kesalahan pada data",
            "Data error atau tidak ditemukan."
        )
        is HttpException -> result.value =
            when {
                HttpException(e.response()!!).response()?.code() == 500 -> Result.fail(
                    title = "Terjadi kesalahan pada server",
                    message = ""
                )
                HttpException(e.response()!!).response()?.code() == 404 -> Result.fail(
                    e,
                    "Data tidak ditemukan",
                    e.response()?.errorBody().toString()
                )
                else -> Result.fail(
                    e,
                    title = "Terjadi kesalahan",
                    message = "Error tidak diketahui"
                )
            }
        is NoSuchElementException -> result.value = Result.empty()
        else -> result.value =
            Result.fail(title = "Terjadi kesalahan", message = "Error tidak diketahui")
    }
}