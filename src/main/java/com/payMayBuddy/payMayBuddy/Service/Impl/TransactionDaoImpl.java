package com.payMayBuddy.payMayBuddy.Service.Impl;

import com.payMayBuddy.payMayBuddy.Service.Dao.TransactionDao;
import com.payMayBuddy.payMayBuddy.application.ConfigDB;
import com.payMayBuddy.payMayBuddy.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionDaoImpl implements TransactionDao {

    @Autowired
    private ConfigDB configDB;

    private Connection connection;

    @Override
    public List<Transaction> findAllTransactions() throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM transaction ORDER BY date DESC;");
        ResultSet rs = stmt.executeQuery();
        List<Transaction> transactions = new ArrayList<>();

        while(rs.next()){
            Transaction transaction = new Transaction();
            transaction.setId_transaction(rs.getInt("id_transaction"));
            transaction.setDescription(rs.getString("description"));
            transaction.setEmail_user(rs.getString("email"));
            transaction.setDate(rs.getDate("date"));
            transaction.setMontant(rs.getBigDecimal("montant"));
            transaction.setUser_id(rs.getInt("id_utilisateur"));
            transaction.setCompte_id(rs.getInt("id_compte"));

            transactions.add(transaction);

        }
        return transactions;
    }

    @Override
    public Transaction findById(int id) throws SQLException {
        connection= configDB.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM transaction WHERE id_transaction=?;");
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Transaction transactionId = new Transaction();
            transactionId.setId_transaction(rs.getInt("id_transaction"));
            transactionId.setDescription(rs.getString("description"));
            transactionId.setEmail_user(rs.getString("email"));
            transactionId.setDate(rs.getDate("date_transaction"));
            transactionId.setMontant(rs.getBigDecimal("montant"));
            transactionId.setUser_id(rs.getInt("id_utilisateur"));
            transactionId.setCompte_id(rs.getInt("id_compte"));

            return transactionId;
        }
        return null;
    }

    @Override
    public List<Transaction> findTransactionByType(String description) throws SQLException {
        connection= configDB.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM transaction WHERE description=?;");
        stmt.setString(1, description);
        List<Transaction>transactionList = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Transaction descriTransaction = new Transaction();
            descriTransaction.setId_transaction(rs.getInt("id_transaction"));
            descriTransaction.setDescription(rs.getString("description"));
            descriTransaction.setEmail_user(rs.getString("email"));
            descriTransaction.setDate(rs.getDate("date"));
            descriTransaction.setMontant(rs.getBigDecimal("montant"));
            descriTransaction.setUser_id(rs.getInt("id_utilisateur"));
            descriTransaction.setCompte_id(rs.getInt("id_compte"));
            transactionList.add(descriTransaction);
        }

        return transactionList;
    }

    @Override
    public List<Transaction> findTransactionByIdCompte(int id_compte) throws SQLException {
        connection= configDB.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM transaction WHERE id_compte=? ORDER BY date DESC;");
        stmt.setInt(1, id_compte);
        List<Transaction>transactions = new ArrayList<>();

        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Transaction transactionUser = new Transaction();
            transactionUser.setId_transaction(rs.getInt("id_transaction"));
            transactionUser.setDescription(rs.getString("description"));
            transactionUser.setEmail_user(rs.getString("email"));
            transactionUser.setDate(rs.getDate("date"));
            transactionUser.setMontant(rs.getBigDecimal("montant"));
            transactionUser.setUser_id(rs.getInt("id_utilisateur"));
            transactionUser.setCompte_id(rs.getInt("id_compte"));

            transactions.add(transactionUser);
        }

        return transactions;
    }


    @Override
    public Transaction createdTransaction(Transaction transaction) throws SQLException {
        connection= configDB.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO transaction (description,email,date,montant,id_utilisateur,id_compte) VALUES (?,?,?,?,?,?);",Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1,transaction.getDescription());
        stmt.setString(2,transaction.getEmail_user());
        stmt.setDate(3,transaction.getDate());
        stmt.setBigDecimal(4,transaction.getMontant());
        stmt.setInt(5,transaction.getUser_id());
        stmt.setInt(6,transaction.getCompte_id());

        stmt.executeUpdate();
        ResultSet results = stmt.getGeneratedKeys();
        if (results.next()){
            transaction.setId_transaction(results.getInt(1));
        }
        return transaction;
    }

    @Override
    public Transaction getEmailForTransaction(String email) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO transaction(email,id_utilisateur) ; ");
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            Transaction emailTransaction = new Transaction();
            emailTransaction.setEmail_user(rs.getString("email"));
            return emailTransaction;
        }

        return null;
    }

    @Override
    public void deleteTransaction(int id_compte) throws SQLException {
        connection= configDB.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE transaction WHERE id_transaction=?");
        stmt.setInt(1,id_compte);
        stmt.executeUpdate();
        stmt.close();

    }


}
