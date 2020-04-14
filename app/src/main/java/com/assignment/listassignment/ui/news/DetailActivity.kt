package com.assignment.listassignment.ui.news

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.assignment.listassignment.R
import com.assignment.listassignment.databinding.ActivityDetailBinding
import com.assignment.listassignment.model.newlist.ContributorModel
import com.assignment.listassignment.repository.MyConstant
import com.assignment.listassignment.ui.news.adapter.ContributorAdapter
import com.assignment.listassignment.ui.news.adapter.NewsListAdapter
import com.assignment.listassignment.utill.*
import com.assignment.listassignment.utill.Utility.isNetworkAvailable
import com.assignment.listassignment.widget.loader.UtilLoader




class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    lateinit var viewModel: NewsViewModel
    private lateinit var mUtilLoader: UtilLoader
    private var source = ""
    private var REQUEST_PHONE_CALL: Int = 1
    var project_name=""
    var pro_url=""
    var pro_desc=""
    var pro_img=""
    var pro_contributor_url=""
    private lateinit var adapter: ContributorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        viewModel = NewsViewModel()
        binding.viewModel = viewModel
        init()
        onClickListeners()
        attachObserver()
        callAPI()
    }

    /**
     * Init variables and set values
     */
    private fun init() {
        mUtilLoader = UtilLoader(this)

        val gridLayoutManager = GridLayoutManager(applicationContext, 3)
        binding.rvContributor.setLayoutManager(gridLayoutManager)

        binding.infoLink.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(PROJECT_LINK, pro_url)
            this.startActivity(intent)
            this.overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )  // for open
        }


        try {
            try {
                val intent = intent.extras!!
                if (intent != null) {
                    project_name = intent.getString(PRO_NAME, "")
                    pro_url = intent.getString(PROJECT_LINK, "")
                    pro_desc=intent.getString(PRO_DESC, "")
                    pro_img=intent.getString(PRO_IMG, "")

                    pro_contributor_url=intent.getString(PRO_COTRIBUTOR_URL, "")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    /**
     * all click listeners
     */
    private fun onClickListeners() {

    }




    /**
     * set all UI details
     */
    @SuppressLint("SetTextI18n")
    private fun setupUI() {
        binding.tvHeader.text=project_name
        binding.infoLink.text=pro_url
        binding.tvdescText.text=pro_desc
        showImage(binding.ivThumbnail, pro_img, com.assignment.listassignment.R.drawable.border)



    }

    /**
     * call apis
     */
    private fun callAPI() {
        if (isNetworkAvailable(this@DetailActivity)) {
            viewModel.callContributorListApi(
                this@DetailActivity,
                pro_contributor_url

            )
        } else {
            showToast(this@DetailActivity, getString(R.string.no_internet),Toast.LENGTH_SHORT)

        }
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
                showToast(this@DetailActivity, getString(R.string.no_internet),Toast.LENGTH_SHORT)

            }
        })
        viewModel.apiResponse.observe(this, androidx.lifecycle.Observer<Any> {
            it?.let {
             if (it is ContributorModel) {
                    try {
                        when {



                          /*  it.status == ResponseCodes.Success -> {
                                when {
                                    it.data != null -> setupUI(it)
                                    else -> showAlertDialog(it.message!!)
                                }
                            }
                            it.status == ResponseCodes.TOKEN_MISMATCH -> {
                                showCustomTokenExpireDialog(
                                    this,
                                    getString(R.string.app_name),
                                    it.message!!
                                )
                            }
                            else -> {
                                showAlertDialog(it.message!!)
                            }*/
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
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
    }


}