package com.example.tip


import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.tip.databinding.ActivityMainBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlin.math.ceil


open class MainActivity: AppCompatActivity() {




    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel=ViewModelProvider(this).get(TotalViewModel::class.java)
        viewModel.getlivetotal()?.observe(this,{
            binding.resultTv.text="$it"
        } )
        binding.calculateBtn.setOnClickListener {
            val cost = binding.costEt.text.toString().toDouble()
            val checkedrb = binding.group.checkedRadioButtonId
            val tip = when (checkedrb) {
                R.id.rb_20 -> 0.2
                R.id.rb_18 -> 0.18
                else -> 0.15

            }

            viewModel.totalvalue(cost,tip) {
                var total = cost * tip
                if (binding.roundUpTipSwitch.isChecked)
                    total = ceil(total)
                binding.resultTv.text = "$$total"
45
            }

            Snackbar
                .make(binding.root, "Reset Everything ", BaseTransientBottomBar.LENGTH_INDEFINITE)
                .setAction("Proceed"
                ) {
                    binding.costEt.text?.clear()
                    binding.group.check(R.id.rb_20)
                    binding.roundUpTipSwitch.isChecked = true
                    binding.resultTv.text = "Tip Amount"
                }
                .setBackgroundTint(getColor(R.color.white))
                .setTextColor(getColor(R.color.black))
                .show()

        }

    }


}
