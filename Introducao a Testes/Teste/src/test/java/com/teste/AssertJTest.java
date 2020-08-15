package com.teste;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class AssertJTest {

	@Test
	public void checkEquality() {
		Person person = new Person("Jessica","Rodrigues");
		Person personClone = person;
		
		assertThat(person).isEqualTo(personClone);
	}
}
