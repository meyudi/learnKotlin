package com.uddhav.country

class Person(
    private val fname: String,
    private val lname: String,
    private val nationality: Nationality
) {
    constructor(fname: String, lname: String? = null, country: String) : this(
        fname = fname,
        lname = lname ?: "",
        nationality = NationalityFactory.create(Country.valueOf(country.uppercase()))
    )

    fun getFullName() = "${this.lname} ${this.fname}"
    fun getHonorificFullName() = this.nationality.honorifics.attachHonorific(getFullName())
    fun getNationality() = this.nationality.country
}
