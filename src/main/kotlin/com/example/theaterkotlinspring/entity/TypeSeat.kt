package com.example.theaterkotlinspring.entity

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table( uniqueConstraints = [UniqueConstraint(columnNames = ["description"])])
enum class TypeSeat(
    @Id
    @Column(nullable = false)
    val id: Long,
    @Column(nullable = false)
    val price: BigDecimal,
    @Column(nullable = false)
    val description: String,
) {

    BEST_VIEW(0,BigDecimal(21.0), "Best view"),
    STANDARD_SEAT(1,BigDecimal(18.0), "Standard view"),
    RESTRICTED_VIEW(2,BigDecimal(16.5), "Restricted view"),
    CHEAPER_SEAT(3,BigDecimal(14.5), "Cheaper seat"),
    BACK_ROW(4,BigDecimal(14.5), "Back row")
}
