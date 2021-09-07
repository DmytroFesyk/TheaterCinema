package com.example.theaterkotlinspring.repository

import com.example.theaterkotlinspring.entity.Actor
import com.example.theaterkotlinspring.entity.Performance
import com.example.theaterkotlinspring.entity.Seance
import com.example.theaterkotlinspring.entity.Seat
import org.springframework.data.jpa.repository.JpaRepository

interface SeanceRepository :JpaRepository<Seance,Long>