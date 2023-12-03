package uz.gita.luis.qoraroyxat.presentation.screens.trash

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.luis.qoraroyxat.R
import uz.gita.luis.qoraroyxat.data.source.local.entity.NoteData
import uz.gita.luis.qoraroyxat.databinding.SreenTrashBinding
import uz.gita.luis.qoraroyxat.presentation.adapter.TrashAdapter
import uz.gita.luis.qoraroyxat.presentation.screens.trash.TrashViewModel.TrashViewModel

class TrashScreen:Fragment(R.layout.sreen_trash) {
    val binding by viewBinding(SreenTrashBinding::bind)
    val viewModel by lazy {  ViewModelProvider(this)[TrashViewModel::class.java] }
    val adapter by lazy { TrashAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.backToMainLiveData.observe(this,backToMainLiveDataObserver)
        viewModel.trashesLiveData.observe(this,trashesObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getAllTrashes()
        binding.backToMain.setOnClickListener { viewModel.backToMain() }
        binding.recycler.adapter = adapter

        adapter.setDelete {
            viewModel.deleteFromTrash(it)
            Toast.makeText(requireContext(), "restored successfully!", Toast.LENGTH_SHORT).show()
        }

        binding.deleteAll.setOnClickListener {
            viewModel.deleteAll()
            Toast.makeText(requireContext(), "cleared successfully", Toast.LENGTH_SHORT).show()
        }
    }

    val backToMainLiveDataObserver = Observer<Unit>{
        findNavController().popBackStack()
    }

    val trashesObserver = Observer<List<NoteData>> {
        adapter.submitList(it)
    }
}
