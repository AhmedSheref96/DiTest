package com.example.ditest.ui.home.cart

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.ditest.R
import com.example.ditest.base.FragmentBinding
import com.example.ditest.databinding.CartFragmentBinding
import timber.log.Timber

class CartFragment : FragmentBinding() {

    private val viewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding<CartFragmentBinding>(inflater, R.layout.cart_fragment, container)
            .apply {
                val st = "I want THIS and THIS to be clickable"

                var spannable = SpannableString(st)
                var clickableSpan: ClickableSpan? = null
                clickableSpan = object : ClickableSpan() {
                    override fun onClick(p0: View) {
                        Timber.d("------------------------- clicked")
                        Toast.makeText(context, "99999999999", Toast.LENGTH_LONG).show()
                        Timber.d("-------------  end index ${spannable.getSpanEnd(clickableSpan) + 1}")
                        spannable = SpannableString(
                            spannable.replaceRange(
                                spannable.getSpanStart(clickableSpan),
                                spannable.getSpanEnd(clickableSpan) + 1,
                                "4444"
                            )
                        )
                        Timber.d("------------------------- clicked $spannable")
                        spannable.removeSpan(clickableSpan)
                        text.text = spannable
//                        text.movementMethod = LinkMovementMethod.getInstance()
                    }
                }

                spannable.setSpan(clickableSpan, 7, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//                spannable.removeSpan(clickableSpan)
                text.text = spannable
                text.movementMethod = LinkMovementMethod.getInstance()
            }.root
    }

}