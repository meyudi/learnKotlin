package com.uddhav

import com.uddhav.country.Greetings
import com.uddhav.country.Person

/* learnt about
*  how to write classes
*  how to extend classes
*  use of interface and how to extend them
*  use to enum
*  factory and singleton design pattern
*/

fun main(vararg args: String) {
    val p1 = Person("Uddhav", "Arote", "India")
    val p2 = Person("Uddhav", country = "France")
    val p3 = Person("Uddhav", "Arote", "Japan")
    val p4 = Person("Uddhav", "Arote", "USA")
    val p5 = Person("Uddhav", "Arote", "England")

    Greetings.greet(p1, p2, p3, p4, p5)
}
