package com.stephen.studentapp.service

import com.stephen.studentapp.config.CustomUserDetails
import com.stephen.studentapp.repository.StudentRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val studentRepository: StudentRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        studentRepository.findStudentByUsername(username) ?: throw UsernameNotFoundException("Student with username $username not found")
        return CustomUserDetails()
    }
}