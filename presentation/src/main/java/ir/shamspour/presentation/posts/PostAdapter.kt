package ir.shamspour.presentation.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ir.shamspour.domain.model.Post
import ir.shamspour.presentation.R
import ir.shamspour.presentation.databinding.ItemPostBinding


class PostAdapter :
        RecyclerView.Adapter<PostAdapter.FaqViewHolder>() {
    private var list = ArrayList<Post>()


    inner class FaqViewHolder(itemView: ItemPostBinding) :
            RecyclerView.ViewHolder(itemView.root) {
        private val itemBinding: ItemPostBinding = itemView
        fun bind(data: Post) {
            itemBinding.apply {
                post = data
            }
        }


    }


    fun upDateDataSet(data: List<Post>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        val binding = DataBindingUtil.inflate<ItemPostBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_post, parent, false
        )
        return FaqViewHolder(binding)

    }


    override fun getItemCount(): Int {
        return if (list.isNullOrEmpty()) 0 else list.size
    }


    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        holder.bind(list[position])
    }
}