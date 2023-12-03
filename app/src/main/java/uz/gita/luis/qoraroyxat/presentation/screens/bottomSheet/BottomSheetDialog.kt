package uz.gita.luis.qoraroyxat.presentation.screens.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.gita.luis.qoraroyxat.R
import uz.gita.luis.qoraroyxat.databinding.BottomSheetBinding

class BottomSheetDialog: BottomSheetDialogFragment() {
    val binding by viewBinding(BottomSheetBinding::bind)
    private lateinit var selectPinListener: () -> Unit
    fun selectPinBlockListener(block: () -> Unit) {
        selectPinListener = block
    }

    private lateinit var changeColorListener: () -> Unit
    fun changeColorBlockListener(block: () -> Unit) {
        changeColorListener = block
    }

    private lateinit var removeListener: () -> Unit
    fun removeBlockListener(block: () -> Unit) {
        removeListener = block
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            selectOnOff.setOnClickListener {
                selectPinListener.invoke()
                dismiss()
            }
            deleteInSheet.setOnClickListener {
                removeListener.invoke()
                dismiss()
            }
        }
    }
}