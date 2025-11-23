package com.stephen.studentapp.repository

import com.stephen.studentapp.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository: JpaRepository<Student, Long> {
    fun findStudentByUsername(username: String): Student?
    fun existsByUsername(username: String): Boolean
}