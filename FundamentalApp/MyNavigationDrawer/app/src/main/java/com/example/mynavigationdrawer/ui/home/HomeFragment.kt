package com.example.mynavigationdrawer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mynavigationdrawer.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

  private lateinit var homeViewModel: HomeViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    homeViewModel =
    ViewModelProviders.of(this).get(HomeViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_home, container, false)
    val textView: TextView = root.findViewById(R.id.text_home)
    homeViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })

    return root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val pokeCount = 3
    val hello = resources.getString(R.string.hello_world, "Adam", pokeCount, "Fajar")
    tv_hello.text = hello
    val songCount = 5
    /** teks yang ditampilkan akan sesuai dengan jumlah yang diberikan. Penentuan ini menggunakan metode [getQuantityString] **/
    val pluralText = resources.getQuantityString(R.plurals.numberOfSongsAvailable, songCount, songCount)
    tv_plural.text = pluralText
    tv_xliff.text = resources.getString(R.string.app_homeurl)
  }
}