package com.ktm.kshape.fragment

import com.ktm.kshape.R
import com.ktm.kshape.fragment.base.BaseFragment
import com.ktm.kshape.view.base.ShapeView

class SquareFragment : BaseFragment(R.layout.fragment_square) {

    override val shapeView: ShapeView?
        get() = view?.findViewById(R.id.view_square)
}