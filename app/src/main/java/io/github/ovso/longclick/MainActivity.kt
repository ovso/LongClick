package io.github.ovso.longclick

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> handleTimer()
                MotionEvent.ACTION_UP -> compositeDisposable.clear()
            }

            true
        }
    }

    private fun handleTimer() {
        compositeDisposable.add(
            Observable.timer(
                3,
                TimeUnit.SECONDS
            ).subscribeOn(Schedulers.io())
                .subscribe {
                    println("Long click!!! 3 seconds")
                }
        );

    }
}