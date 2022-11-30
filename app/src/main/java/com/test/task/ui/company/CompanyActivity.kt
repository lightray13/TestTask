package com.test.task.ui.company

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import com.test.task.data.local.database.model.CompanyEntity
import com.test.task.databinding.ActivityCompanyBinding
import com.test.task.util.Constants
import com.test.task.util.ImageLoader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompanyActivity : AppCompatActivity() {
    private val viewModel: CompanyViewModel by viewModels()

    private lateinit var binding: ActivityCompanyBinding

    private var id: String? = null
    private var name: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCompanyBinding.inflate(layoutInflater).also { setContentView(it.root) }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (intent?.hasExtra(Constants.EXTRA_COMPANY_ID) == true) {
            id = intent?.getStringExtra(Constants.EXTRA_COMPANY_ID)
        }

        if (intent?.hasExtra(Constants.EXTRA_COMPANY_NAME) == true) {
            name = intent?.getStringExtra(Constants.EXTRA_COMPANY_NAME)
        }

        supportActionBar?.title = name ?: ""

        id?.let { viewModel.loadCompanyFromApi(it) }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(this) {
            binding.companyLoading.visibility = if (it) VISIBLE else GONE
        }

        id?.let {
            viewModel.companyById(it).observe(this) { company ->
                initializeViews(company)
            }
        }
    }

    private fun initializeViews(company: CompanyEntity) {
        ImageLoader.loadImage(binding.companyItemIconImageView, company.img ?: "")
        binding.companyItemNameTextView.text = company.name
        binding.companyItemDescriptionTextView.text = company.description
        binding.companyItemWebsiteTextView.text = company.www
        binding.companyItemWebsiteTitleTextView.visibility = if (company.www.isNullOrEmpty()) GONE else VISIBLE
        binding.companyItemWebsiteTextView.visibility = if (company.www.isNullOrEmpty()) GONE else VISIBLE
        binding.companyItemPhoneTextView.text = company.phone
        binding.companyItemPhoneTitleTextView.visibility = if (company.phone.isNullOrEmpty()) GONE else VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

}