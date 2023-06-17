package com.lrm.marsalbum.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lrm.marsalbum.adapter.PhotoAdapter
import com.lrm.marsalbum.databinding.FragmentPhotosBinding
import com.lrm.marsalbum.model.PhotosViewModel

class PhotosFragment : Fragment() {

    private val viewModel: PhotosViewModel by viewModels()

    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotosBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.refresh.setOnClickListener {
            viewModel.getMarsPhotos()
        }

        binding.rvPhotos.adapter = PhotoAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}