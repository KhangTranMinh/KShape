package com.ktm.kshape.fragment

import com.ktm.kshape.R
import com.ktm.kshape.fragment.base.BaseFragment
import com.ktm.kshape.view.base.ShapeView

class TriangleFragment : BaseFragment(R.layout.fragment_triangle) {

    override val shapeView: ShapeView?
        get() = view?.findViewById(R.id.view_triangle)
}