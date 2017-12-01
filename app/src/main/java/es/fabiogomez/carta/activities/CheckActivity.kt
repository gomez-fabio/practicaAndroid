package es.fabiogomez.carta.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import es.fabiogomez.carta.R


class CheckActivity : AppCompatActivity() {

    companion object {

        fun intent(context: Context): Intent {
            return Intent(context, CheckActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)

        findViewById<View>(R.id.pay_button).setOnClickListener { payCheck() }
        findViewById<View>(R.id.cancel_pay_button).setOnClickListener { cancelPayCheck() }
    }

    private fun cancelPayCheck() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    // NOS TENDRAN QUE PASAR LA MESA
    private fun payCheck() {
        // TENDREMOS QUE VACIAR LA LISTA DE PLATOS DE LA MESA QUE NOS PASAN
        setResult(Activity.RESULT_OK)
        finish()
    }
}