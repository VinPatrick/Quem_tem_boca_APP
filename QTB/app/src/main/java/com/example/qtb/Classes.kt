package com.example.qtb

class Usuario (
    var CPF: String,
    var Nome: String,
    var Senha: String,
    var Email: String,
    var Bairro: String,
    var Cidade: String,
    var Endereco:String,
    var Numero: Int,
    var Telefone: String
){
}
class Restaurante(
    var Nome_R: String,
    var Dono: String,
    var Tipo: String,
    var Id_Produtos: Int,
    var Id_Pedidos: Int
){
}
class Produto (
    var Id: Int,
    var Nome: String,
    var Tipo: String,
    var Valor: Float
){
}
class Pedido(
    var id : Int,
    var Id_Produtos: Int,
    var Comprador: String
){
}