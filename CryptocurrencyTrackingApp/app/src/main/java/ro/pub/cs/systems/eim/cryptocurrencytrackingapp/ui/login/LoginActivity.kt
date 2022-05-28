package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.login

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.databinding.ActivityLoginBinding
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    private var register = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        register = intent.getBooleanExtra(Constants.REGISTER, false)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
//        auth.signOut()

        binding.btnLoginRegister.setOnClickListener {
            loginOrRegister()
        }
    }

    private fun loginOrRegister() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            if (!register) {
                login(email, password)
            } else {
                register(email, password)
            }
        }
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")

                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)

                    }
                }
    }

    private fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")

                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)

                    }
                }
    }

    private fun redirectUI() {}
}