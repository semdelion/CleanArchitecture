package loymax.ktx.petapplication.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import loymax.ktx.petapplication.R
import loymax.ktx.petapplication.data.repositories.UserRepository
import loymax.ktx.petapplication.domain.models.User
import loymax.ktx.petapplication.domain.repositories.IUserRepository

class MainViewModelFactory(private val repository: IUserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = UserRepository(requireContext().applicationContext)
        viewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val dataTextView = view.findViewById<TextView>(R.id.get_data_textview);
        val firstNameTextLayout = view.findViewById<TextInputLayout>(R.id.first_name_textInputLayout);
        val firstNameEditText = view.findViewById<TextInputEditText>(R.id.first_name_editText);
        firstNameTextLayout.hint = "First Name"
        val lastNameTextLayout = view.findViewById<TextInputLayout>(R.id.last_name_textInputLayout);
        val lastNameEditText = view.findViewById<TextInputEditText>(R.id.last_name_editText);
        lastNameTextLayout.hint = "Last Name"
        val sendButton = view.findViewById<Button>(R.id.save_data_button);
        val receiveButton = view.findViewById<Button>(R.id.get_data_button);

        sendButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val result = viewModel.saveUser.execute(User(firstName, lastName))
            if (result)
                Toast.makeText(context, "Success save", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(context, "Error last name or first name is empty", Toast.LENGTH_SHORT).show()
        }

        receiveButton.setOnClickListener {
            val user = viewModel.getUser.execute()
            dataTextView.text = "${user.firstName} ${user.lastName}"
        }

        return view;
    }
}