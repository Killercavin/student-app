package com.stephen.studentapp.controller

import com.stephen.studentapp.dto.request.CreateStudentRequest
import com.stephen.studentapp.dto.request.StudentLoginRequest
import com.stephen.studentapp.dto.response.StudentResponse
import com.stephen.studentapp.service.StudentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth/students")
class StudentController(
    private val studentService: StudentService
) {

    @GetMapping("/all")
    fun getAllStudents(): List<StudentResponse> {
        return studentService.getStudents()
    }

    @PostMapping("/register")
    fun register(@Valid @RequestBody request: CreateStudentRequest): ResponseEntity<StudentResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            studentService.create(request)
        )
    }

    @GetMapping("/byUsername/{username}")
    fun getStudentByUsername(@Valid @PathVariable username: String): ResponseEntity<StudentResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(
            studentService.getStudentByUsername(username)
        )
    }

    @GetMapping("/byId/{id}")
    fun getStudentById(@Valid @PathVariable id: String): ResponseEntity<StudentResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(
            studentService.getStudentById(id.toLong())
        )
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: StudentLoginRequest): String {
        return studentService.login(request)
    }
}