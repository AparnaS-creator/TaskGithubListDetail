package com.assignment.listassignment.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.listassignment.R
import com.assignment.listassignment.databinding.ActivityMainBinding

import com.assignment.listassignment.model.newlist.ItemList
import com.assignment.listassignment.model.newlist.NewsListResponse
import com.assignment.listassignment.ui.news.adapter.NewsListAdapter
import com.assignment.listassignment.utill.Utility.isNetworkAvailable
import com.assignment.listassignment.utill.showToast
import com.assignment.listassignment.widget.loader.UtilLoader
import kotlinx.android.synthetic.main.toolbar.view.*

class MainActivity : AppCompatActivity(),NewsListAdapter.ItemClickListener {


    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsListAdapter
    private lateinit var mUtilLoader: UtilLoader
    private var previousSelected = -1
    private var onClick = false
    var swipeCount = 0
lateinit var search:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = NewsViewModel()
        binding.viewModel = viewModel
        search="Android"
        init()
        attachObserver()
        callAPI()


        binding.swipeRefreshLayout.setOnRefreshListener {

            swipeCount += 1;
            if (swipeCount > 0) {
                callAPI()
            }
            adapter.notifyDataSetChanged()

            binding.swipeRefreshLayout.isRefreshing = false
        }



        binding.svSearch.setImeOptions(EditorInfo.IME_ACTION_DONE);

       // binding.svSearch.setOnQueryTextListener(SearchView.OnQueryTextListener)
        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });*/


    }

    /**
     * Init variables and set values
     */
    private fun init() {
        mUtilLoader = UtilLoader(this)
        binding.idToolbar.tvTitle.text = getString(R.string.app_name)
        val supportList = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNews.layoutManager = supportList
    }

 
    /**
     * call apis
     */
    private fun callAPI() {
        if (isNetworkAvailable(this@MainActivity)) {
            viewModel.callNewsListApi(
                this@MainActivity,
                search
            )
        } else {
            noNewsFound()
            showToast(this@MainActivity, getString(R.string.no_internet),Toast.LENGTH_SHORT)

        }
    }

    /**
     * when no News available
     */
    private fun noNewsFound() {
        binding.tvRecordFound.visibility = View.VISIBLE
        binding.rvNews.visibility = View.GONE
    }

    /**
     * attach observer for api calls
     */
    private fun attachObserver() {
        viewModel.isLoading.observe(this, androidx.lifecycle.Observer<Boolean> {
            it?.let {
                showLoadingDialog(it)
            }
        })
        viewModel.apiError.observe(this, androidx.lifecycle.Observer<String> {
            it?.let {
                //nothing to do
              showToast(this@MainActivity, getString(R.string.no_record),Toast.LENGTH_SHORT)
            }
        })
        viewModel.apiResponse.observe(this, androidx.lifecycle.Observer<Any> {
            it?.let {
                if (it is NewsListResponse) {
                    try {
                        
                           
                                when {
                                    it.items == null -> noNewsFound()
                                    it.items!!.size > 0 -> {
                                        binding.tvRecordFound.visibility = View.GONE
                                        binding.rvNews.visibility = View.VISIBLE
                                        adapter =
                                            NewsListAdapter(
                                                this@MainActivity,
                                                it.items!!
                                            )
                                        binding.rvNews.adapter = adapter
                                        adapter.notifyDataSetChanged()
                                    }
                                    else -> noNewsFound()
                                }
                           
                        
                        
                    } catch (e: Exception) {
                        e.printStackTrace()
                        noNewsFound()
                    }
                
                }
            }
        })
    }

    /**
     * show / hide loader
     */
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


    override fun onItemClick(data: ItemList, position: Int) {
        if (!onClick) {
            onClick = true
            if (position != previousSelected) {
                previousSelected = position
            }

            // binding.idToolbar.tvTitle.text=data.title

        }
    }


}
