package com.seungsu.pokemon.presentation.home.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.seungsu.pokemon.presentation.R

class GridSpacingItemDecoration(
    private val padding: Int = R.dimen.space_8
): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val pixelOfPadding = view.context.resources.getDimensionPixelSize(padding)

        val position: Int = parent.getChildLayoutPosition(view)
        outRect.apply {
            left = pixelOfPadding
            right = pixelOfPadding
            top = pixelOfPadding
            bottom = pixelOfPadding
        }
    }
}
