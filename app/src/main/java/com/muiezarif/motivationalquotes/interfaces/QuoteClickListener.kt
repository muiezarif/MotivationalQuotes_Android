package com.muiezarif.motivationalquotes.interfaces

import android.view.View
import com.muiezarif.motivationalquotes.databinding.CustomQuoteItemBinding


interface QuoteClickListener {
    fun onClick(view: CustomQuoteItemBinding?, position: Int)
}