package uz.gita.luis.qoraroyxat.presentation.screens.add

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import jp.wasabeef.richeditor.RichEditor
import uz.gita.luis.qoraroyxat.R
import uz.gita.luis.qoraroyxat.data.source.local.converter.DataConverter
import uz.gita.luis.qoraroyxat.data.source.local.entity.NoteData
import uz.gita.luis.qoraroyxat.databinding.ScreenAddBinding
import uz.gita.luis.qoraroyxat.presentation.screens.add.viewmodel.AddViewModel

class AddScreen : Fragment(R.layout.screen_add) {
    private val binding by viewBinding(ScreenAddBinding::bind)
    private val viewModel: AddViewModel by lazy {
        ViewModelProvider(this)[AddViewModel::class.java]
    }

    private lateinit var editor: RichEditor



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.richLine.isVisible = false
        binding.apply {

            backToMain.setOnClickListener {
                findNavController().navigateUp()
            }

            binding.kimEdi.setText(arguments?.getString("title")).toString().trim()
            binding.nimaQilgan.html = arguments?.getString("content").toString().trim()
            if (binding.nimaQilgan.html == "null") {
                binding.nimaQilgan.html = ""
            }
            editor = binding.nimaQilgan
            editor.setPlaceholder("Content...")
            editor.setInputEnabled(true)
            editor.setTextColor(Color.WHITE)
            editor.setPadding(25, 20, 25, 15)
            editor.setEditorFontColor(R.color.tema)
            editor.focusEditor()
            editor.setEditorFontSize(20)

            binding.nimaQilgan.setOnFocusChangeListener { v, hasFocus ->
                binding.richLine.isVisible = hasFocus
            }

            nimaQilgan.setOnTextChangeListener {
                if (it.isNotBlank() && kimEdi.text.isNotBlank()) {
                    save.isClickable = true
                    save.animate().alpha(1F).start()
                } else {
                    save.isClickable = false
                    save.animate().alpha(0.1F).start()
                }
            }
            kimEdi.doAfterTextChanged {
                if (nimaQilgan.html.toString().isNotBlank() && kimEdi.text.isNotBlank()) {
                    save.isClickable = true
                    save.animate().alpha(1F).start()
                }  else {
                    save.isClickable = false
                    save.animate().alpha(0.1F).start()
                }
            }
            save.setOnClickListener {
                viewModel.addNote(NoteData(
                    id = 0,
                    kimEdi.text.toString(),
                    nimaQilgan.html.toString().trim(),
                    DataConverter.getCurrentTime(),0,R.color.default_bg_item,0))
                findNavController().navigateUp()
            }

            actionBold.setOnClickListener {
                editor.setBold()
            }
            actionItalic.setOnClickListener {
                editor.setItalic()
            }
            actionTxtColor.setOnClickListener {
                MaterialColorPickerDialog
                    .Builder(requireContext())                            // Pass Activity Instance
                    .setTitle("Choose color")                // Default "Choose Color"
                    .setColorShape(ColorShape.SQAURE)    // Default ColorShape.CIRCLE
                    .setColorSwatch(ColorSwatch._300)    // Default ColorSwatch._500
                    .setDefaultColor("#FF000000")

                    .setColorListener { color, colorHex ->
                        Log.d("COLOR", "$color")
                        editor.setTextColor(color)
                    }
                    .show()
            }

            actionBgColor.setOnClickListener {

                MaterialColorPickerDialog
                    .Builder(requireContext())                            // Pass Activity Instance
                    .setTitle("Choose color")                // Default "Choose Color"
                    .setColorShape(ColorShape.SQAURE)    // Default ColorShape.CIRCLE
                    .setColorSwatch(ColorSwatch._300)    // Default ColorSwatch._500
                    .setDefaultColor("#FEFFFE")        // Pass Default Color
                    .setColorListener { color, colorHex ->
                        editor.setTextBackgroundColor(color)
                        Log.d("COLOR", "$color")
                    }
                    .setDismissListener {

                    }
                    .show()
            }

            actionUnderline.setOnClickListener {
                editor.setUnderline()
            }

            actionStrikethrough.setOnClickListener {
                editor.setStrikeThrough()
            }

            actionUndo.setOnClickListener {
                editor.undo()
            }

            actionRedo.setOnClickListener {
                editor.redo()
            }
        }

        binding.save.isClickable = false
        binding.save.animate().alpha(0.1F).start()
    }
}