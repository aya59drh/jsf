package org.example.database3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class StudentBean {
    private Student student= new Student();

    public Student getStudent() {
        return student;
    }

        public void setStudent(Student student) {
            this.student = student;
        }

        public void addStudent(){
            try{

                Connection con = DBConnection.getConnection();

                String sql="INSERT INTO student(nom,prenom,email) VALUES(?,?,?)";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, student.getNom());
                ps.setString(2, student.getPrenom());
                ps.setString(3, student.getEmail());

                ps.executeUpdate();

                student = new Student();

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        public List<Student> getStudents(){
            List<Student> list = new java.util.ArrayList<>();

            try{

                Connection con = DBConnection.getConnection();

                String sql="SELECT * FROM student";

                java.sql.Statement st = con.createStatement();

                java.sql.ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    Student s = new Student();
                    s.setId(rs.getInt("id"));
                    s.setNom(rs.getString("nom"));
                    s.setPrenom(rs.getString("prenom"));
                    s.setEmail(rs.getString("email"));
                    list.add(s);
                }

            }
            catch(Exception e){
                e.printStackTrace();
            }

            return list;
        }

        public void deleteStudent(int id){
            try{

                Connection con = DBConnection.getConnection();

                String sql="DELETE FROM student WHERE id=?";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1, id);

                ps.executeUpdate();

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }



}
