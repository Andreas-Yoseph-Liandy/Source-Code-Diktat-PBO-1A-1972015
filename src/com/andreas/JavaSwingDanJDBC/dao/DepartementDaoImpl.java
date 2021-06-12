/**
 * @Author 1972015 -  Andreas Yoseph Liandy
 */

package com.andreas.JavaSwingDanJDBC.dao;
import com.andreas.JavaSwingDanJDBC.entity.Departement;
import com.andreas.JavaSwingDanJDBC.entity.Student;
import com.andreas.JavaSwingDanJDBC.util.DaoService;
import com.andreas.JavaSwingDanJDBC.util.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;


public class DepartementDaoImpl implements DaoService<Departement> {
    @Override
    public List<Departement> fetchAll() throws SQLException, ClassNotFoundException {
        List<Departement> departements = new ArrayList<>();
        String query = "SELECT id,name FROM departement";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while(rs.next()){
                        Departement departement = new Departement();
                        departement.setId(rs.getInt("id"));
                        departement.setName(rs.getString("name"));
                        departements.add(departement);
                    }
                }
            }
        }

        return departements;
    }

    @Override
    public int addData(Departement departement) throws SQLException, ClassNotFoundException {
        int result = 0;
        String query = "INSERT INTO departement(name) VALUES(?)";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, departement.getName());
                if(ps.executeUpdate()!= 0){
                    connection.commit();
                    result = 1;

                }else{
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int updateData() throws SQLException, ClassNotFoundException {
        return updateData();
    }

    @Override
    public int updateData(Departement departement) throws SQLException, ClassNotFoundException {
        int result=0;
        String query = "UPDATE departement SET name = ? WHERE id = ?";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, departement.getName());
                ps.setInt(2,departement.getId());
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
