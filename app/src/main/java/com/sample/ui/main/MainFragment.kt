package com.sample.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.sample.databinding.MainFragmentBinding
import com.sample.helper.AppMethods
import com.sample.helper.dummyData.GymData
import com.sample.ui.main.adapter.ActivityAdapter
import com.sample.ui.main.adapter.ClassesAdapter
import com.sample.ui.main.adapter.GymAdapter
import org.json.JSONObject


class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initView()
        setupObserver()
        viewModel.checkIfDataExist()
    }

    private fun setupObserver() {
        viewModel.fetchDataFromJson.observe(viewLifecycleOwner, Observer {
            if (it) {
                context?.let { it1 -> fetchDataFromJsonFile(it1) }
            }
        })
        viewModel.fetchGymDataFromLocal().observe(viewLifecycleOwner, {
            val gymAdapter: GymAdapter = binding.rvGym.adapter as GymAdapter
            gymAdapter.arrayList = it
            gymAdapter.notifyDataSetChanged()
        })
        viewModel.fetchClassDataFromLocal().observe(viewLifecycleOwner, {
            val classesAdapter: ClassesAdapter = binding.rvClasses.adapter as ClassesAdapter
            classesAdapter.arrayList = it
            classesAdapter.notifyDataSetChanged()
        })
        viewModel.fetchActivitiesDataFromLocal().observe(viewLifecycleOwner, {
            val activityAdapter: ActivityAdapter = binding.rvActivities.adapter as ActivityAdapter
            activityAdapter.arrayList = it
            activityAdapter.notifyDataSetChanged()
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {

        binding.rvGym.adapter = GymAdapter().apply {
            onItemClick = { id, updatedStatus ->
                viewModel.updateFavouriteStatus(id, updatedStatus)

            }
        }

        binding.rvClasses.adapter = ClassesAdapter().apply {
            onItemClick = { id, updatedStatus ->
                viewModel.updateFavouriteStatusForClass(id, updatedStatus)

            }
        }

        binding.rvActivities.adapter = ActivityAdapter().apply {
            onItemClick = { res, updatedStatus ->
                viewModel.updateSelectedStatusForActivity(res, updatedStatus)
            }
        }

    }

    private fun fetchDataFromJsonFile(context: Context) {
        //This code is used to read json file
        val obj = JSONObject(AppMethods.readJSONFromAsset(context))
        val gymDataResponse =
            Gson().fromJson(AppMethods.readJSONFromAsset(context), GymData::class.java)

        if (gymDataResponse.gyms != null) {
            viewModel.saveGymData(gymDataResponse.gyms)
        }
    }

}