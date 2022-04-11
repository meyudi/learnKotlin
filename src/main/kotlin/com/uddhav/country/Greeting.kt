package com.uddhav.country

object Greetings {
    fun greet(vararg persons: Person) {
        persons.forEach { greet(it) }
    }
    private fun greet(person: Person) {
        val countryGreeting = when (person.getNationality()) {
            Country.JAPAN -> JapaneseGreeting
            Country.INDIA -> IndianGreeting
            Country.USA -> EnglishGreeting
            Country.FRANCE -> FrenchGreeting
            Country.ENGLAND -> EnglishGreeting
        }
        countryGreeting.greet(person)
    }
}

interface Greeting {
    val greeting: String

    fun greet(person: Person) {
        println("$greeting ${person.getHonorificFullName()}")
    }
}

object EnglishGreeting : Greeting {
    override val greeting: String
        get() = "Hello"
}

object IndianGreeting : Greeting {
    override val greeting: String
        get() = "Namaste"
}

object FrenchGreeting : Greeting {
    override val greeting: String
        get() = "Bonjour"
}

object JapaneseGreeting : Greeting {
    override val greeting: String
        get() = "こんにちわ"
}
