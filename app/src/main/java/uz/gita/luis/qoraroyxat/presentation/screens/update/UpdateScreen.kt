package uz.gita.luis.qoraroyxat.presentation.screens.update

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import jp.wasabeef.richeditor.RichEditor
import uz.gita.luis.qoraroyxat.R
import uz.gita.luis.qoraroyxat.data.source.local.entity.NoteData
import uz.gita.luis.qoraroyxat.databinding.ScreenUpdateBinding
import uz.gita.luis.qoraroyxat.presentation.screens.update.viewmodel.UpdateViewModel

class UpdateScreen:Fragment(R.layout.screen_update) {
    private val binding by viewBinding(ScreenUpdateBinding::bind)
    private val viewModel by lazy {  ViewModelProvider(this)[UpdateViewModel::class.java] }
    private val args by navArgs<UpdateScreenArgs>()

    private lateinit var editor: RichEditor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.richLine.isVisible = false

        val note = args.notedata
        binding.apply {
            binding.backToMain.setOnClickListener {  findNavController().navigateUp() }
           kimEdi.setText(note.title)
            nimaQilgan.html = note.content
//            binding.save.animate().alpha(1F).start()
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
                viewModel.update(
                    NoteData(note.id,
                        kimEdi.text.toString(),
                        nimaQilgan.html.toString(),
                        note.date,
                        note.pin,
                        note.bg,
                        note.trash)
                )
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
