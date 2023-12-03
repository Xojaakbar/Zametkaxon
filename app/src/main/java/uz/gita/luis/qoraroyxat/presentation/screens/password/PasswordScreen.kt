package uz.gita.luis.qoraroyxat.presentation.screens.password

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.luis.qoraroyxat.R
import uz.gita.luis.qoraroyxat.databinding.ScreenPasswordBinding
import uz.gita.luis.qoraroyxat.domain.repository.SharedPref

class PasswordScreen:Fragment(R.layout.screen_password) {
    val sharedPref = SharedPref.getInstance()
    val binding by viewBinding(ScreenPasswordBinding::bind)
    var stringBuffer = StringBuffer()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.passwordSwitch.isChecked = sharedPref.hasPassword


        binding.passwordSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                    sharedPref.hasPassword = true

                    binding.include.btConfirm.setOnClickListener {
                        if (binding.editPassword.text.toString().isNotBlank()){
                            sharedPref.password = binding.editPassword.text.toString()
                            findNavController().navigateUp()}
                    }
                    binding.include.btOne.setOnClickListener {
                        stringBuffer.append("1")
                        binding.editPassword.setText(stringBuffer)
                    }

                    binding.include.btTwo.setOnClickListener {
                        stringBuffer.append("2")
                        binding.editPassword.setText(stringBuffer)
                    }
                    binding.include.btThree.setOnClickListener {
                        stringBuffer.append("3")
                        binding.editPassword.setText(stringBuffer)
                    }
                    binding.include.btFour.setOnClickListener {
                        stringBuffer.append("4")
                        binding.editPassword.setText(stringBuffer)
                    }
                    binding.include.btFive.setOnClickListener {
                        stringBuffer.append("5")
                        binding.editPassword.setText(stringBuffer)
                    }
                    binding.include.btSix.setOnClickListener {
                        stringBuffer.append("6")
                        binding.editPassword.setText(stringBuffer)
                    }
                    binding.include.btSeven.setOnClickListener {
                        stringBuffer.append("7")
                        binding.editPassword.setText(stringBuffer)
                    }
                    binding.include.btEight.setOnClickListener {
                        stringBuffer.append("8")
                        binding.editPassword.setText(stringBuffer)
                    }
                    binding.include.btNine.setOnClickListener {
                        stringBuffer.append("9")
                        binding.editPassword.setText(stringBuffer)
                    }
                    binding.include.btZero.setOnClickListener {
                        stringBuffer.append("0")
                        binding.editPassword.setText(stringBuffer)
                    }
                    binding.include.btClear.setOnClickListener {
                        if (stringBuffer.length != 0) {
                            stringBuffer.deleteCharAt(stringBuffer.lastIndex)
                            binding.editPassword.setText(stringBuffer)
                        }
                    }
                }
            else{
                sharedPref.hasPassword = false
                binding.include.btConfirm.isClickable = false
            }
        }
    }
}