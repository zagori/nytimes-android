package io.github.zagori.nytimes.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import io.github.zagori.nytimes.repositories.ArticlesRepository
import io.github.zagori.nytimes.utils.viewModelInjection
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class ArticlesViewModel: ViewModel() {

    @Inject
    lateinit var imagesRepository: ArticlesRepository

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    init {
        viewModelInjection.inject(this)
    }

    fun getRemoteMostViewed() = compositeDisposable.add(
        imagesRepository.getRemoteMostViewed()
            .doOnSubscribe { Log.d(this::class.java.name, "==> GET ARTICLES: Loading...") }
            .subscribe({
                Log.d(this::class.java.name, "==> GET ARTICLES: Success: $it")
            }, {
                Log.e(this::class.java.name, "==> GET ARTICLES: Error")
                it.printStackTrace()
            })
    )

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
        super.onCleared()
    }
}