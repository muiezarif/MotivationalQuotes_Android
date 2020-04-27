package com.muiezarif.motivationalquotes.interfaces

import android.view.View
import com.muiezarif.motivationalquotes.databinding.CustomAuthorItemBinding

interface AuthorClickListener {
    fun onClick(view: CustomAuthorItemBinding?, position: Int, authorName:String)
}