package org.delivery.api.domain.userorder.controller.model

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import org.delivery.db.userorder.enums.UserOrderStatus
import java.math.BigDecimal
import java.time.LocalDateTime


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
data class UserOrderResponse (
    val id: Long? = null,
    val storeId: Long? = null,
    val userId: Long? = null,
    val status: UserOrderStatus? = null,
    val amount: BigDecimal? = null,
    val orderedAt: LocalDateTime? = null,
    val acceptedAt: LocalDateTime? = null,
    val cookingStartedAt: LocalDateTime? = null,
    val deliveryStartedAt: LocalDateTime? = null,
    val receivedAt: LocalDateTime? = null,
)