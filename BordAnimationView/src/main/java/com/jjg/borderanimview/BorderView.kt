package com.jjg.borderanimview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.jjg.borderanimview.R
import com.jjg.borderanimview.util.DisplayUtil.Companion.dpToPx

class BorderView : View {
    var MARGIN_TOP = dpToPx(context, 30).toInt()
    var MARGIN_BOTTOM = dpToPx(context, 0).toInt()
    var MARGIN_LEFT = dpToPx(context, 30).toInt()
    var MARGIN_RIGHT = dpToPx(context, 0).toInt()
    var WIDTH = dpToPx(context, 300).toInt()
    var HEIGHT = dpToPx(context, 100).toInt()

    private val RADIUS = HEIGHT / 2
    private var borderPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private lateinit var borderPath: Path

    private lateinit var pathMeasure: PathMeasure
    private var pathLength = 0f

    private var step = 1f
    private var distance = 0f
    private var pos: FloatArray = FloatArray(2)
    private var tan: FloatArray = FloatArray(2)
    private var iconMatrix = Matrix()

    var moveIcon: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon)
        set(value) {
            field = value
            halfWidthMoveIcon = field.width / 2
            halfHeightMoveIcon = field.height / 2
        }

    var halfWidthMoveIcon = 0
    var halfHeightMoveIcon = 0

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet) {
        initPaint(attrs)
        initBorder()
    }

    private fun initPaint(set: AttributeSet) {
        borderPaint!!.color = Color.BLACK
        borderPaint!!.style = Paint.Style.STROKE
        borderPaint!!.strokeWidth = 3f
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    fun initBorder() {
        borderPath = Path()
        val rect = RectF(
            MARGIN_LEFT.toFloat(),
            MARGIN_TOP.toFloat(),
            (WIDTH - MARGIN_RIGHT).toFloat(),
            (HEIGHT +MARGIN_BOTTOM).toFloat()
        )
        borderPath!!.addRoundRect(rect, RADIUS.toFloat(), RADIUS.toFloat(), Path.Direction.CW)

        pathMeasure = PathMeasure(borderPath, true)
        pathLength = pathMeasure!!.length
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(borderPath!!, borderPaint!!)
        canvas.setMatrix(iconMatrix)
        if (distance <= pathLength) {
            iconMatrix!!.reset()
            pathMeasure!!.getPosTan(distance, pos, tan)
            iconMatrix!!.postTranslate(
                (pos[0] - halfWidthMoveIcon) / 2,
                (pos[1] - halfHeightMoveIcon) / 2
            )
            distance += step
            invalidate()
        } else {
            distance = 0f
            invalidate()
            // distance = 300;
        }
        canvas.drawBitmap(moveIcon!!, iconMatrix!!, null)
    }

    fun setStrokeWidth(borderWidth :Int){
        borderPaint.strokeWidth = borderWidth.toFloat()
    }

    fun setStrokeColor(strokeColor :Int){
        borderPaint.color  = strokeColor
    }
}