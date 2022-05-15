package com.issues.ui.issuedetails

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.issues.R
import com.issues.ui.resp.CommentResponseItem
import com.issues.ui.resp.IssueResponseItem
import com.squareup.picasso.Picasso

class CommentListAdapter(context: Context?,
                         list: ArrayList<CommentResponseItem>):
                        RecyclerView.Adapter<CommentListAdapter.MyItem>() {

    private val context: Context? = context
    private val list: ArrayList<CommentResponseItem> = list

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

    private fun getValidString(string: String): String{
        return string ?: ""
    }

    override fun onBindViewHolder(holder: MyItem, position: Int) {
        val item = list[position]
        val body = item.body ?: ""
        if(body.length > 200)
            holder.tvDescription.text = body.substring(0, 200)
        else
            holder.tvDescription.text = body ?: ""
        holder.tvTitle.visibility = View.GONE
        holder.tvUser.text = item.user.login ?: ""
        holder.tvDate.visibility = View.GONE
        val image = item.user.avatar_url ?: ""
        if(image.isNotEmpty())
            Picasso.with(context).load(image).into(holder.imgAvatar)

    }

    override fun getItemCount(): Int {
        return list.size
    }

}