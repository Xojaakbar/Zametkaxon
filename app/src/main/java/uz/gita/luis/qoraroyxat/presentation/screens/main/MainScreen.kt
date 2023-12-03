package uz.gita.luis.qoraroyxat.presentation.screens.main

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.luis.qoraroyxat.R
import uz.gita.luis.qoraroyxat.data.source.local.entity.NoteData
import uz.gita.luis.qoraroyxat.databinding.ScreenMainBinding
import uz.gita.luis.qoraroyxat.presentation.adapter.HomeAdapter
import uz.gita.luis.qoraroyxat.presentation.screens.bottomSheet.BottomSheetDialog
import uz.gita.luis.qoraroyxat.presentation.screens.main.viewModel.MainViewModel

class MainScreen:Fragment(R.layout.screen_main) {
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val adapter = HomeAdapter()
    private val binding by viewBinding(ScreenMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.buttonAddLiveData.observe(this,openAddLiveDataObserver)
        viewModel.openUpdateLiveData.observe(this,openUpdateLiveDataObserver)
        viewModel.buttonTrashLiveData.observe(this,openTrashObserver)
    }

    override fun onResume() {
        super.onResume()
        if (binding.search.text.toString().isEmpty()){
        viewModel.getAllNotes()
        }else{
            viewModel.search(binding.search.text.trim().toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getAllNotes()
        viewModel.getAllLiveData.observe(viewLifecycleOwner,getAllObserver)

        viewModel.searchLiveData.observe(viewLifecycleOwner,searchLiveDataObserver)

        adapter.setUpdateListener {
            viewModel.openUpdate(it)
        }
        adapter.setBottomSheetListener {
            openBottomSheet(it)
        }

        binding.recycler.adapter = adapter
        binding.search.doAfterTextChanged {
            if (binding.search.text.isNotBlank()){
                viewModel.search(binding.search.text.trim().toString())
            }
            else{
                viewModel.getAllNotes()
            }
        }

        view.findViewById<ImageView>(R.id.btn_add).setOnClickListener {
            findNavController().navigate(R.id.action_main_to_addScreen)
        }

        binding.trashes.setOnClickListener { viewModel.openTrash() }
        binding.buttonAddPassword.setOnClickListener {
            findNavController().navigate(R.id.action_main_to_passwordScreen)
        }
    }

    val getAllObserver = Observer<List<NoteData>>{
        if(!it.isEmpty()){
            binding.recycler.visibility = View.VISIBLE
            adapter.submitList(it)
            binding.notFount.visibility = View.GONE}
        else{
            binding.recycler.visibility = View.GONE
            binding.notFount.visibility = View.VISIBLE
        }
    }

    val openAddLiveDataObserver = Observer<Unit>{
        findNavController().navigate(R.id.action_main_to_addScreen)
    }
    val searchLiveDataObserver = Observer<List<NoteData>> {
        adapter.submitList(it)
    }

    val openTrashObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_main_to_trashScreen)
    }

    val openUpdateLiveDataObserver = Observer<NoteData>{
        findNavController().navigate(MainScreenDirections.actionMainToUpdateScreen(it))
    }

    fun openBottomSheet(noteData: NoteData){
        val bottomSheet = BottomSheetDialog()
        bottomSheet.selectPinBlockListener {
        if (noteData.pin == 1){
                viewModel.update(NoteData(noteData.id,noteData.title,noteData.content,noteData.date,0,noteData.bg,noteData.trash))
                bottomSheet.onHiddenChanged(true)
        }
            else{
                viewModel.update(NoteData(noteData.id,noteData.title,noteData.content,noteData.date,1,noteData.bg,noteData.trash))
            bottomSheet.onHiddenChanged(true)}
        }
        bottomSheet.removeBlockListener {
            noteData.trash = 1
            viewModel.update(noteData)
            bottomSheet.onHiddenChanged(true)
        }
        bottomSheet.show(parentFragmentManager,"")
    }

}