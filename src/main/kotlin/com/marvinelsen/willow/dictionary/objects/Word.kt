package com.marvinelsen.willow.dictionary.objects

import com.marvinelsen.willow.persistence.entities.WordEntity

data class Word(
    val traditional: String,
    val zhuyin: String,
    val definitions: Map<SourceDictionary, List<Definition>>,
) {
    val preferredDefinitions: List<Definition> by lazy {
        definitions[SourceDictionary.LAC] ?: definitions[SourceDictionary.MOE] ?: definitions[SourceDictionary.CEDICT]!!
    }
}

fun WordEntity.asWord() = Word(
    traditional = traditional,
    zhuyin = zhuyin,
    definitions = definitions.map { it.asDefinition() }.groupBy { it.sourceDictionary })