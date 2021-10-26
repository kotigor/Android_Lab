package ru.konstantinov.lab4.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.konstantinov.lab4.R
import ru.konstantinov.lab4.databinding.FragmentProfileBinding
import ru.konstantinov.lab4.ui.settings.SettingsFragment
import ru.konstantinov.lab4.ui.about.AboutFragment

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val profileVM: ProfileVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileShareText.setOnClickListener {
            shareApp()
        }
        binding.profileSettingsText.setOnClickListener{
            profileVM.changeMainFragment(SettingsFragment())
        }
        binding.profileAboutText.setOnClickListener{
            val aboutFragment = AboutFragment()
            val bundle = Bundle()
            bundle.putString("EXTRA_URL", "https://www.journaldev.com")
            aboutFragment.arguments = bundle
            profileVM.changeMainFragment(aboutFragment)
        }
    }

    private fun shareApp() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, getString(R.string.shareDescription))
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}