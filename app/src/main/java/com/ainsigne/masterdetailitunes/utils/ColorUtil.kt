package com.ainsigne.masterdetailitunes.utils

import android.graphics.Color

class ColorUtil {
    companion object{
        fun getRandomColor(num : Int) : Int {
            return when (num) {
                0 -> {
                    Color.RED
                }
                1 -> {
                    Color.BLUE
                }
                2 -> {
                    Color.GREEN
                }
                3 -> {
                    Color.DKGRAY
                }
                4 -> {
                    Color.parseColor("#1E90FF")
                }
                5 -> {
                    Color.parseColor("#ffa500")
                }
                6 -> {
                    Color.MAGENTA
                }
                7 -> {
                    Color.CYAN
                }
                8 -> {
                    Color.parseColor("#ff8c00")
                }
                9 -> {
                    Color.parseColor("#ffd700")
                }
                10 -> {
                    Color.parseColor("#228B22")
                }
                11 -> {
                    Color.parseColor("#006400")
                }
                12 -> {
                    Color.parseColor("#008080")
                }
                13 -> {
                    Color.parseColor("#4682B4")
                }
                14 -> {
                    Color.parseColor("#191970")
                }
                15 -> {
                    Color.parseColor("#B8860B")
                }
                16 -> {
                    Color.parseColor("#800000")
                }
                17 -> {
                    Color.parseColor("#2F4F4F")
                }
                18 -> {
                    Color.parseColor("#778899")
                }
                19 -> {
                    Color.parseColor("#BC8F8F")
                }
                else -> {
                    Color.BLACK
                }
            }
        }
    }
}