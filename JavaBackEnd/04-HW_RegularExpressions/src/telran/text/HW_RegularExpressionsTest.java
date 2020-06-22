package telran.text;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HW_RegularExpressionsTest {

	@Test
	void testIsraelMobilePhone() {
		assertTrue("+972-50-1-22-33-44".matches(HW_RegularExpressions.israelMobilePhone()));
		assertTrue("+972541223344".matches(HW_RegularExpressions.israelMobilePhone()));
		assertTrue("057-1223344".matches(HW_RegularExpressions.israelMobilePhone()));
		assertTrue("058-122-33-44".matches(HW_RegularExpressions.israelMobilePhone()));
		assertFalse("057+1223344".matches(HW_RegularExpressions.israelMobilePhone()));
		assertFalse("050-1-22-33-445".matches(HW_RegularExpressions.israelMobilePhone()));
		assertFalse("50-1-22-33-44".matches(HW_RegularExpressions.israelMobilePhone()));
		assertFalse("972-50-1-22-33-445".matches(HW_RegularExpressions.israelMobilePhone()));
		assertFalse("+972-050-1-22-33-44".matches(HW_RegularExpressions.israelMobilePhone()));
		assertFalse("050-1-22-33-4t5".matches(HW_RegularExpressions.israelMobilePhone()));
		assertFalse("057-122--3344".matches(HW_RegularExpressions.israelMobilePhone()));
		assertFalse("051-122-33-44".matches(HW_RegularExpressions.israelMobilePhone()));
		assertFalse("052-122-33-4-".matches(HW_RegularExpressions.israelMobilePhone()));
		assertFalse("050-122-33-44-".matches(HW_RegularExpressions.israelMobilePhone()));
		assertFalse("+972-050-122-33-44-".matches(HW_RegularExpressions.israelMobilePhone()));
	}

	@Test
	void testEmailAddress() {
		assertTrue("yuragranovsky@gmail.com".matches(HW_RegularExpressions.emailAddress()));
		assertTrue("tt%2@mail.ru".matches(HW_RegularExpressions.emailAddress()));
		assertTrue("tt_67@co.il.d-d.a-a".matches(HW_RegularExpressions.emailAddress()));
		assertTrue("t5&4_s@ff.gt".matches(HW_RegularExpressions.emailAddress()));
		assertTrue("alexander001@co.il".matches(HW_RegularExpressions.emailAddress()));
		assertTrue("alex#12@mail.ru".matches(HW_RegularExpressions.emailAddress()));
		assertTrue("alex!.12@mail.ru".matches(HW_RegularExpressions.emailAddress()));
//		assertTrue("abc.\"defghi\".xyz@example.com".matches(HW_RegularExpressions.emailAddress()));
//		assertFalse("abc\"defghi\"xyz@example.com".matches(HW_RegularExpressions.emailAddress()));
		assertFalse("yu ra@gmail.com".matches(HW_RegularExpressions.emailAddress()));
//		assertFalse(".yura@gmail.com".matches(HW_RegularExpressions.emailAddress()));
//		assertFalse("yura.@gmail.com".matches(HW_RegularExpressions.emailAddress()));
//		assertFalse("yu...ra@gmail.com".matches(HW_RegularExpressions.emailAddress()));
		assertFalse("yu,ra@gmail.com".matches(HW_RegularExpressions.emailAddress()));
		assertFalse("tt%2@ma_il.ru".matches(HW_RegularExpressions.emailAddress()));
		assertFalse("tt_67@co.il.dd.aa.bb".matches(HW_RegularExpressions.emailAddress()));
		assertFalse("t5&4_s@ffgt".matches(HW_RegularExpressions.emailAddress()));
		assertFalse("tt_67@co.-il.dd.aa".matches(HW_RegularExpressions.emailAddress()));
	}

}
