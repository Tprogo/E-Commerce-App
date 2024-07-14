package com.example.e_comapplication.fragments

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.e_comapplication.DataStoreManager
import com.example.e_comapplication.R
import com.example.e_comapplication.databinding.FragmentExploreBinding
import com.example.e_comapplication.databinding.FragmentProfileBinding
import com.example.e_comapplication.models.auth_model.LoginUser
import com.example.e_comapplication.models.auth_model.RegisterUser
import com.example.e_comapplication.utils.NetworkResult
import com.example.e_comapplication.viewmodels.DataViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    val binding get() = _binding


    @Inject
    lateinit var dataStoreManager: DataStoreManager

    private lateinit var token: String

    val dataViewModel by activityViewModels<DataViewModel>()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            dataStoreManager.getToken().collect{tokenData ->
                if (tokenData != null) {
                    binding?.detProfileLayout?.root?.visibility = View.VISIBLE
                    binding?.registrationLayout?.root?.visibility = View.GONE
                    binding?.loginLayout?.root?.visibility = View.GONE

                    Log.d("Token TAG","$tokenData")
                } else{
                    binding?.detProfileLayout?.root?.visibility = View.GONE
                    binding?.registrationLayout?.root?.visibility = View.VISIBLE
                    binding?.loginLayout?.root?.visibility = View.GONE
                    Log.d("No Token TAG","No Token Found")
                }
            }
        }



        binding?.registrationLayout?.progressBarSignUp?.visibility = View.GONE


        binding?.registrationLayout?.signupbtn?.setOnClickListener{

            val email = binding?.registrationLayout?.emailedt?.text.toString()
            val password = binding?.registrationLayout?.passwordedt?.text.toString()
            val firstName =  binding?.registrationLayout?.firstName?.text.toString()
            val lastName =  binding?.registrationLayout?.lastName?.text.toString()

            if ( email.isNotEmpty() && password.isNotEmpty() &&
                firstName.isNotEmpty() && lastName.isNotEmpty()){
                val userInfo = RegisterUser(email = email, password = password, first_name = firstName, last_name = lastName)

                CoroutineScope(Dispatchers.IO).launch {
                    dataViewModel.register(userInfo)
                }
                Toast.makeText(context, "Successfully registered", Toast.LENGTH_SHORT).show()
                binding?.registrationLayout?.root?.visibility = View.GONE
                binding?.loginLayout?.root?.visibility = View.GONE
                binding?.detProfileLayout?.root?.visibility = View.VISIBLE

            } else{

                Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            }


        }




        dataViewModel.registerLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Error -> {
                    binding?.registrationLayout?.responsetext?.text = it.message

                }
                is NetworkResult.Loading -> {

                    binding?.registrationLayout?.progressBarSignUp?.visibility = View.VISIBLE

                }
                is NetworkResult.Success -> {
                    binding?.registrationLayout?.progressBarSignUp?.visibility = View.GONE
                    binding?.detProfileLayout?.root?.visibility = View.VISIBLE

                    lifecycleScope.launch {
                        dataStoreManager.saveToken(it.data!!.jwt)
                    }

                    lifecycleScope.launch {
                        dataStoreManager.saveUserId(it.data!!.id.toInt())
                    }

                }
            }
        })

//        binding?.registrationLayout?. {
//            binding?.registrationLayout?.root?.visibility = View.GONE
//            binding?.loginLayout?.root?.visibility = View.VISIBLE
//        }


        val loginMail = binding?.loginLayout?.emailedtlogin?.text.toString()
        val loginPassword = binding?.loginLayout?.passwordedtlogin?.text.toString()

        binding?.loginLayout?.loginbtn?.setOnClickListener {
            if (loginMail.isNotEmpty() && loginPassword.isNotEmpty()){
                val userLoginInfo = LoginUser(email = loginMail, password = loginPassword)

                CoroutineScope(Dispatchers.IO).launch {
                    dataViewModel.login(userLoginInfo)
                }
            } else{
                Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        dataViewModel.loginLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Error -> {
                    binding?.loginLayout?.responsetextlogin?.text = it.message
                }
                is NetworkResult.Loading -> {
                    binding?.loginLayout?.progressBarSignin?.visibility = View.VISIBLE
                }
                is NetworkResult.Success -> {
                    binding?.loginLayout?.progressBarSignin?.visibility = View.GONE
                    binding?.detProfileLayout?.root?.visibility = View.VISIBLE
                    lifecycleScope.launch {
                        dataStoreManager.saveToken(it.data!!.data.jwt)
                    }

                }
            }
        })






    }







}