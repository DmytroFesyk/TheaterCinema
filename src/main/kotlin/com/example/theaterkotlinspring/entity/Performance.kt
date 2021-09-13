package com.example.theaterkotlinspring.entity

import org.springframework.format.annotation.DateTimeFormat
import java.sql.Date
import javax.persistence.*

@Entity
data class Performance(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long = 0,
    @Column(nullable = false)
    val title: String = "",
    @Column(columnDefinition="TEXT")
    val description: String = "",
    @Column(nullable = false)
    val durationTime: Int = 0,
    val distributionStart: Date?=null,
    val distributionEnd: Date?=null ,
    var imgLink: String = "",
) {

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "performance_actors",
        joinColumns = [JoinColumn(name = "performance_id")],
        inverseJoinColumns = [JoinColumn(name = "actors_id")]
    )
    var actors: List<Actor> = emptyList()

    @OneToMany(mappedBy = "performance")
    lateinit var seances: List<Seance>
}
