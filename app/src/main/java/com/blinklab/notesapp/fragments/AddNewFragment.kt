package com.blinklab.notesapp.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.blinklab.notesapp.MainActivity
import com.blinklab.notesapp.R
import com.blinklab.notesapp.databinding.FragmentAddNewBinding
import com.blinklab.notesapp.databinding.PriortyDialogBinding

class AddNewFragment : Fragment() {
    private lateinit var binding: FragmentAddNewBinding


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         binding = FragmentAddNewBinding.inflate(layoutInflater)

        binding.backArrowNote.setOnClickListener {
            val intent = Intent (requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
    // ye error q aya select files?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            editor.setEditorHeight(300)
            editor.setFontSize(20)
            editor.setPlaceholder("Write something here...")
            editor.setPadding(16,10,16,16)
        }
        textFormatingOptions()

        binding.saveBtn.setOnClickListener {

            priorityDialog()

        }

    }

    private fun priorityDialog() {
        val dialog  = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        val binding = PriortyDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.8).toInt(), // 0.8 mean 80% width of screen ok
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(R.drawable.round_dialog_bg)
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        binding.important.setOnClickListener {
            showToast("Important")
            dialog.dismiss()
        }
        binding.lecture.setOnClickListener {
            showToast("Lecture Note")
            dialog.dismiss()
        }
        binding.shopping.setOnClickListener {
            showToast("Shopping")
            dialog.dismiss()
        }
        binding.grocery.setOnClickListener {
            showToast("Grocery")
            dialog.dismiss()
        }

// baqi kal sahi next ok mujhy koi important topic backend k bta dain jis ki khud practice kron

        dialog.show()
    }
// ab es dialog design ko thora better kro textsize etc
    private fun textFormatingOptions() {
        binding.apply {
            boldBtn.setOnClickListener { editor.setBold() }
            italicBtn.setOnClickListener { editor.setItalic() }
            underlineBtn.setOnClickListener { editor.setUnderline() }
            alignCenterBtn.setOnClickListener { editor.setAlignCenter() }
            alignLeftBtn.setOnClickListener { editor.setAlignLeft() }
            alignRightBtn.setOnClickListener { editor.setAlignRight() }
            textBtn.setOnClickListener { editor.setTextBackgroundColor(Color.YELLOW) }
            pointsBtn.setOnClickListener { editor.setBullets() }
        }
    }
    private fun showToast(msg:String){
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}