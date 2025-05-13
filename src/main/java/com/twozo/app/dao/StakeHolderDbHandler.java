package com.twozo.app.dao;

import java.sql.Connection;
import org.springframework.stereotype.Repository;

@Repository
public interface StakeHolderDbHandler<T> {
   T checkExistence(String phoneNo);

   int store(T t, Connection connection);

   int store(T t);
}
