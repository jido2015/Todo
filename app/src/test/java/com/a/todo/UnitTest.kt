package com.a.todo

import com.a.todo.data.helper.HelperClass
import junit.framework.Assert.assertEquals
import org.junit.Test

class UnitTest {
    @Test
    fun formatPhoneNumberTest() {
        assertEquals("23408090712213", HelperClass.phoneNumberFormat("08090712213"))
    }

}