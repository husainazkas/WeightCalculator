package com.husainazkas.weightcalculator

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var option : Spinner
    lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alertView = LayoutInflater.from(this).inflate(R.layout.alert_intro_layout, null);
        val alert_intro = AlertDialog.Builder(this)

        alert_intro.setView(alertView)
        alert_intro.setTitle("PERHATIAN!")
        alert_intro.setCancelable(false)
        alert_intro.setPositiveButton("Mengerti") { dialogInterface: DialogInterface, i: Int -> }
        alert_intro.show()

        option = findViewById(R.id.sp_option) as Spinner
        result = findViewById(R.id.tv_opt) as TextView

        val options = arrayOf("Pria", "Wanita")
        val pilihan = "Pilih Jenis Kelamin Anda"

        option.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                result.text = pilihan.toString()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                result.text = options.get(position)
            }
        }

        /*Toast.makeText(this, welcome.toString(), Toast.LENGTH_LONG).show()*/

        tombol.setOnClickListener {
            val tinggi = editTinggi.text

            if (tinggi.isNullOrBlank()) {
                editTinggi.error = "Tinggi Badan harus diisi"
                editTinggi.requestFocus()
            } else if (tinggi.toString().toInt() < 125) {
                editTinggi.error = "Hasil tidak akan sesuai"
                editTinggi.requestFocus()
            } else if (tinggi.toString().toInt() > 300){
                editTinggi.error = "Tidak berlaku untuk di atas 3 meter"
                editTinggi.requestFocus()
            } else {
                hitungIdeal(
                    tinggi.toString().toInt(), def = 100
                )
            }
        }
    }


    fun hitungIdeal(tinggi: Int, def: Int) {
        val hasilsatu = tinggi - def
        val hasilduapria = hasilsatu * 10/100
        val hasilduawanita = hasilsatu * 15/100
        val hasilpria = hasilsatu - hasilduapria
        val hasilwanita = hasilsatu - hasilduawanita
        val kage = " KG"

        if (result.text == "Pria") {
            textHasil.text = hasilpria.toString() + kage
        } else {
            textHasil.text = hasilwanita.toString() + kage
        }
    }

    override fun onBackPressed() {
        val alert_exit = AlertDialog.Builder(this)
        alert_exit.setTitle("Keluar")
        alert_exit.setPositiveButton("Ya", { dialogInterface: DialogInterface, i: Int ->
            finish()
        })
        alert_exit.setNegativeButton("Tidak", { dialogInterface: DialogInterface, i: Int -> })
        alert_exit.show()
    }
}
