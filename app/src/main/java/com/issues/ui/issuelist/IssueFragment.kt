package com.issues.ui.issuelist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.ColumnInfo
import com.issues.R
import com.issues.data.room.AppDatabase
import com.issues.data.room.Issue
import com.issues.ui.MainActivity
import com.issues.ui.resp.IssueResponseItem
import kotlinx.android.synthetic.main.issue_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class IssueFragment : Fragment(), ClickListener {

    companion object {
        fun newInstance() = IssueFragment()
    }

    private lateinit var viewModel: IssueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.issue_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(IssueViewModel::class.java)
        viewModel.clickListener = this

        GlobalScope.launch(Dispatchers.Main) {
            val li = AppDatabase.getInstance(context?.applicationContext!!).IssueDao().getAll() as ArrayList<Issue>
            if(li.size > 0){
                updateData(li)
            }else{
                viewModel.getIssues()
            }
        }
    }

    private fun updateData(list: ArrayList<Issue>){
        viewModel.list = list
        val issueListAdapter = IssueListAdapter(requireContext(), list, this)
        rvIssues.adapter = issueListAdapter
        rvIssues.layoutManager = LinearLayoutManager(requireContext())
        rvIssues.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))

    }

    override fun loadData(list: ArrayList<IssueResponseItem>){
        var l = ArrayList<Issue>()
        for(item in list){
            val id = item.user.id
            val title = item.title
            val desc = item.body
            val user = item.user.login
            val date = item.updated_at
            val avatar = item.user.avatar_url
            val comment = item.comments_url
            val isu = Issue(0,id, title, desc, user, date, avatar, comment)
            l.add(isu)
        }
        updateData(l)

        GlobalScope.launch {
            AppDatabase.getInstance(context?.applicationContext!!).IssueDao().insertAll(l)
        }
    }

    override fun onClick(pos: Int) {
        viewModel.pos = pos
        val act = requireActivity() as MainActivity
        act.fragment2()
    }
}