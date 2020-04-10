package it.tarczynski.hotel.california.core.model.aggregate

abstract class EntityId(val id: String) {

    override fun equals(other: Any?): Boolean {
        return other is EntityId && other.id == id
    }

    override fun hashCode(): Int {
        return id.hashCode() * 31
    }

    override fun toString(): String {
        return id
    }
}
