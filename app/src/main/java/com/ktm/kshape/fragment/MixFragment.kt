package com.ktm.kshape.fragment

import com.ktm.kshape.R
import com.ktm.kshape.fragment.base.BaseFragment
import com.ktm.kshape.view.base.ShapeView

class MixFragment : BaseFragment(R.layout.fragment_mix) {

    override val shapeView: ShapeView?
        get() = view?.findViewById(R.id.view_mix)
}