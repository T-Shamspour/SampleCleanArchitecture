package ir.shamspour.presentation.posts

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import ir.shamspour.domain.model.Post
import ir.shamspour.domain.usecase.GetPostsUseCase
import ir.shamspour.presentation.base.BaseViewModel
import ir.shamspour.domain.util.Result
import ir.shamspour.presentation.util.DispatchersProvider

class PostViewModel @ViewModelInject constructor(
        private val
    dispatchers: DispatchersProvider, private val getPostsUseCase: GetPostsUseCase
) : BaseViewModel(dispatchers) {
    private val _posts: MutableLiveData<Result<List<Post>>> = MutableLiveData()

    init {
        fetchPosts()
    }


    fun fetchPosts() {
        execute {
        _posts.postValue(Result.Loading(true))
            when (val result = getPostsUseCase()) {
                is Result.Success -> {
                    _posts.postValue(result)

                }
                is Result.Error -> {
                    _posts.postValue(result)
                }
                else -> {}
            }
        }
    }


    fun getPosts(): MutableLiveData<Result<List<Post>>> = _posts

}