package io.afdon.movie.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.afdon.movie.databinding.ItemMovieListBinding
import io.afdon.movie.model.Movie

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.Holder>() {

    private val asyncListDiffer = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.movie.id == newItem.movie.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.movie.title == newItem.movie.title
        }
    })

    fun submitList(items: List<Item>) {
        asyncListDiffer.submitList(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) =
        holder.bind(asyncListDiffer.currentList[position])

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    class Holder(private val binding: ItemMovieListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.item = item
        }
    }

    data class Item(
        val movie: Movie
    )
}