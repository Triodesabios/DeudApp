package melerospaw.deudapp.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import butterknife.OnClick
import melerospaw.deudapp.R

const val INFINITY_CHAR = "\u221e"
const val NEGATIVE_INFINITY_CHAR = "-\u221e"

fun String.isInfinityCharacter() = isPositiveInfinityCharacter() || isNegativeInfinityCharacter()

fun String.isPositiveInfinityCharacter() = this == INFINITY_CHAR

fun String.isNegativeInfinityCharacter() = this == NEGATIVE_INFINITY_CHAR

fun getInfiniteFloat() = Float.POSITIVE_INFINITY

fun getNegativeInfiniteFloat() = Float.NEGATIVE_INFINITY

fun Float.getSignedInfinityCharacter() =
    if (this == Float.POSITIVE_INFINITY) {
        INFINITY_CHAR
    } else {
        NEGATIVE_INFINITY_CHAR
    }

fun Any.isInfiniteFloat() =
        when (this) {
            is Float -> {
                this == Float.POSITIVE_INFINITY || this == Float.NEGATIVE_INFINITY
            }
            is String -> {
                try {
                    val floatValue = toFloat()
                    floatValue == Float.POSITIVE_INFINITY || floatValue == Float.NEGATIVE_INFINITY
                } catch (e: NumberFormatException) {
                    false
                }
            }
            else -> false
        }

fun mostrarInfinityDialog(context: Context,
                          positiveCallback: DialogInterface.OnClickListener? = null,
                          negativeCallback: DialogInterface.OnClickListener? = null) {
    AlertDialog.Builder(context)
            .setTitle(R.string.dialog__infinite_amount_title)
            .setMessage(R.string.dialog__infinite_amount_message)
            .setPositiveButton(R.string.si) { dialog, which ->
                    positiveCallback?.onClick(dialog, which)
                    dialog?.dismiss()
            }
            .setNegativeButton(R.string.no) { dialog, which ->
                negativeCallback?.onClick(dialog, which)
                dialog?.dismiss()
            }
            .show()
}