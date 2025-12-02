package com.stephen.studentapp.service

import com.stephen.studentapp.dto.request.CreateStudentRequest
import com.stephen.studentapp.dto.request.StudentLoginRequest
import com.stephen.studentapp.dto.request.toEntity
import com.stephen.studentapp.dto.response.StudentResponse
import com.stephen.studentapp.dto.response.toStudentResponse
import com.stephen.studentapp.exception.DuplicateStudentFieldException
import com.stephen.studentapp.exception.ResourceNotFoundException
import com.stephen.studentapp.repository.StudentRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.security.authentication.BadCredentialsException
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

    fun getStudents(): List<StudentResponse> {
        // paging the data from the most recent
        val page: PageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"))
        return studentRepository.findAll(page).content.map { it.toStudentResponse() }
        // return studentRepository.findAll().map { it.toStudentResponse() }
    }

    // get student by their username
    fun getStudentByUsername(username: String): StudentResponse {
        val student = studentRepository.findStudentByUsername(username)
            ?: throw ResourceNotFoundException("Student not found")
        return student.toStudentResponse()
    }

    // get student by their id
    fun getStudentById(id: Long): StudentResponse {
        val student = studentRepository.findById(id)
        .orElseThrow { ResourceNotFoundException("Student not found") }
        return student.toStudentResponse()
    }

    // login
    fun login(request: StudentLoginRequest): String {
        val student = studentRepository.findStudentByUsername(request.username)
            ?: throw ResourceNotFoundException("Student not found")

        // password check
        if (!passwordEncoder.matches(request.password, student.password)) {
            throw BadCredentialsException("Invalid credentials")
        }

        return "Student logged in"
    }
}