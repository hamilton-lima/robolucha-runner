package com.robolucha.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.score.ScoreBuilder;

public class ScoreVOTest {

	Logger logger = Logger.getLogger(ScoreVOTest.class);

	@Before
	public void setup() {
	}

	@Test
	public void testScoreVOLongString() {

		ScoreVO vo = new ScoreVO(45, 2, "foo");
		assertEquals("foo", vo.getName());
		assertTrue(vo.getId().equals(vo.getId()));
		assertEquals(0, vo.getK());
		assertEquals(0, vo.getD());
		assertEquals(0, vo.getScore());
	}

	@Test
	public void testScoreBuilder() {

		ScoreVO vo = new ScoreVO(45, 3, "foo");
		ScoreBuilder builder = ScoreBuilder.getInstance();

		builder.addDamagePoints(vo, 5);
		assertEquals(25, vo.getScore());

		builder.addDamagePoints(vo, 1);
		assertEquals(30, vo.getScore());

		builder.addDamagePoints(vo, 10);
		assertEquals(80, vo.getScore());

		builder.addKillPoints(vo);
		assertEquals(280, vo.getScore());

		builder.addKillPoints(vo);
		assertEquals(480, vo.getScore());

		builder.addDamagePoints(vo, 3);
		assertEquals(495, vo.getScore());

		builder.addKill(vo);
		assertEquals(1, vo.getK());
		assertEquals(0, vo.getD());

		builder.addKill(vo);
		assertEquals(2, vo.getK());
		assertEquals(0, vo.getD());

		builder.addDeath(vo);
		assertEquals(2, vo.getK());
		assertEquals(1, vo.getD());

	}

	@Test
	public void testCompareToTudoIgual() {

		// tudo igual deve prevalecer o ID mais antigo
		List<ScoreVO> list = new ArrayList<ScoreVO>();
		ScoreVO vo1 = new ScoreVO(1, 0, "s1", 1, 1, 1);
		ScoreVO vo2 = new ScoreVO(2, 0, "s1", 1, 1, 1);

		list.add(vo1);
		list.add(vo2);

		Collections.sort(list);
		ScoreVO first = list.get(0);
		assertTrue(vo1.getId().equals(first.getId()));
	}

	@Test
	public void testCompareToMudandoValores() {

		// score maior
		ArrayList<ScoreVO> list = new ArrayList<ScoreVO>();
		ScoreVO vo1 = new ScoreVO(1, 0, "s1", 1, 0, 200);
		ScoreVO vo2 = new ScoreVO(2, 0, "s1", 1, 1, 250);

		list.add(vo1);
		list.add(vo2);
		Collections.sort(list);

		ScoreVO first = list.get(0);
		logger.debug(first);
		assertTrue(vo2.getId().equals(first.getId()));

		vo1.setScore(300);
		Collections.sort(list);

		first = list.get(0);
		logger.debug(first);
		assertTrue(vo1.getId().equals(first.getId()));

	}

	@Test
	public void testCompareToDesempates() {

		// score maior
		ArrayList<ScoreVO> list = new ArrayList<ScoreVO>();
		ScoreVO vo1 = new ScoreVO(1, 0, "s1", 1, 0, 200);
		ScoreVO vo2 = new ScoreVO(2, 0, "s1", 1, 1, 250);
		ScoreVO vo3 = new ScoreVO(3, 0, "s1", 2, 1, 250);

		list.add(vo1);
		list.add(vo2);
		list.add(vo3);
		Collections.sort(list);

		// score igual, Kills >
		ScoreVO first = list.get(0);
		logger.debug(first);
		assertTrue(vo3.getId().equals(first.getId()));

		vo1.setScore(250);
		vo1.setK(2);
		vo2.setK(2);

		Collections.sort(list);

		// score igual
		// kills igual
		// menor death vo1
		logger.debug(list);

		first = list.get(0);
		logger.debug(first);
		assertTrue(vo1.getId().equals(first.getId()));

	}

}
