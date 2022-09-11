package app.ideea.tipcalculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider


class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var slider: Slider
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()

        editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if (editText.text.isNullOrBlank()) {
                    textView.visibility = View.GONE
                } else {
                    textView.visibility = View.VISIBLE
                    textView.text =
                        calculateTip(editText.text.toString().toDouble(), slider.value.toInt())
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
            }
        })

        slider.addOnChangeListener { slider, value, fromUser ->
            if (editText.text.isNullOrBlank().not()) {
                textView.text =
                    calculateTip(editText.text.toString().toDouble(), slider.value.toInt())
            }
        }
    }

    private fun bindViews() {
        editText = findViewById(R.id.edit_text)
        slider = findViewById(R.id.slider)
        textView = findViewById(R.id.text_view)
    }

    fun calculateTip(overall: Double, tipPercentage: Int): String {
        return "Tip amount: ${"%.2f".format(overall * tipPercentage / 100)}"
    }
}