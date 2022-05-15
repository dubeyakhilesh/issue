package com.issues.ui.issuelist

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.issues.R
import com.issues.data.room.Issue
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class IssueListAdapter(context: Context?,
                       list: ArrayList<Issue>, clickListener: ClickListener):
                        RecyclerView.Adapter<IssueListAdapter.MyItem>() {

    private val context: Context? = context
    private val list: ArrayList<Issue> = list
    private val clickListener: ClickListener = clickListener

    class MyItem(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        var tvUser: TextView = itemView.findViewById(R.id.tvUser)
        var tvDate: TextView = itemView.findViewById(R.id.tvDate)
        var imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyItem {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyItem(view)
    }

    override fun onBindViewHolder(holder: MyItem, position: Int) {
        val item = list[position]
        val body = item.description ?: ""
        if(body.length > 200)
            holder.tvDescription.text = Html.fromHtml(body.substring(0, 200))
        else
            holder.tvDescription.text = Html.fromHtml(body ?: "")
        holder.tvTitle.text = Html.fromHtml(item.title ?: "")
        holder.tvUser.text = item.user ?: ""
        holder.tvDate.text = getDateOnly(item.date ?: "") ?: ""
        val image = item.avatar ?: ""
        if(image.isNotEmpty())
            Picasso.with(context).load(image).into(holder.imgAvatar)
        holder.itemView.setOnClickListener {
            clickListener.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun getDateOnly(date: String): String {
        if(date.isNullOrEmpty())
            return date
        var date= SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date)
        return SimpleDateFormat("MM-dd-yyyy").format(date)
    }

}