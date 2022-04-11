package com.uddhav.country

interface Honorifics {
    val honorific: String
    fun attachHonorific(name: String): String
}

object JapaneseHonorifics : Honorifics {
    override val honorific: String
        get() = "さん"

    override fun attachHonorific(name: String) = "$name $honorific"
}

object EnglishHonorifics : Honorifics {
    override val honorific: String
        get() = "Mr."

    override fun attachHonorific(name: String) = "$honorific $name "
}

object FrenchHonorifics : Honorifics {
    override val honorific: String
        get() = "Monsieur"

    override fun attachHonorific(name: String) = "$honorific $name "
}



