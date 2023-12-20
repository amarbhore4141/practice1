package com.storedprocexample.storedproceduresexample.repository;

import com.storedprocexample.storedproceduresexample.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    @Modifying
    @Query(value = "CALL transfer(:fromAccountId, :toAccountId, :transferAmount)", nativeQuery = true)
    void transferAmountStoredProc(int fromAccountId, int toAccountId, double transferAmount);
}
