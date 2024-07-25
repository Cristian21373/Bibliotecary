package com.example.crudlibrary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.crudlibrary.config.config
import com.example.crudlibrary.models.libro
import com.google.gson.Gson

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class detalleLibro : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var lbltitulo: TextView
    private lateinit var lblautor: TextView
    private lateinit var lblgenero: TextView
    private lateinit var lblcod_isbn: TextView
    private lateinit var lblcant_dis: TextView
    private lateinit var lblcant_ocup: TextView

    private lateinit var btnEditar: Button
    private lateinit var btnEliminar: Button

    private var id: String = "14f4eef8-bd20-4cf4-a103-22599aba835f"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun consultarLibro() {
        if (id.isNotEmpty()) {
            val request = JsonObjectRequest(
                Request.Method.GET,
                config.urlLibro + id,
                null,
                { response ->
                    val gson = Gson()
                    val libro: libro = gson.fromJson(response.toString(), libro::class.java)
                    lbltitulo.text = libro.titulo
                    lblautor.text = libro.autor
                    lblgenero.text = libro.genero
                    lblcod_isbn.text = libro.isbn
                    lblcant_dis.text = libro.cant_Dis.toString()
                    lblcant_ocup.text = libro.cant_Ocup.toString()
                },
                { error ->
                    Toast.makeText(context, "Error al consultar: ${error.message}", Toast.LENGTH_LONG).show()
                    Log.e("Volley", "Error: ${error.message}")
                }
            )
            Volley.newRequestQueue(context).add(request)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detalle_libro, container, false)
        lbltitulo = view.findViewById(R.id.lbltitulo)
        lblautor = view.findViewById(R.id.lblautor)
        lblgenero = view.findViewById(R.id.lblgenero)
        lblcod_isbn = view.findViewById(R.id.lblcod_isbn)
        lblcant_dis = view.findViewById(R.id.lblcant_dis)
        lblcant_ocup = view.findViewById(R.id.lblcant_ocup)

        btnEditar = view.findViewById(R.id.btnEditar)
        btnEditar.setOnClickListener { editarLibro() }
        btnEliminar = view.findViewById(R.id.btnEliminar)
        btnEliminar.setOnClickListener { eliminarLibro() }

        consultarLibro() // Llamar a consultarLibro() aquí para cargar los datos al iniciar la vista

        return view
    }

    fun editarLibro() {
        // Implementar funcionalidad de edición
    }

    fun eliminarLibro() {
        // Implementar funcionalidad de eliminación
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            detalleLibro().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
