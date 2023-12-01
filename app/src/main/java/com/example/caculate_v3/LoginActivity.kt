package com.example.caculate_v3

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.caculate_v3.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var biding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(biding.root)

        biding.button.setOnClickListener {
            val phoneNumber = biding.editTextPhone.text.toString()
            val password = biding.editTextNumberPassword.text.toString()
            val context: Context = this


            val loginRequest = LoginRequest(phoneNumber, password)

            val retrofit = Retrofit.Builder()
                .baseUrl("https://fitfo-api.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(ApiService::class.java)

            val call = apiService.loginUser(loginRequest)

            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        // Xử lý phản hồi thành công
                        val message = response.body()?.name ?: "Unknown response"
//                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        // TODO: Xử lý message
                        val intent = Intent(this@LoginActivity, ProfileActivity::class.java)
                        intent.putExtra("textView", message)
                        startActivity(intent);

                    } else {
                        // Xử lý phản hồi không thành công
                        // TODO: Xử lý lỗi
                        Toast.makeText(context, "phản hồi không thành công", Toast.LENGTH_SHORT).show();
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    // Xử lý lỗi khi request không thành công
                    // TODO: Xử lý lỗi
                    Toast.makeText(context, "lỗi khi request không thành công", Toast.LENGTH_SHORT).show();
                }
            })
        }
    }
}