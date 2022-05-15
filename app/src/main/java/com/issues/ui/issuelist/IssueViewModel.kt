package com.issues.ui.issuelist

import androidx.lifecycle.ViewModel
import com.issues.data.network.MyApi
import com.issues.data.room.Issue
import com.issues.ui.issuedetails.CommentListener
import com.issues.ui.resp.CommentResponse
import com.issues.ui.resp.IssueResponse
import com.issues.ui.resp.IssueResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IssueViewModel : ViewModel() {

    lateinit var list: ArrayList<Issue>
    var clickListener: ClickListener? = null
    var commentListener: CommentListener? = null
    var pos: Int = 0

    fun getIssues(){
        MyApi().getIssues().enqueue(object :
            Callback<IssueResponse> {
            override fun onResponse(call: Call<IssueResponse>, response: Response<IssueResponse>) {
                var resp = response.body()
                if (resp != null) {
                    list = ArrayList()
                    clickListener?.loadData(resp)
                }
            }

            override fun onFailure(call: Call<IssueResponse>, t: Throwable) {

            }
        })
    }

    fun getComments(){
        val item = list[pos]
        MyApi().getComments(item.comment!!).enqueue(object :
            Callback<CommentResponse> {
            override fun onResponse(call: Call<CommentResponse>, response: Response<CommentResponse>) {
                var resp = response.body()
                if (resp != null) {
                    commentListener?.onCommentLoad(resp)
                }
            }

            override fun onFailure(call: Call<CommentResponse>, t: Throwable) {

            }
        })
    }
}