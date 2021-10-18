package io.github.zagori.nytimes.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.zagori.nytimes.models.Article
import io.github.zagori.nytimes.models.Doc
import io.github.zagori.nytimes.models.ListType
import io.github.zagori.nytimes.models.State
import io.github.zagori.nytimes.repositories.ArticlesRepository
import io.github.zagori.nytimes.utils.viewModelInjection
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ArticlesViewModel : ViewModel() {

    @Inject
    lateinit var articlesRepository: ArticlesRepository

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    val articlesLiveData by lazy { MutableLiveData<State<List<Article>>>() }
    val articlesRefreshedLiveData by lazy { MutableLiveData<State<Boolean>>() }

    val docsLiveData by lazy { MutableLiveData<State<List<Doc>>>() }
    val docsRefreshedLiveData by lazy { MutableLiveData<State<Boolean>>() }

    var page = 1
    var query = ""

    init {
        viewModelInjection.inject(this)
    }

    /**
     * This is meant to load and save articles list based on the type passed,
     * either most viewed, shared or emailed, and then it will notify the livedata
     * */
    private fun loadAndSaveMostPopular(listType: String) = compositeDisposable.add(
        articlesRepository.loadAndSaveMostPopular(listType)
            .doOnSubscribe { articlesRefreshedLiveData.postValue(State.Loading) }
            .subscribe({
                articlesRefreshedLiveData.postValue(State.Success(true))
            }, {
                articlesRefreshedLiveData.postValue(State.Error(it))
            })
    )

    fun getLocalMostPopular(type: String?) = compositeDisposable.add(
        articlesRepository.getLocalMostViewed(type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { articlesLiveData.postValue(State.Loading) }
            .subscribe({
                articlesLiveData.postValue(it)
            }, {
                articlesLiveData.postValue(State.Error(it))
            })
    )

    /**
     * This is meant to preload and save articles list
     * */
    fun preLoadMostPopular() = ListType.values().forEach {
        if (it != ListType.Search) loadAndSaveMostPopular(it.name)
    }

    /**
     * This is meant to load and save searched articles
     * */
    fun loadAndSaveSearched(query: String, page: Int) = compositeDisposable.add(
        articlesRepository.loadAndSaveBySearch(query, page)
            .doOnSubscribe { docsRefreshedLiveData.postValue(State.Loading) }
            .subscribe({
                docsRefreshedLiveData.postValue(State.Success(true))
            }, {
                docsRefreshedLiveData.postValue(State.Error(it))
            })
    )

    /**
     * Subscribe to the emissions of the searched articles from the local database.
     * This subscription will update the ui every time data changed in the database
     * */
    fun getLocalSearch() = compositeDisposable.add(
        articlesRepository.getLocalSearched()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { docsLiveData.postValue(State.Loading) }
            .subscribe({
                docsLiveData.postValue(it)
            }, {
                docsLiveData.postValue(State.Error(it))
            })
    )

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
        super.onCleared()
    }
}