package com.github.algamza.bucketplace.repository.local.entities

import androidx.room.Embedded
import androidx.room.Relation
import javax.annotation.Nullable

data class CardDetailEntity (
        @Embedded
        var card: CardEntity,
        @Relation(
                parentColumn = "user_id",
                entityColumn = "id",
                entity = UserEntity::class
        )
        @Nullable var user: UserEntity?,
        @Relation(
                parentColumn = "id",
                entityColumn = "id",
                entity = CardRecommendEntity::class
        )
        @Nullable var recommend: CardRecommendEntity?
    )