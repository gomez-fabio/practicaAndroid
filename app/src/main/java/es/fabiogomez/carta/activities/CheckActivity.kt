package es.fabiogomez.carta.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import es.fabiogomez.carta.R


class CheckActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)

        findViewById<View>(R.id.pay_button).setOnClickListener { payCheck() }
    }

    private fun payCheck() {
        finish()
    }
}