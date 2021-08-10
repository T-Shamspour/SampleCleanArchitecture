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
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val _loadingHasError: MutableLiveData<Boolean> = MutableLiveData()
    private val _posts: MutableLiveData<Result<List<Post>>> = MutableLiveData()

    init {
        _isLoading.postValue(true)
        _loadingHasError.postValue(false)
        fetchPosts()
    }


    fun fetchPosts() {
        _isLoading.postValue(true)
        _loadingHasError.postValue(false)
        execute {
            _isLoading.postValue(true)
            when (val result = getPostsUseCase.execute()) {
                is Result.Success -> {
                    _posts.postValue(result)
                    _isLoading.postValue(false)
                    _loadingHasError.postValue(false)
                }
                is Result.Error -> {
                    _posts.postValue(result)
                    _isLoading.postValue(false)
                    _loadingHasError.postValue(true)
                }
            }
        }
    }


    fun isLoading(): MutableLiveData<Boolean> = _isLoading
    fun getLoadingStatus(): MutableLiveData<Boolean> = _loadingHasError
    fun getPosts(): MutableLiveData<Result<List<Post>>> = _posts

}