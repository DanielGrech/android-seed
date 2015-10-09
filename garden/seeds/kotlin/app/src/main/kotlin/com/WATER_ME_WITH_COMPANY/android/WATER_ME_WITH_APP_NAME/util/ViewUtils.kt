package com.{{company_name}}.android.{{app_package_name_prefix}}.util

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.TextUtils
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

/**
 * Runs the given action in an {@link OnPreDrawListener}
 *
 * @param action The action to perform
 */
public fun View.onPreDraw(action: () -> Boolean) {
    viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            viewTreeObserver.removeOnPreDrawListener(this)
            return if (isAttachedToWindow) action() else true
        }
    })
}

public fun View.onLayout(action: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            if (isAttachedToWindow) {
                action()
            }
        }
    })
}

/**
 * Set the visibility of all the given views to [View.GONE]
 */
public fun View.hide() {
    visibility = View.GONE
}

/**
 * Set the visibility of all the given views to [View.INVISIBLE]
 */
public fun View.hideInvisible() {
    visibility = View.INVISIBLE
}

/**
 * Set the visibility of all the given views to [View.VISIBLE]
 */
public fun View.show() {
    visibility = View.VISIBLE
}

public fun View.showWhen(condition : Boolean) {
    if (condition) {
        this.show()
    } else {
        this.hide()
    }
}

public fun View.hideWhen(condition : Boolean) {
    showWhen(!condition)
}

public fun View.isGone(): Boolean {
    return this.visibility == View.GONE
}

public fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

/**
 * Set the given string as the text of the given TextView, or hide the view completely if it
 * is empty
 *
 * @param text The text to set
 */
public fun TextView.setTextOrHide(text: CharSequence) {
    if (TextUtils.isEmpty(text)) {
        this.hide()
    } else {
        this.text = text
        this.show()
    }
}

public fun View.startActivity(intent: Intent) {
    val activityOpts: ActivityOptions
    if (Api.isMin(Api.MARSHMALLOW)) {
        activityOpts = ActivityOptions.makeClipRevealAnimation(this, width / 2, height / 2, width, height)
    } else {
        activityOpts = ActivityOptions.makeScaleUpAnimation(this, width / 2, height / 2, width, height)
    }
    context.startActivity(intent, activityOpts.toBundle())
}

public fun ImageView.setImageUrl(url: String) {
    Picasso.with(this.getContext())
            .load(url)
            .fit()
            .into(this)
}