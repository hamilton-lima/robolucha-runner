package com.robolucha.runner.code.lua;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.robolucha.runner.code.MethodDefinition;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.runner.luchador.LuchadorFacade;
import com.robolucha.runner.luchador.LuchadorRunner;

public class LuaScriptDefinitionTest {

	LuchadorLuaScriptDefinition definition;
	LuaFacadeLocal facade;

	@Before
	public void setup() throws Exception {
		definition = new LuchadorLuaScriptDefinition();
		definition.loadDefaultLibraries();
		facade = new LuaFacadeLocal(null, "test");
	}
	
	@Test
	public void testGetDefaultMethods() throws IllegalArgumentException, IllegalAccessException {

		HashMap<String, MethodDefinition> methods = definition.getDefaultMethods();
		Field[] fields = MethodNames.class.getFields();
		for (Field field : fields) {
			String name = field.getName();
			String value = (String) field.get(null);

			// dont test ALL 
			if( value.equals(MethodNames.ALL)) {
				continue;
			}

			System.out.println("checking for default methods of: " + name + "=" + value);
			MethodDefinition methodDefinition = methods.get(value);
			assertNotNull(methodDefinition);

			assertTrue(methodDefinition.getStart().startsWith("function "));
			assertTrue(methodDefinition.getEnd().endsWith("end"));
		}

	}

	@Test
	public void testRunWithNoParameter() throws Exception {

		String start = "a = 'start'";
		String nop = "function nop() a = 'OMG' end";

		definition.eval(start);
		definition.eval(nop);

		definition.run(facade, "nop");

		assertEquals("OMG", definition.getString("return a"));
	}

	@Test
	public void testRunWithOneParameter() throws Exception {

		String start = "a = 'start'";
		String one = "function one(value) a = value.stringValue end";

		definition.eval(start);
		definition.eval(one);

		PublicSharedData data = new PublicSharedData();
		data.stringValue = "hey";
		definition.run(facade, "one", data);

		assertEquals("hey", definition.getString("return a"));
	}

	@Test
	public void testRunWithTwoParameter() throws Exception {
		String start = "a = 'start' b = 10";
		String two = "function two(value, intValue) a = value.stringValue b = intValue end";

		definition.eval(start);
		definition.eval(two);

		PublicSharedData data = new PublicSharedData();
		data.stringValue = "hey";
		definition.run(facade, "two", data, 42);

		assertEquals("hey", definition.getString("return a"));
		assertEquals(42, definition.getInt("return b"));
	}

	@Test
	public void testRunWithThreeParameter() throws Exception {
		String start = "a = 'start' b = 10 c = 23.5";
		String three = "function three(value, intValue, doubleValue) a = value.stringValue b = intValue c = doubleValue end";

		definition.eval(start);
		definition.eval(three);

		PublicSharedData data = new PublicSharedData();
		data.stringValue = "hey";
		data.doubleValue = 12.8;

		definition.run(facade, "three", data, 42, 12.8);

		assertEquals("hey", definition.getString("return a"));
		assertEquals(42, definition.getInt("return b"));
		assertEquals(12.8, definition.getDouble("return c"), 0.001);
	}

	@Test
	public void testRunWithMoreThan3Parameters() throws Exception {
		String start = "a = 'start' b = 10 c = 23.5";
		String four = "function four(value, intValue, doubleValue, x) a = value.stringValue b = intValue c = doubleValue end";

		definition.eval(start);
		definition.eval(four);

		PublicSharedData data = new PublicSharedData();
		data.stringValue = "hey";
		data.doubleValue = 12.8;

		try {
			definition.run(facade, "four", data, 42, 12.8, 1);
			fail("This should generate an exception");
		} catch (Exception e) {

		}

	}

	@Test
	public void testCallMoveUsingFacade() throws Exception {
		String maybe = "function maybe(amount) move(amount) end";
		definition.eval(maybe);
		definition.run(facade, "maybe", 42);
		assertEquals(42, facade.amount);

	}

	@Test
	public void testAfterCompile() {
		definition.afterCompile();
	}

	@Test
	public void testRun() {
		assertTrue(definition != null);
	}

	private class LuaFacadeLocal extends LuchadorFacade {

		public LuaFacadeLocal(LuchadorRunner owner, String codeName) {
			super(owner, codeName);
		}

		public int amount;

		@Override
		public void move(int amount) {
			this.amount = amount;
		}

	}

	@Test
	public void testLoadDefaultLibraries() throws Exception {
		definition.loadDefaultLibraries();
		assertEquals("#FAA21D", definition.getString("return NMSColor.TANGERINE"));
	}

}
