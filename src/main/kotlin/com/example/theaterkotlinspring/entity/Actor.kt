package com.example.theaterkotlinspring.entity

import org.springframework.format.annotation.DateTimeFormat
import java.sql.Date
import javax.persistence.*

@Entity
data class Actor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long = 0,
    @Column(nullable = false)
    val name: String = "",
    @Column(nullable = false)
    val surname: String = "",
    val birthDay: Date? = null,
) {

    @ManyToMany(cascade = [CascadeType.ALL],mappedBy = "actors")
    lateinit var performance: List<Performance>
    override fun toString() = "$name $surname"
}

