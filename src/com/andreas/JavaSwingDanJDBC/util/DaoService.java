package com.andreas.JavaSwingDanJDBC.util;

import java.sql.SQLException;
import java.util.List;
import com.andreas.JavaSwingDanJDBC.entity.Student;


public interface DaoService <T>{
    List<T> fetchAll() throws SQLException, ClassNotFoundException;
    int addData(T t)throws SQLException, ClassNotFoundException;

    int updateData() throws SQLException, ClassNotFoundException;

    int updateData(T t ) throws  SQLException, ClassNotFoundException;


    int deleteData(Student student) throws SQLException, ClassNotFoundException;
}
