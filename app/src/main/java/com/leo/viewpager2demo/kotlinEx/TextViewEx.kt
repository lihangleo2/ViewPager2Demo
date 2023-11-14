package com.leo.viewpager2demo.kotlinEx

import android.content.Intent
import android.widget.TextView

/**
 * @Author leo
 * @Address https://github.com/lihangleo2
 * @Date 2023/10/24
 */
inline fun TextView.jumpToActivityWithClick(cls: Class<*>?) {
    this.setOnClickListener {
        it.context.startActivity(Intent(it.context, cls))
    }

}