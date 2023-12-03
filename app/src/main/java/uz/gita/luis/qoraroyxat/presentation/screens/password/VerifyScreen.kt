package uz.gita.luis.qoraroyxat.presentation.screens.password

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.luis.qoraroyxat.R
import uz.gita.luis.qoraroyxat.databinding.ScreenCodeVerifyBinding
import uz.gita.luis.qoraroyxat.domain.repository.SharedPref

class VerifyScreen:Fragment(R.layout.screen_code_verify) {
    val sharedPref = SharedPref.getInstance()
    val binding by viewBinding(ScreenCodeVerifyBinding::bind)
    var stringBuffer = StringBuffer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!sharedPref.hasPassword){
            findNavController().navigate(R.id.action_verifyScreen_to_main)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.quit.setOnClickListener {
        activity?.finish()
        }

        binding.include.btConfirm.setOnClickListener {
            if (sharedPref.password == binding.editPassword.text.toString())
                findNavController().navigate(R.id.action_verifyScreen_to_main)
            else
                Toast.makeText(requireContext(), "Incorrect password", Toast.LENGTH_SHORT).show()
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

}