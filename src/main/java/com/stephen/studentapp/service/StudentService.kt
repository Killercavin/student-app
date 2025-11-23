package com.stephen.studentapp.service

import com.stephen.studentapp.dto.request.CreateStudentRequest
import com.stephen.studentapp.dto.request.toEntity
import com.stephen.studentapp.dto.response.StudentResponse
import com.stephen.studentapp.dto.response.toStudentResponse
import com.stephen.studentapp.exception.DuplicateStudentFieldException
import com.stephen.studentapp.repository.StudentRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val studentRepository: StudentRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun create(request: CreateStudentRequest): StudentResponse {
        // validate student exists
        if (studentRepository.existsByUsername(request.username)) {
            throw DuplicateStudentFieldException(message = "Student already exists")
        }

        return studentRepository.save(request.toEntity(passwordEncoder)).toStudentResponse()
    }
}