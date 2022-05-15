package com.issues.ui.issuedetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.issues.R
import com.issues.ui.issuelist.IssueListAdapter
import com.issues.ui.issuelist.IssueViewModel
import com.issues.ui.resp.CommentResponseItem
import kotlinx.android.synthetic.main.issue_details_fragment.*
import kotlinx.android.synthetic.main.issue_fragment.*

class IssueDetailsFragment : Fragment(), CommentListener {

    companion object {
        fun newInstance() = IssueDetailsFragment()
    }

    private lateinit var viewModel: IssueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.issue_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(IssueViewModel::class.java)
        viewModel.commentListener = this
        viewModel.getComments()
        data()
    }

    private fun data(){
        val item = viewModel.list[viewModel.pos]
        tvIssue.text = item.description
    }

    override fun onCommentLoad(list: ArrayList<CommentResponseItem>) {
        val adapter = CommentListAdapter(requireContext(), list)
        rvComments.adapter = adapter
        rvComments.layoutManager = LinearLayoutManager(requireContext())
        rvComments.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        rvComments.setHasFixedSize(true);
        rvComments.isNestedScrollingEnabled = false;
    }

}