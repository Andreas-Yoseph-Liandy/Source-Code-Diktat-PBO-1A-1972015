package com.andreas.JavaSwingDanJDBC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.andreas.JavaSwingDanJDBC.entity.Departement;
import com.andreas.JavaSwingDanJDBC.entity.Student;
import com.andreas.JavaSwingDanJDBC.util.DaoService;
import com.andreas.JavaSwingDanJDBC.util.MySQLConnection;

public class StudentDaoImpl implements DaoService<Student> {
        @Override
        public List<Student> fetchAll() throws SQLException, ClassNotFoundException {
            List<Student> students = new ArrayList<>();
            String query = " SELECT s.id, first_name, last_name, address, departement_id, name FROM "+
                    " student s JOIN departement d ON s.departement_id = d.id ";
            try (Connection connection = MySQLConnection.createConnection()) {
                try (PreparedStatement ps = connection.prepareStatement(query)) {
                    try (ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            Departement departement = new Departement();
                            departement.setId(rs.getInt("departement_id"));
                            departement.setName(rs.getString("name"));
                            Student student = new Student();
                            student.setId(rs.getString("id"));
                            student.setFirst_name(rs.getString("first_name"));
                            student.setLast_name(rs.getString("last_name"));
                            student.setAddress(rs.getString("address"));
                            student.setDepartement(departement);
                            students.add(student);
                        }
                    }
                }
            }
            return students;
        }

        @Override
        public int addData(Student student) throws SQLException, ClassNotFoundException {
            int result = 0;
            String query = "INSERT INTO student(id, first_name, last_name, address, departement_id) " +
                    "VALUES(?, ?, ?, ?, ?)";
            try(Connection connection = MySQLConnection.createConnection()){
                try (PreparedStatement ps = connection.prepareStatement(query)) {
                    ps.setString(1, student.getId());
                    ps.setString(2, student.getFirst_name());
                    ps.setString(3, student.getLast_name());
                    ps.setString(4, student.getAddress());
                    ps.setInt(5, student.getDepartement().getId());
                    if(ps.executeUpdate() != 0){
                        connection.commit();
                        result=1;
                    }else{
                        connection.rollback();
                    }
                }
            }
            return result;
        }

    @Override
    public int updateData() throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
        public int updateData(Student student) throws SQLException, ClassNotFoundException {
            int result = 0;
            String query = "UPDATE student SET first_name = ?, last_name = ?, address = ?," +
                    "departement_id = ? WHERE id = ?";
            try(Connection connection = MySQLConnection.createConnection()){
                try (PreparedStatement ps = connection.prepareStatement(query)) {
                    ps.setString(1, student.getFirst_name());
                    ps.setString(2, student.getLast_name());
                    ps.setString(3, student.getAddress());
                    ps.setInt(4, student.getDepartement().getId());
                    ps.setString(5, student.getId());
                    if(ps.executeUpdate()!=0){
                        connection.commit();
                        result=1;

                    }else{
                        connection.rollback();
                    }
                }
            }
            return result;
        }


        @Override
        public int deleteData(Student student) throws SQLException, ClassNotFoundException {
            int result = 0;
            String query = "DELETE FROM student WHERE id=?";
            try (Connection connection = MySQLConnection.createConnection()){
                try (PreparedStatement ps = connection.prepareStatement(query)){
                    ps.setString(1, student.getId());
                    if(ps.executeUpdate()!=0){
                        connection.commit();
                        result=1;
                    }else{
                        connection.rollback();
                    }
                }
            }
            return result;
        }
}
