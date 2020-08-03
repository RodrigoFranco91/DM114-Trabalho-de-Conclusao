package br.com.rodrigo.dm114

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.findNavController
import br.com.rodrigo.dm114.R.id
import br.com.rodrigo.dm114.order.OrderFragmentDirections
import br.com.rodrigo.dm114.order.OrderListFragment

import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onNewIntent(intent: Intent) {
        if (intent.hasExtra("orderDetail")) {
            showOrder(intent.getStringExtra("orderDetail")!!)
        }
        super.onNewIntent(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val name = user.displayName
            val email = user.email
            setContentView(R.layout.activity_main)
            if (this.intent.hasExtra("orderDetail")) {
                showOrder(intent.getStringExtra("orderDetail")!!)
            }
        } else {
            val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build(), 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                setContentView(R.layout.activity_main)
            } else {
                Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun showOrder(orderInfo: String) {
        this.findNavController(id.nav_host_fragment).navigate(OrderFragmentDirections.actionShowOrder(orderInfo))
    }

    private fun showList() {
        this.findNavController(id.nav_host_fragment).navigate(OrderFragmentDirections.actionShowList())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            id.nav_sign_out -> {
                AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener {
                        this.recreate()
                    }
                true
            }
            id.nav_list -> {
                showList()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

