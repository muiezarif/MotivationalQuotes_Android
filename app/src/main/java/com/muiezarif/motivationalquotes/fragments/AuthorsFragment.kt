package com.muiezarif.motivationalquotes.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager

import com.muiezarif.motivationalquotes.R
import com.muiezarif.motivationalquotes.activities.ListQuotesActivity
import com.muiezarif.motivationalquotes.adapters.AuthorRecyclerAdapter
import com.muiezarif.motivationalquotes.databinding.CustomAuthorItemBinding
import com.muiezarif.motivationalquotes.databinding.FragmentAuthorsBinding
import com.muiezarif.motivationalquotes.db.DbQueries
import com.muiezarif.motivationalquotes.db.models.AuthorModel
import com.muiezarif.motivationalquotes.interfaces.AuthorClickListener
import com.muiezarif.motivationalquotes.utils.AdHelper

/**
 * A simple [Fragment] subclass.
 */
class AuthorsFragment : Fragment(), AuthorClickListener {
    private lateinit var binding: FragmentAuthorsBinding
    private lateinit var dbQuery: DbQueries
    private lateinit var authorList:ArrayList<AuthorModel>
    private var adapter: AuthorRecyclerAdapter?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.fragment_authors, container, false)
        AdHelper.loadBannerAd(binding.adView)
        init()
        return binding.root
    }

    private fun init(){
        dbQuery= DbQueries(context)
        authorList=dbQuery.getAllAuthors()
        adapter= context?.let { AuthorRecyclerAdapter(it,authorList,this) }
        binding.rvAuthors.layoutManager= GridLayoutManager(context,2)
        binding.rvAuthors.adapter=adapter
        binding.etSearchAuthor.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(e: Editable?) {
                filter(e.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }

    private fun filter(s:String){
        var filteredList:ArrayList<AuthorModel> = ArrayList()
        authorList.forEach {
            if (it.name.toLowerCase().contains(s.toLowerCase())) {
                filteredList.add(it)
            }
        }
        adapter?.filterList(filteredList)
    }

    override fun onClick(view: CustomAuthorItemBinding?, position: Int, authorName: String) {
        var intent= Intent(context, ListQuotesActivity::class.java)
        intent.putExtra("authorName",authorName)
        Log.i("CheckLog",authorName)
        startActivity(intent)
    }

}
