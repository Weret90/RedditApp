package com.ermolaev_arkhip.redditapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.ermolaev_arkhip.redditapp.domain.entity.PopularPost
import com.ermolaev_arkhip.redditapp.domain.repository.PopularPostsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainViewModel(private val repository: PopularPostsRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _pagingData = MutableLiveData<PagingData<PopularPost>>()
    val pagingData: LiveData<PagingData<PopularPost>> get() = _pagingData

    init {
        getPopularPosts()
    }

    private fun getPopularPosts() {
        compositeDisposable.add(
            repository.getPopularPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    _pagingData.value = it
                }
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}