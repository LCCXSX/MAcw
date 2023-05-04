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
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var userTypeRadioGroup: RadioGroup
    lateinit var learnerRadioButton: RadioButton
    lateinit var educatorRadioButton: RadioButton
    lateinit var database: FirebaseDatabase
    var db = Firebase.firestore
    //private lateinit var learner: Learner
    //private lateinit var educator:Educator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginButton=findViewById<Button>(R.id.loginButton)
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
                    "Password" to password
                )
                //db.collection("users").document("Educators").set(educator)

                db.collection("Users").document("Educators").collection(email).add(educator)
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
                    "Password" to password
                )

                db.collection("Users").document("Learners").collection(email).add(learner)
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

            // 登录用户

           db.collection("Users").document(selectedUserType)
                .collection(email)
                .whereEqualTo("emailAddress", email)
                .get()
                .addOnSuccessListener { task ->

                        val pass=task.documents[0].data?.get("password")
                        if(pass==password){
                            if(selectedUserType=="Learner"){
                                val intent = Intent(this, LearnerActivity::class.java)
                                startActivity(intent)
                                finish()
                            }

                        }


                }
               .addOnFailureListener { exception ->
                   Log.e("wode", "Error getting documents: ", exception)

                    }


//            val query = db.collection("Users")
//                .whereEqualTo("email", email)
//                .whereEqualTo("password", password)
//                .limit(1)
//
//
//// 执行查询，并在结果准备好时进行处理
//            query.get()
//                .addOnSuccessListener { querySnapshot ->
//                    // 检查是否有匹配的文档
//                    if (!querySnapshot.isEmpty) {
//                        val documentSnapshot = querySnapshot.documents[0]
//                        // 从文档中提取用户类型
//                        val userType = documentSnapshot.getString("userType")
//                        // 检查用户类型并重定向到相应的页面
//                        if (userType == "Educator") {
//                            // 将用户重定向到 educator 页面
//                            val intent = Intent(this, EducatorActivity::class.java)
//                            startActivity(intent)
//                            finish()
//                        } else if (userType == "Learner") {
//                            // 将用户重定向到 learner 页面
//                            val intent = Intent(this, LearnerActivity::class.java)
//                            startActivity(intent)
//                            finish()
//                        }
//                    } else {
//                        // 没有找到匹配的文档，显示错误消息
//                    }
//                }
//                .addOnFailureListener { exception ->
//                    "Not find"// 处理查询失败的情况
//                }
        }
    }
}
