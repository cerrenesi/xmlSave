package xmlsave.entity

abstract class BaseEntity (@Transient open val id: Long = newEntityId) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseEntity) return false

        if (id == newEntityId || other.id == newEntityId) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        const val newEntityId = 0L
        const val schemaName = "dbo"
    }
}