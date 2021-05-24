package com.jjg.borderanimview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import com.jjg.borderanimview.util.DisplayUtil


class BorderAnimView : RelativeLayout {
    private lateinit var borderView: BorderView
    private lateinit var tvMain: TextView

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        getAttrs(attrs, defStyle)
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {
        getAttrs(attrs)
    }

    init {
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflater.inflate(R.layout.button_border_anim, null)
        addView(view)
        initView()
    }

    private fun getAttrs(attrs: AttributeSet) {
        var typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.BorderAnimView
        )
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        var typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.BorderAnimView,
            defStyle,
            0
        )
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        // icon 변경
        var resIdMoveIcon =
            typedArray.getResourceId(R.styleable.BorderAnimView_move_icon, R.drawable.icon)
        setMoveIcon(BitmapFactory.decodeResource(resources, resIdMoveIcon))

        // stroke Width 값 변경
        var strokeWidth =
            typedArray.getDimensionPixelSize(
                R.styleable.BorderAnimView_stroke_width,
                DisplayUtil.dpToPx(context, 5).toInt()
            )
        setStrokeWidth(strokeWidth)

        // stroke Color 변경
        var strokeColor =
            typedArray.getColor(
                R.styleable.BorderAnimView_stroke_color,
                Color.BLACK
            )
        setStrokeColor(strokeColor)

        // text 변경
        var text = typedArray.getString(R.styleable.BorderAnimView_text) ?: ""
        setText(text)

        // text size 변경
        var textSize =
            typedArray.getDimensionPixelSize(
                R.styleable.BorderAnimView_text_size,
                DisplayUtil.dpToPx(context, 5).toInt()
            )
        setTextSize(textSize)

        // text Color 변경
        var textColor =
            typedArray.getColor(
                R.styleable.BorderAnimView_text_color,
                Color.BLACK
            )
        setTextColor(textColor)

        //minHeight 값 변경
        var minHeight =
            typedArray.getDimensionPixelSize(
                R.styleable.BorderAnimView_min_height,
                DisplayUtil.dpToPx(context, 30).toInt()
            )
        setMinHeight(minHeight)

        // background 변경
        var resIdBg =
            typedArray.getResourceId(R.styleable.BorderAnimView_bg, 0)
        setBackground(resIdBg)

        //font
        val fontFamilyId =
            typedArray.getResourceId(R.styleable.BorderAnimView_android_fontFamily, 0)
        if (fontFamilyId > 0)
            setFont(fontFamilyId)

        typedArray.recycle()
    }


    private fun initView() {
        borderView = findViewById(R.id.border_view)
        tvMain = findViewById(R.id.tv_main)
        initMainView()
        initBorderView()
    }

    private fun initMainView() {
        tvMain.post {
            var layoutParams = LayoutParams(tvMain.width, tvMain.height)
            layoutParams.leftMargin = borderView.halfWidthMoveIcon
            layoutParams.rightMargin = borderView.halfWidthMoveIcon
            layoutParams.topMargin = borderView.halfHeightMoveIcon
            layoutParams.bottomMargin = borderView.halfHeightMoveIcon

            tvMain.layoutParams = layoutParams
        }
    }

    private fun initBorderView() {
        tvMain.post {
            borderView.WIDTH = tvMain.width
            borderView.HEIGHT = tvMain.height

            borderView.MARGIN_TOP = tvMain.marginTop
            borderView.MARGIN_BOTTOM = tvMain.marginBottom
            borderView.MARGIN_LEFT = tvMain.marginLeft
            borderView.MARGIN_RIGHT = tvMain.marginRight

            borderView.initBorder()
        }
    }

    private fun setMoveIcon(bitmap: Bitmap) {
        borderView.moveIcon = bitmap
    }

    private fun setStrokeWidth(strokeWidth: Int) {
        borderView.setStrokeWidth(strokeWidth)
    }

    private fun setStrokeColor(strokeColor: Int) {
        borderView.setStrokeColor(strokeColor)
    }

    private fun setText(text: String) {
        tvMain.text = text
    }

    private fun setTextSize(textSize: Int) {
        tvMain.textSize = textSize.toFloat()
    }

    private fun setTextColor(textColor: Int) {
        tvMain.setTextColor(textColor)
    }

    private fun setMinHeight(minHeight: Int) {
        tvMain.minHeight = minHeight
    }

    private fun setBackground(resId: Int) {
        tvMain.setBackgroundResource(resId)
    }

    private fun setFont(resId: Int) {
        tvMain.typeface = ResourcesCompat.getFont(context, resId)
    }
}