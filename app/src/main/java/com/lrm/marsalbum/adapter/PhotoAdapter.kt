package com.lrm.marsalbum.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lrm.marsalbum.databinding.ListItemBinding
import com.lrm.marsalbum.model.MarsPhoto

class PhotoAdapter: ListAdapter<MarsPhoto, PhotoAdapter.MarsPhotoViewHolder>(DiffCallback) {

    class MarsPhotoViewHolder(private var binding: ListItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(marsPhoto: MarsPhoto) {
                binding.photo = marsPhoto
            }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<MarsPhoto>() {
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoViewHolder {
        return MarsPhotoViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }
}