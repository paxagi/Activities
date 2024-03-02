package com.example.activities

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment() : Fragment() {
    private var images: ArrayList<Int>? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPagerOfImages: ViewPager2 =
            view.findViewById(R.id.viewPagerOfImages)
        val adapter = ViewPagerAdapter(images ?: arrayListOf(R.drawable.empty,))
        viewPagerOfImages.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        images = arguments?.obtainProfileFragmentState()?.images
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.saveProfileFragmentState(images)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        images = savedInstanceState?.obtainProfileFragmentState()?.images
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param images is array list of Int(ids of images res)
         * @return A new instance of fragment BlankFragment.
         */
        @JvmStatic
        fun newInstance(images: ArrayList<Int>) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    saveProfileFragmentState(images)
                }
            }
    }
}

private fun Bundle.saveProfileFragmentState(images: ArrayList<Int>?) {
    putIntegerArrayList(IMAGES, images)
}

private fun Bundle.obtainProfileFragmentState() = object { var images = getIntegerArrayList(IMAGES) }

private const val IMAGES = "images"