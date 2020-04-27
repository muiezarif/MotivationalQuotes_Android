package com.muiezarif.motivationalquotes.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.muiezarif.motivationalquotes.databinding.CustomAuthorItemBinding
import com.muiezarif.motivationalquotes.interfaces.AuthorClickListener


class MyAuthorViewHolder(var binding: CustomAuthorItemBinding, var authorClickListener: AuthorClickListener): RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {
    init {
        binding.clAuthorItem.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        authorClickListener.onClick(binding,adapterPosition,binding.tvAuthorName.text.toString())
    }
}