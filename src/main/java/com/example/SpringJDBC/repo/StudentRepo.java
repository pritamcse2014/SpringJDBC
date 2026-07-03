package com.example.SpringJDBC.repo;

import com.example.SpringJDBC.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepo {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Student student) {
//        System.out.println("StudentRepo save");
        String sql = "insert into student(rollNo, name, marks)  values(?,?,?)";
        int rows = jdbcTemplate.update(sql,  student.getRollNo(), student.getName(), student.getMarks());
        System.out.println(rows);
    }

    public List<Student> findAll() {
//        List<Student> students = new ArrayList<>();
//        return students;
        String sql = "select * from student";
//        1
//        RowMapper<Student> rowMapper = new RowMapper<Student>() {
//            @Override
//            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

//        2
//        RowMapper<Student> rowMapper = (rs,rowNum) -> {
//                Student student = new Student();
//                student.setRollNo(rs.getInt("rollNo"));
//                student.setName(rs.getString("name"));
//                student.setMarks(rs.getInt("marks"));
//                return student;
////            }
//        };
//
//        return jdbcTemplate.query(sql, rowMapper);

//        3
        return jdbcTemplate.query(sql, (rs,rowNum) -> {
            Student student = new Student();
            student.setRollNo(rs.getInt("rollNo"));
            student.setName(rs.getString("name"));
            student.setMarks(rs.getInt("marks"));
            return student;
        });
    }
}
