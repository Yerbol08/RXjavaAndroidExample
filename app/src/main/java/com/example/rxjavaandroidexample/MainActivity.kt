package com.example.rxjavaandroidexample

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import com.example.rxjavaandroidexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.btnRX.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext, "RX", Toast.LENGTH_LONG).show()
            doSomeWord()
        })
    }
    private fun doSomeWord(){
        Observable.just(1,2,2,3,4,5,6,7,8)
            .filter{ value->
                return@filter value%2==1
            }
            .subscribe(getObserver())

    }
    var TAG = "TAG"
    private fun getObserver(): Observer<Int> {
        return object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe")
            }

            override fun onNext(t: Int) {
                Log.d(TAG, "onNext")
                binding.textView.append(t.toString())
                binding.textView.append("\n")
            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onComplete() {
                binding.textView.append("onComplete")
                binding.textView.append("\n")
                Log.d(TAG, "onComplete")
            }
        }
    }
}