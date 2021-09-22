package com.josejunior.calculadoradeipva

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter

import com.josejunior.calculadoradeipva.databinding.ActivityMainBinding
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val estado = arrayListOf("Acre","Alagoas","Amapá","Amazonas","Bahia","Ceará","Espírito Santo","Goiás","Maranhão",
            "Mato Grosso","Mato Grosso do Sul","Minas Gerais","Pará","Paraíba","Paraná","Pernambuco","Piauí",
            "Rio de Janeiro","Rio Grande do Norte","Rio Grande do Sul","Rondônia","Roraima","Santa Catarina","São Paulo",
            "Sergipe","Tocantins","Distrito Federal")
        val dec = DecimalFormat("#,###.00")

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, estado)
        binding.spnEstado.adapter = spinnerAdapter

        fun porcentagem(perc:Double, valorVeic:Int, estado:String){
            val valorIPVA = (valorVeic * perc) / 100
            binding.txvValorIPVA.text = "IPVA: R$ ${dec.format(valorIPVA)}"
            binding.txvBaseCalc.text = "Alíquota do $estado é $perc%"
        }

        binding.btnCalcular.setOnClickListener {
            var valorVeiculo = (binding.edtValorVeiculo.text.toString().trim()).toInt()
            var estadoSel = binding.spnEstado.selectedItem.toString()

            // 2% (Acre || Espírito Santo || Paraíba || Santa Catarina || Sergipe || Tocantins
            if(estadoSel == estado[0] || estadoSel == estado[6] || estadoSel == estado[13] || estadoSel == estado[22] ||
                estadoSel == estado[24] || estadoSel == estado[25]){

                porcentagem(2.0, valorVeiculo, estadoSel)
            }
            // 2.5% (Alagoas || Bahia || Ceará || Goiás || Maranhão || Mato Grosso do Sul || Pará || Pernambuco || Piauí || Rio Grande do Norte)
            else if(estadoSel == estado[1] || estadoSel == estado[4] || estadoSel == estado[5] || estadoSel == estado[7] ||
                estadoSel == estado[8] || estadoSel == estado[10] || estadoSel == estado[12] || estadoSel == estado[15] ||
                estadoSel == estado[16] || estadoSel == estado[18]){

                porcentagem(2.5, valorVeiculo, estadoSel)

            }
            // 3% (Amapá || Amazonas || Mato Grosso || Rio Grande do Sul || Rondônia || Roraima || São Paulo || Distrito Federal)
            else if(estadoSel == estado[2] || estadoSel == estado[3] || estadoSel == estado[9] || estadoSel == estado[19] ||
                estadoSel == estado[20] || estadoSel == estado[21] || estadoSel == estado[23] || estadoSel == estado[26]){

                porcentagem(3.0, valorVeiculo, estadoSel)
            }
            // 3.5% (Paraná)
            else if(estadoSel == estado[14]){

                porcentagem(3.5, valorVeiculo, estadoSel)

            }// 4% (Minas Gerais || Rio de Janeiro )
            else if(estadoSel == estado[11] || estadoSel == estado[17]){
                porcentagem(4.0, valorVeiculo, estadoSel)
            }


        }
    }
}