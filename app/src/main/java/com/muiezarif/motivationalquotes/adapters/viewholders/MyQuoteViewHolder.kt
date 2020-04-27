package com.muiezarif.motivationalquotes.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.muiezarif.motivationalquotes.databinding.CustomQuoteItemBinding
import com.muiezarif.motivationalquotes.interfaces.QuoteClickListener

class MyQuoteViewHolder(var binding: CustomQuoteItemBinding, var quoteClickListener: QuoteClickListener): RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {
    init {
        binding.clQuote.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        quoteClickListener.onClick(binding,adapterPosition)
    }
}