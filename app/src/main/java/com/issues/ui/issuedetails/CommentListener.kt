package com.issues.ui.issuedetails

import com.issues.ui.resp.CommentResponseItem

interface CommentListener {
    fun onCommentLoad(list: ArrayList<CommentResponseItem>)
}