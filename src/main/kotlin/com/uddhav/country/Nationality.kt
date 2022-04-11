package com.uddhav.country

// factory singleon object
object NationalityFactory {
    fun create(country: Country) =
        when (country) {
            Country.JAPAN -> Japanese(country)
            Country.INDIA -> Indian(country)
            Country.USA -> American(country)
            Country.FRANCE -> French(country)
            Country.ENGLAND -> English(country)
        }
}

interface Nationality {
    val honorifics: Honorifics
    val country: Country
}

class Japanese(override val country: Country) : Nationality {
    override val honorifics: Honorifics
        get() = JapaneseHonorifics
}

class Indian(override val country: Country) : Nationality {
    override val honorifics: Honorifics
        get() = EnglishHonorifics
}

class American(override val country: Country) : Nationality {
    override val honorifics: Honorifics
        get() = EnglishHonorifics
}

class English(override val country: Country) : Nationality {
    override val honorifics: Honorifics
        get() = EnglishHonorifics
}

class French(override val country: Country) : Nationality {
    override val honorifics: Honorifics
        get() = FrenchHonorifics
}
