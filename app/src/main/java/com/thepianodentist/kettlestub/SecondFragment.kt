package com.thepianodentist.kettlestub

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.thepianodentist.kettlestub.api.Retriever
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private val retriever = Retriever()
    private val viewModel: TokenViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_boil).setOnClickListener {
            val errorHandler = CoroutineExceptionHandler{_, exception ->
                Log.e("2nd frag", "fudge", exception)
            }
            CoroutineScope(Job() + Dispatchers.IO).launch(errorHandler){
                retriever.postConfirm()
            }
            findNavController().navigate(R.id.action_SecondFragment_to_WaitingFragment)
        }
    }
}