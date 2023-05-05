package com.example.macw

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.macw.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var userTypeRadioGroup: RadioGroup
    lateinit var learnerRadioButton: RadioButton
    lateinit var educatorRadioButton: RadioButton
    lateinit var database: FirebaseDatabase
    var db = Firebase.firestore
    private lateinit var auth: FirebaseAuth
    //private lateinit var learner: Learner
    //private lateinit var educator:Educator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginButton=findViewById<Button>(R.id.loginButton)
        auth = Firebase.auth
        //val learnerRadioButton=findViewById<RadioButton>(R.id.learnerRadioButton)
        //val educatorRadioButton=findViewById<RadioButton>(R.id.educatorRadioButton)
        userTypeRadioGroup=findViewById(R.id.identityRadioGroup)
        learnerRadioButton=findViewById(R.id.learnerRadioButton)
        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // 检查电子邮件和密码是否为空
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this, "please input your email address and password",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val selectedUserType = when(userTypeRadioGroup.checkedRadioButtonId) {
                R.id.learnerRadioButton -> "Learner"
                R.id.educatorRadioButton -> "Educator"
                else -> null
            }
            if (selectedUserType == null) {
                Toast.makeText(
                    this, "please select your user type",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if(selectedUserType=="Educator"){
                val educator= hashMapOf<String,Any>(
                    "Email" to email,
                    "Password" to password,
                    "Educator" to "Educator"
                )
                //db.collection("users").document("Educators").set(educator)

                db.collection(selectedUserType).add(educator)
                    .addOnSuccessListener {
                        // 注册成功后跳转到主页
                        //Log.d(TAG, "DocumentSnapshot added with ID: ${it.id}")
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener {
                        // 注册失败，显示错误信息
                        Toast.makeText(this, "Registration failed: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            }else{
                val learner= hashMapOf<String,Any>(
                    "Email" to email,
                    "Password" to password,
                    "Learner" to "Learner"
                )

                db.collection(selectedUserType).add(learner)
                    .addOnSuccessListener {
                        // 注册成功后跳转到主页
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener {
                        // 注册失败，显示错误信息
                        Toast.makeText(this, "Registration failed: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // 检查电子邮件和密码是否为空
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this, "please input your email address and password",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // 获取用户类型
            val selectedUserType = when(userTypeRadioGroup.checkedRadioButtonId) {
                R.id.learnerRadioButton -> "Learner"
                R.id.educatorRadioButton -> "Educator"
                else -> null
            }

            // 检查用户类型是否选择
            if (selectedUserType == null) {
                Toast.makeText(
                    this, "please select your user type",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            Log.d("selectedUserType","${selectedUserType}")
            Log.d("doazhe ","run here")
            // 登录用户
            lateinit  var documentId:String
            db.collection(selectedUserType).whereEqualTo("Email",email).whereEqualTo("Password",password).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        Log.d("wode", "DocumentSnapshot data: ${document.documents[0].data}")
                        if(selectedUserType=="Educator"){
                            val intent = Intent(this, EducatorActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else if(selectedUserType=="Learner"){
                            val intent = Intent(this, LearnerActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    } else {
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }


        }
    }
}

