package com.test.task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.task.R
import com.test.task.data.local.database.model.CompanyEntity
import com.test.task.databinding.ItemCompanyListBinding
import com.test.task.util.ImageLoader

interface OnItemClickCallback {
    fun onItemClick(id: String, name: String)
}

class CompanyListAdapter(private val onItemClickCallback: OnItemClickCallback) : RecyclerView.Adapter<CompanyListAdapter.CompanyListViewHolder>() {
    private val companyList = mutableListOf<CompanyEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyListViewHolder {
        return CompanyListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_company_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CompanyListViewHolder, position: Int) {
        holder.bind(companyList[position], onItemClickCallback)
    }

    override fun getItemCount(): Int {
        return companyList.size
    }

    fun updateList(list: List<CompanyEntity>) {
        this.companyList.clear()
        this.companyList.addAll(list)
        notifyDataSetChanged()
    }

    class CompanyListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCompanyListBinding.bind(itemView)

        fun bind(model: CompanyEntity, onItemClickCallback: OnItemClickCallback) {

            ImageLoader.loadImage(binding.companyItemIconImageView, model.img ?: "")
            binding.companyItemNameTextView.text = model.name

            itemView.setOnClickListener {
                onItemClickCallback.onItemClick(model.id, model.name ?: "")
            }
        }
    }
}