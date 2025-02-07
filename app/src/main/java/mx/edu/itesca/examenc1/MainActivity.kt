package mx.edu.itesca.examenc1

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val costo = findViewById<TextView>(R.id.tvCosto)
        val years = findViewById<EditText>(R.id.etYears)
        val btnPagar = findViewById<Button>(R.id.btnPagar)


        val polizas = resources.getStringArray(R.array.polizas)
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, polizas
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.setSelection(0)

        var costoSeleccionado = 0.0

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val polizaSeleccionada = parent.getItemAtPosition(position) as String
                when (polizaSeleccionada) {
                    "Autos Sedán" -> {
                        costo.text = "500.00"
                        costoSeleccionado = 500.00
                    }
                    "Camionetas" -> {
                        costo.text = "700.00"
                        costoSeleccionado = 700.00
                    }
                    "Autos Deportivos" -> {
                        costo.text = "1,200.00"
                        costoSeleccionado = 1200.00
                    }
                    "Tipo Póliza" -> {
                        costo.text = ""
                        years.text.clear()
                        costoSeleccionado = 0.0
                        btnPagar.text = "Pagar $"
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        btnPagar.setOnClickListener {
            val aniosText = years.text.toString()

            if (aniosText.isNotEmpty() && costoSeleccionado > 0) {
                try {
                    val anios = aniosText.toDouble()
                    val total = anios * costoSeleccionado
                    btnPagar.text = "Pagar $${"%.2f".format(total)}"
                } catch (e: NumberFormatException) {
                    btnPagar.text = "Pagar $"
                }
            } else {
                btnPagar.text = "ERROR: Añadir años"
            }
        }
    }
}