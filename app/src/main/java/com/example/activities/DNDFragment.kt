package com.example.activities

import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout

class DNDFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_table, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction().apply {
            replace(R.id.flTop, TableTopFragment())
            replace(R.id.flBottom, TableBottomFragment())
            commit()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        val llTop = requireView().findViewById<LinearLayout>(R.id.llTop)
        val llBottom = requireView().findViewById<LinearLayout>(R.id.llBottom)
        val dragView = requireView().findViewById<View>(R.id.dragView)
        dragView.setOnLongClickListener{
            val clipText = "This is our ClipData text"
            val item = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true
        }

        llTop.setOnDragListener(dragListener)
        llBottom.setOnDragListener(dragListener)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DNDFragment()
    }
}