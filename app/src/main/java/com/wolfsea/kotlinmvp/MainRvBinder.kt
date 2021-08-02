package com.wolfsea.kotlinmvp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wolfsea.kotlinmvp.databinding.RvItemBinding
import com.wolfsea.kotlinmvp.drakeer.ItemViewBinder

/**
 *@desc
 *@author liuliheng
 *@time 2021/5/30  17:21
 **/
class MainRvBinder : ItemViewBinder<String, MainRvBinder.MainViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): MainViewHolder {

        val itemBinding : RvItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.rv_item, parent, false)
        return MainViewHolder(itemBinding.root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, item: String) {
        holder.bindData(item)
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val itemBinding = DataBindingUtil.findBinding<RvItemBinding>(view)!!

        fun bindData(item: String) {

            itemBinding.rvItemContentTv.text = item
        }
    }

}