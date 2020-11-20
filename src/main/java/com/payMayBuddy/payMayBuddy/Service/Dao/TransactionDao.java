package com.payMayBuddy.payMayBuddy.Service.Dao;

import com.payMayBuddy.payMayBuddy.model.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface TransactionDao {

    List<Transaction> findAllTransactions() throws SQLException;

    Transaction findById(int id) throws SQLException;

    List<Transaction> findTransactionByType(String description) throws SQLException;

    List<Transaction> findTransactionByIdCompte(int id_compte) throws SQLException;

    Transaction createdTransaction(Transaction transaction) throws SQLException;

    Transaction getEmailForTransaction(String email)throws SQLException;

    void deleteTransaction(int id_compte) throws SQLException;

}
