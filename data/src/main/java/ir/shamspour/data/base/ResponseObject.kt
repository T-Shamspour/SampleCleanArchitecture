package ir.shamspour.data.base

interface ResponseObject<out DomainObject : Any?> {
    fun toDomain(): DomainObject
}

