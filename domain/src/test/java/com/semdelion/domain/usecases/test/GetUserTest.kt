package com.semdelion.domain.usecases.test

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GetUserTest {
    @Test
    fun test1() {
        val expected = 4
        val actual = 2+2
        Assertions.assertEquals(expected, actual)
    }

}