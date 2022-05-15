package com.issues.ui.issuelist

import com.issues.ui.resp.IssueResponseItem

interface ClickListener {
    fun onClick(pos: Int)
    fun loadData(list: ArrayList<IssueResponseItem>)
}