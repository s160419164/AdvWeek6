package com.ubaya.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.advweek4.R
import com.ubaya.advweek4.util.loadImage
import com.ubaya.advweek4.viewmodel.DetailViewModel
import com.ubaya.advweek4.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_detail.*

class StudentDetailFragment : Fragment() {
    private lateinit var detailModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        if(arguments != null){
            var id=StudentDetailFragmentArgs.fromBundle(requireArguments()).idStudent
            detailModel.setIdStudent(id)
        }
        detailModel.fetch()

        ObserveDetailModel()
    }

    fun ObserveDetailModel(){
        detailModel.studentLD.observe(viewLifecycleOwner, Observer {
            txtID.setText(it.id)
            txtNama.setText(it.name)
            txtBod.setText(it.dob)
            txtPhone.setText(it.phone)
            imageView2.loadImage(it.photoUrl.toString(),progressBar2)
        })
    }
}