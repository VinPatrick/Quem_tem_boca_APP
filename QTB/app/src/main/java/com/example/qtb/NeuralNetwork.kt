package com.example.qtb

import java.util.Random
import kotlin.math.tanh


class NeuralNetwork(
    private val Num_entradas: Int,
    private val Num_ocultas: Int,
    private val Num_neuronios_ocultas: Int,
    private val Num_saidas: Int
) {
    private val Ocultas = ArrayList<ArrayList<Float>>()
    private val Saida = ArrayList<Float>()
    private val Pesos_ocultas = ArrayList<ArrayList<ArrayList<Float>>>()
    private val peso_entrada = ArrayList<ArrayList<Float>>()
    private val peso_saida = ArrayList<ArrayList<Float>>()
    private val bias = ArrayList<ArrayList<Float>>()
    private val bias_saida = ArrayList<Float>()
    private val random = Random()
    var alfa: Float = 0.01f

    init {
        // Inicialização das listas
        for (i in 0 until Num_ocultas) {
            val neuronios = ArrayList<Float>()
            for (j in 0 until Num_neuronios_ocultas) {
                neuronios.add(0.0f)
            }
            Ocultas.add(neuronios)
        }

        for (i in 0 until Num_neuronios_ocultas) {
            val pesos = ArrayList<Float>()
            for (j in 0 until Num_entradas) {
                pesos.add(random.nextFloat() * 2 - 1) // Pesos entre -1 e 1
            }
            peso_entrada.add(pesos)
        }

        for (i in 0 until Num_ocultas - 1) {
            val camada = ArrayList<ArrayList<Float>>()
            for (j in 0 until Num_neuronios_ocultas) {
                val pesos = ArrayList<Float>()
                for (k in 0 until Num_neuronios_ocultas) {
                    pesos.add(random.nextFloat() * 2 - 1)
                }
                camada.add(pesos)
            }
            Pesos_ocultas.add(camada)
        }

        for (i in 0 until Num_saidas) {
            val pesos = ArrayList<Float>()
            for (j in 0 until Num_neuronios_ocultas) {
                pesos.add(random.nextFloat() * 2 - 1)
            }
            peso_saida.add(pesos)
        }

        for (i in 0 until Num_ocultas) {
            val biases = ArrayList<Float>()
            for (j in 0 until Num_neuronios_ocultas) {
                biases.add(random.nextFloat() * 2 - 1)
            }
            bias.add(biases)
        }

        for (i in 0 until Num_saidas) {
            bias_saida.add(random.nextFloat() * 2 - 1)
        }
    }

    fun mostra_oculta() {
        println("Camadas ocultas:")
        for (camada in Ocultas) {
            println(camada)
        }
    }

    fun feedforward(entrada: ArrayList<Float>): ArrayList<Float> {
        // Entrada para primeira camada oculta
        for (i in 0 until Num_neuronios_ocultas) {
            var soma = bias[0][i]
            for (j in 0 until Num_entradas) {
                soma += entrada[j] * peso_entrada[i][j]
            }
            Ocultas[0][i] = funcao_ativacao(soma)
        }

        // Camadas ocultas intermediárias
        for (i in 1 until Num_ocultas) {
            for (j in 0 until Num_neuronios_ocultas) {
                var soma = bias[i][j]
                for (k in 0 until Num_neuronios_ocultas) {
                    soma += Ocultas[i - 1][k] * Pesos_ocultas[i - 1][k][j]
                }
                Ocultas[i][j] = funcao_ativacao(soma)
            }
        }

        // Última camada para saída
        val saida = ArrayList<Float>()
        for (i in 0 until Num_saidas) {
            var soma = bias_saida[i]
            for (j in 0 until Num_neuronios_ocultas) {
                soma += Ocultas[Num_ocultas - 1][j] * peso_saida[i][j]
            }
            saida.add(funcao_ativacao(soma))
        }

        return saida
    }

    private fun funcao_ativacao(x: Float): Float {
        return tanh(x.toDouble()).toFloat()
    }

    fun backpropagation(entrada: ArrayList<Float>, saida_d: ArrayList<Float>) {
        // Passo 1: Executar o feedforward para obter a saída da rede
        val saida_o = feedforward(entrada)

        // Passo 2: Inicializar listas para armazenar os deltas das camadas
        val deltaSaida = ArrayList<Float>() // Delta da camada de saída
        val deltaOcultas = ArrayList<ArrayList<Float>>() // Deltas das camadas ocultas

        // Passo 3: Calcular o delta da camada de saída
        for (i in 0 until Num_saidas) {
            val erro = saida_d[i] - saida_o[i]
            val derivada = 1 - (saida_o[i] * saida_o[i]) // Derivada de tanh(x)
            deltaSaida.add(erro * derivada)
        }

        // Passo 4: Calcular o delta das camadas ocultas (de trás para frente)
        for (k in Num_ocultas - 1 downTo 0) {
            val deltaAtual = ArrayList<Float>()
            for (i in 0 until Num_neuronios_ocultas) {
                var erroAcumulado = 0.0f

                if (k == Num_ocultas - 1) {
                    // Primeira camada oculta ligada à saída
                    for (j in 0 until Num_saidas) {
                        erroAcumulado += deltaSaida[j] * peso_saida[j][i]
                    }
                } else {
                    // Camadas ocultas intermediárias
                    for (j in 0 until Num_neuronios_ocultas) {
                        erroAcumulado += deltaOcultas[Num_ocultas - 2 - k][j] * Pesos_ocultas[k][i][j]
                    }
                }

                val derivada = 1 - (Ocultas[k][i] * Ocultas[k][i]) // Derivada de tanh(x)
                deltaAtual.add(erroAcumulado * derivada)
            }
            deltaOcultas.add(deltaAtual)
        }

        // Passo 5: Atualizar pesos e bias da camada de saída
        for (i in 0 until Num_saidas) {
            for (j in 0 until Num_neuronios_ocultas) {
                val novoPeso = peso_saida[i][j] + alfa * deltaSaida[i] * Ocultas[Num_ocultas - 1][j]
                peso_saida[i][j] = novoPeso
            }
            val novoBias = bias_saida[i] + alfa * deltaSaida[i]
            bias_saida[i] = novoBias
        }

        // Passo 6: Atualizar pesos e bias das camadas ocultas
        for (k in Num_ocultas - 1 downTo 0) {
            for (i in 0 until Num_neuronios_ocultas) {
                for (j in 0 until (if (k == 0) Num_entradas else Num_neuronios_ocultas)) {
                    val valorOculta = if ((k == 0)) entrada[j] else Ocultas[k - 1][j]
                    val novoPeso = ((if (k == 0) peso_entrada else Pesos_ocultas[k - 1])[i][j]
                            + alfa * deltaOcultas[Num_ocultas - 1 - k][i] * valorOculta)

                    if (k == 0) {
                        peso_entrada[i][j] = novoPeso
                    } else {
                        Pesos_ocultas[k - 1][i][j] = novoPeso
                    }
                }

                val novoBias = bias[k][i] + alfa * deltaOcultas[Num_ocultas - 1 - k][i]
                bias[k][i] = novoBias
            }
        }
    }
}
