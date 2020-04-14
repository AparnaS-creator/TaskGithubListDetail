package com.assignment.listassignment.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.listassignment.R
import com.assignment.listassignment.databinding.ActivityMainBinding
import com.assignment.listassignment.databinding.ActivityWebviewBinding
import com.assignment.listassignment.model.newlist.ItemList
import com.assignment.listassignment.model.newlist.NewsListResponse
import com.assignment.listassignment.ui.news.adapter.NewsListAdapter
import com.assignment.listassignment.utill.*
import com.assignment.listassignment.widget.loader.UtilLoader
import kotlinx.android.synthetic.main.toolbar.view.*

class WebViewActivity : AppCompatActivity() {


    lateinit var binding: ActivityWebviewBinding
    lateinit var viewModel: NewsViewModel
    private lateinit var mUtilLoader: UtilLoader
    var pro_url=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_webview)
        viewModel = NewsViewModel()
        binding.viewModel = viewModel
        init()
    }

    /**
     * Init variables and set values
     */
    private fun init() {
        mUtilLoader = UtilLoader(this)
        try {
            try {
                val intent = intent.extras!!
                if (intent != null) {
                    pro_url = intent.getString(PROJECT_LINK, "")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


        binding.idToolbar.tvTitle.text = getString(R.string.app_name)
        binding.idToolbar.ivLeft.visibility=View.VISIBLE
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.settings.loadWithOverviewMode = true
        binding.webview.settings.useWideViewPort = true
        binding.webview.settings.domStorageEnabled = true


        binding.webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        binding.webview.loadUrl(pro_url)
    }

    private fun showLoadingDialog(show: Boolean) {
        if (show) mUtilLoader.startLoader(this) else mUtilLoader.stopLoader()
    }


    override fun onResume() {
        super.onResume()
        try {

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onBackPressed() {
        goBack()
    }

    /**
     * go back
     */
    private fun goBack() {
        this@WebViewActivity.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)    // for close
    }


}