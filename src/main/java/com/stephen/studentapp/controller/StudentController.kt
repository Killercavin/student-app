package com.stephen.studentapp.controller

import com.stephen.studentapp.dto.request.CreateStudentRequest
import com.stephen.studentapp.dto.response.StudentResponse
import com.stephen.studentapp.service.StudentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth/student")
class StudentController(
    private val studentService: StudentService
) {

    @RequestMapping("/register")
    fun register(@Valid @RequestBody request: CreateStudentRequest): ResponseEntity<StudentResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            studentService.create(request)
        )
    }
}