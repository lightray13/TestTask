package com.test.task.ui.companyList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.task.R
import com.test.task.adapter.CompanyListAdapter
import com.test.task.adapter.OnItemClickCallback
import com.test.task.databinding.ActivityMainBinding
import com.test.task.ui.company.CompanyActivity
import com.test.task.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnItemClickCallback {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private var companyListAdapter = CompanyListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        supportActionBar?.title = resources.getString(R.string.companies)

        viewModel.loadCompanyListFromApi()
        observeViewModel()
        initializeViews()
    }

    private fun initializeViews() {
        binding.companyListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = companyListAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(this) {
            binding.companyListLoading.visibility = if (viewModel.isListEmpty() && it) VISIBLE else GONE
        }
        viewModel.companyList.observe(this) {
            companyListAdapter.updateList(it)
        }
    }

    override fun onItemClick(id: String, name: String) {
        startActivity(Intent(this, CompanyActivity::class.java)
            .apply {
                putExtra(Constants.EXTRA_COMPANY_ID, id)
                putExtra(Constants.EXTRA_COMPANY_NAME, name)
            })
    }
}