package com.stephen.studentapp.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    val id: Long? = null,
    @Column(nullable = false, columnDefinition = "TEXT", name = "full_name")
    val fullName: String,
    @Column(nullable = false, columnDefinition = "TEXT", name = "username")
    val username: String,
    @Column(nullable = false, columnDefinition = "TEXT", name = "password")
    val password: String,
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: Instant? = null,
    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Instant? = null,
)
