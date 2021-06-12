/**
 * @Author 1972015 -  Andreas Yoseph Liandy
 */

package com.andreas.JavaSwingDanJDBC.view;
import com.andreas.JavaSwingDanJDBC.dao.DepartementDaoImpl;
import com.andreas.JavaSwingDanJDBC.dao.StudentDaoImpl;
import com.andreas.JavaSwingDanJDBC.entity.Departement;
import com.andreas.JavaSwingDanJDBC.entity.Student;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;


public class MainForm {
    private JTextField textID;
    private JTextField textFirstName;
    private JTextField textLastName;
    private JTextArea textAddress;
    private JComboBox<Departement> comboDepartement;
    private JButton buttonAddDepartement;
    private JButton buttonUpdate;
    private JButton buttonReset;
    private JButton buttonSave;
    private JTable tableStudent;
    private JSplitPane rootPanel;
    private JButton buttonDelete;
    private DepartementDaoImpl departementDao;
    private StudentDaoImpl studentDao;
    private List<Departement> departements;
    private List<Student> students;
    private DefaultComboBoxModel<Departement> departementDefaultComboBoxModel;
    private StudentTableModel studentTableModel;
    private Student selectedStudent;


    public MainForm() {
        departementDao = new DepartementDaoImpl();
        studentDao = new StudentDaoImpl();
        departements = new ArrayList<>();
        students = new ArrayList<>();
        try{
            departements.addAll(departementDao.fetchAll());
            students.addAll(studentDao.fetchAll());
        } catch(SQLException | ClassNotFoundException throwables){
            throwables.printStackTrace();
        }
        departementDefaultComboBoxModel =
                new DefaultComboBoxModel<>(departements.toArray(new Departement[0]));
        comboDepartement.setModel(departementDefaultComboBoxModel);
        studentTableModel = new StudentTableModel(students);
        tableStudent.setModel(studentTableModel);
        tableStudent.setAutoCreateRowSorter(true);




        buttonAddDepartement.addActionListener(e -> {
            String newDepartment = JOptionPane.showInputDialog(rootPanel, "new department name");
            if (newDepartment !=null && !newDepartment.trim().isEmpty()){
                Departement department= new Departement();
                department.setName(newDepartment);
                try {
                    if(departementDao.addData(department)==1){
                        departements.clear();
                        departements.addAll(departementDao.fetchAll());
                        departementDefaultComboBoxModel.removeAllElements();
                        departementDefaultComboBoxModel.addAll(departements);
                    }
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        buttonSave.addActionListener(e -> {
            if (textID.getText().trim().isEmpty() || textFirstName.getText().trim().isEmpty() || textAddress.getText().trim().isEmpty() || comboDepartement.getSelectedItem() == null){
                JOptionPane.showMessageDialog(rootPanel, "Please fill, id, first name, address, and department", "Error", JOptionPane.ERROR_MESSAGE);
            }else {
                Student student = new Student();
                student.setId(textID.getText());
                student.setFirst_name(textFirstName.getText());
                student.setLast_name(textLastName.getText().trim().isEmpty() ? null : textLastName.getText());
                student.setAddress(textAddress.getText());
                student.setDepartement((Departement) comboDepartement.getSelectedItem());
                try {
                    if (studentDao.addData(student)==1){
                        students.clear();
                        students.addAll(studentDao.fetchAll());
                        studentTableModel.fireTableDataChanged();
                        clearAndReset();
                    }
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        buttonReset.addActionListener(e -> {
            clearAndReset();

        });

        buttonUpdate.addActionListener(e -> {
            if(textFirstName.getText().trim().isEmpty() || textAddress.getText().trim().isEmpty() || comboDepartement.getSelectedItem()==null){
                JOptionPane.showMessageDialog(rootPanel, "Please fill first name, address, and department", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                selectedStudent.setFirst_name(textFirstName.getText());
                selectedStudent.setLast_name(textLastName.getText().trim().isEmpty()?null:
                        textLastName.getText());
                selectedStudent.setDepartement((Departement) comboDepartement.getSelectedItem());
                try {
                    if(studentDao.updateData(selectedStudent)==1){
                        students.clear();
                        students.addAll(studentDao.fetchAll());
                        studentTableModel.fireTableDataChanged();
                        clearAndReset();
                    }
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }


        });
        buttonDelete.addActionListener(e -> {
            if(textFirstName.getText().trim().isEmpty() || textAddress.getText().trim().isEmpty() || comboDepartement.getSelectedItem()==null){
                JOptionPane.showMessageDialog(rootPanel, "Please fill first name, address, and department", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                selectedStudent.setFirst_name(textFirstName.getText());
                selectedStudent.setLast_name(textLastName.getText().trim().isEmpty()?null:
                        textLastName.getText());
                selectedStudent.setDepartement((Departement) comboDepartement.getSelectedItem());
                try {
                    if(studentDao.deleteData(selectedStudent)==1){
                        students.clear();
                        students.addAll(studentDao.fetchAll());
                        studentTableModel.fireTableDataChanged();
                        clearAndReset();
                    }
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        tableStudent.getSelectionModel().addListSelectionListener(e -> {
            if (!tableStudent.getSelectionModel().isSelectionEmpty()){
                int selectedIndex= tableStudent.convertRowIndexToModel(tableStudent.getSelectedRow());
                selectedStudent = students.get(selectedIndex);
                if (selectedStudent!=null){
                    textID.setText(selectedStudent.getId());
                    textFirstName.setText(selectedStudent.getFirst_name());
                    textLastName.setText(selectedStudent.getLast_name()!=null? selectedStudent.getLast_name():"");
                    textAddress.setText(selectedStudent.getAddress());
                    comboDepartement.setSelectedItem(selectedStudent.getDepartement());
                    textID.setEnabled(false);
                    buttonSave.setEnabled(false);
                    buttonUpdate.setEnabled(true);
                    buttonDelete.setEnabled(true);
                }
            }

        });
    }






    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void clearAndReset(){
        textID.setText("");
        textFirstName.setText("");
        textLastName.setText("");
        textAddress.setText("");
        textID.setEnabled(true);
        buttonSave.setEnabled(true);
        buttonUpdate.setEnabled(false);
        buttonDelete.setEnabled(true);
        selectedStudent = null;


    }

    private static class StudentTableModel extends AbstractTableModel{
        private List<Student> students;
        private final String[] COLUMNS = {"ID","FIRST NAME","LAST NAME","DEPARTEMENT"};

        public StudentTableModel(List<Student> students) {
            this.students = students;
        }

        @Override
        public int getRowCount() {
            return students.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch(columnIndex){
                case 0 -> students.get(rowIndex).getId();
                case 1 -> students.get(rowIndex).getFirst_name();
                case 2 -> students.get(rowIndex).getLast_name();
                case 3 -> students.get(rowIndex).getDepartement().getName();
                default -> "";
            };
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(getValueAt(0,columnIndex)!=null){
                return getValueAt(0,columnIndex).getClass();
            }
            return Object.class;
        }
    }
}
