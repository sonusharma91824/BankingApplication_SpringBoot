package com.pratice.Banking.repository;

import com.pratice.Banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  AccountRepository extends JpaRepository<Account,Long> {
}
