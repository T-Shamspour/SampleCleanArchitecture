package ir.shamspour.presentation.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.shamspour.domain.model.Post
import ir.shamspour.domain.util.HttpErrors
import ir.shamspour.presentation.R
import ir.shamspour.presentation.base.BaseFragment
import ir.shamspour.presentation.databinding.FragmentPostsBinding
import ir.shamspour.presentation.util.observe
import ir.shamspour.domain.util.Result

@AndroidEntryPoint
class PostFragment: BaseFragment<FragmentPostsBinding>(R.layout.fragment_posts) {
    private val viewModel: PostViewModel by viewModels()
    private val clickHandler=ClickHandler(this)
    private val postAdapter=PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setupObservers() {
        viewModel.run {
            observe(getPosts(), ::initPosts)
        }

    }


    private fun initPosts(result: Result<List<Post>>?) {
        if (result is Result.Success) {
            updateList(result.data)
        } else if (result is Result.Error) {
            if (result.error.status == HttpErrors.Unauthorized)
            {
                //handle expired token
            }
            else
            {
                showRetry(true)
                showLoading(false)
                result.error.message?.let {
                    Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
                }
            }
        }else if (result is Result.Loading) {
           showLoading(result.loading)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.isLoading=isLoading
    }

    private fun updateList(data: List<Post>) {
        showLoading(false)
        showRetry(false)
        postAdapter.upDateDataSet(data)
    }

    private fun showRetry(showRetry: Boolean) {
        binding.loadingHasError=showRetry
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.rvPosts.adapter=postAdapter
        binding.clickHandler=clickHandler

    }


    inner class ClickHandler(fragment: PostFragment){

        fun onRetryButtonClicked(view: View){
            showRetry(false)
            viewModel.fetchPosts()
        }
    }
}