package com.example.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_counter.*

class CounterActivity : AppCompatActivity(), CounterView {

    private val presenter = CounterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

        presenter.onCreate()

        increment.setOnClickListener { presenter.increment() }
        decrement.setOnClickListener { presenter.decrement() }
    }

    override fun render(counterViewState: CounterViewState) {
        counter.text = counterViewState.counterValue.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
