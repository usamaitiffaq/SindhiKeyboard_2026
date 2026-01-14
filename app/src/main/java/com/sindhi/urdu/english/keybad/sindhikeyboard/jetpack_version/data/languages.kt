package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data

import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.Key

data class Language(
    val name: Int,
    val description: Int = name,
    val layout: Array<Array<Key>>,
    val enabled: Boolean = true
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Language

        if (name != other.name) return false
        if (description != other.description) return false
        if (!layout.contentDeepEquals(other.layout)) return false
        if (enabled != other.enabled) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + layout.contentDeepHashCode()
        result = 31 * result + enabled.hashCode()
        return result
    }
}