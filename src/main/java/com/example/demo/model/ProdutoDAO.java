package com.example.demo.model;

import com.example.demo.ConnetionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProdutoDAO {

    public List<Produto> getAll() throws SQLException {
        try (Statement statement = ConnetionSingleton.getConnection().createStatement();
             ResultSet rs = statement.executeQuery("select * from produto")) {

            List<Produto> produtos = new ArrayList<>();
            while (rs.next()){
                Produto produto = new Produto();
                produto.codigo = rs.getInt(1);
                produto.nome = rs.getString(2);
                produto.preco = rs.getDouble(3);
                produtos.add(produto);
            }
            return produtos;
        }
    }


    public void insert(Produto produtonovo) throws SQLException {

        String sql = "insert into produto (nome, preco) values (?, ?)";

        try (PreparedStatement preparedStatement = ConnetionSingleton.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            //preparedStatement.setInt(1,produtonovo.codigo);
            preparedStatement.setString(1,produtonovo.nome);
            preparedStatement.setDouble(2,produtonovo.preco);

            preparedStatement.execute();

            try (ResultSet rs = preparedStatement.getGeneratedKeys()){
                rs.next();
                produtonovo.codigo = rs.getInt(1);
            }
        }
    }

    public void delete (Produto apagaProduto) throws SQLException {
        String sql = "DELETE FROM produto WHERE codigo = ?";
        try(PreparedStatement preparedStatement = ConnetionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, apagaProduto.codigo);

            preparedStatement.execute();
        }
    }

    public void update(Produto atualizaProduto) throws SQLException {
        String sql = "update produto set nome = ?, preco = ? where codigo = ?";
        try (PreparedStatement preparedStatement = ConnetionSingleton.getConnection().prepareStatement(sql)){
            preparedStatement.setString(1,atualizaProduto.nome);
            preparedStatement.setDouble(2,atualizaProduto.preco);
            preparedStatement.setInt(3,atualizaProduto.codigo);

            preparedStatement.execute();
        }
    }
}
