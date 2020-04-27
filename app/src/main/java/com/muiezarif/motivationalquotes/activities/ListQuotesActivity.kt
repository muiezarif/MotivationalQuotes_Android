package com.muiezarif.motivationalquotes.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.muiezarif.motivationalquotes.R
import com.muiezarif.motivationalquotes.adapters.QuoteRecyclerAdapter
import com.muiezarif.motivationalquotes.databinding.ActivityListQuotesBinding
import com.muiezarif.motivationalquotes.databinding.CustomQuoteItemBinding
import com.muiezarif.motivationalquotes.db.DbQueries
import com.muiezarif.motivationalquotes.db.models.QuotesModel
import com.muiezarif.motivationalquotes.interfaces.QuoteClickListener
import com.muiezarif.motivationalquotes.utils.AdHelper

class ListQuotesActivity : AppCompatActivity(), QuoteClickListener {
    private lateinit var binding: ActivityListQuotesBinding
    private lateinit var dbQuery: DbQueries
    private lateinit var quoteList:ArrayList<QuotesModel>
    private var adapter: QuoteRecyclerAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_list_quotes)
        AdHelper.loadBannerAd(binding.adView)
        AdHelper.loadInterstitialAd(this,getString(R.string.admob_full_ad))
        binding.quoteListHeader.ivBack.visibility= View.VISIBLE
        binding.quoteListHeader.ivBack.setOnClickListener {
            finish()
        }
        var authorName=intent.extras?.get("authorName")
        binding.quoteListHeader.tvHomeTitle.text=authorName.toString()
        dbQuery= DbQueries(this)
        quoteList=dbQuery.getQuotesByAuthor(authorName as String)
        quoteList.shuffle()
        adapter= QuoteRecyclerAdapter(this,quoteList,this)
        binding.rvQuotesList.adapter=adapter
    }

    override fun onResume() {
        super.onResume()
        AdHelper.showInterstitialAd()
    }

    override fun onStop() {
        super.onStop()
        AdHelper.reloadInterstitialAd()
    }

    override fun onClick(view: CustomQuoteItemBinding?, position: Int) {
        var intent= Intent(this,FullQuoteViewActivity::class.java)
        intent.putExtra("quotePos",position)
        intent.putExtra("list",adapter?.getList())
        intent.putExtra("from","listQuotes")
        startActivity(intent)
    }
}
