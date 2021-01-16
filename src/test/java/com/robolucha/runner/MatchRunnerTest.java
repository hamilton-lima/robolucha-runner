package com.robolucha.runner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.robolucha.game.vo.MatchReadyToStartVO;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.swagger.client.model.ModelGameComponent;
import io.swagger.client.model.ModelTeam;
import io.swagger.client.model.ModelTeamDefinition;

public class MatchRunnerTest {

	@Test
	public void testAlreadyInTheMatch() throws Exception {

		MatchRunner runner = MockMatchRunner.build();
		runner.getGameDefinition().setMinParticipants(2);

		ModelGameComponent a = MockLuchador.build(1, MethodNames.ON_REPEAT, "--");
		runner.add(a).blockingFirst();
		try {
			runner.add(a).blockingFirst();
			fail("An exception should be generated");
		} catch (Exception e) {

		}
	}

	@Test
	public void testReadyToStartMatchMinParticipants() throws Exception {

		MatchRunner runner = MockMatchRunner.build();
		runner.getGameDefinition().setMinParticipants(2);

		ModelGameComponent a = MockLuchador.build(1, MethodNames.ON_REPEAT, "--");
		runner.add(a).blockingFirst();

		MatchReadyToStartVO info = runner.readyToStartMatch();
		assertFalse("Cant start with only one luchador", info.ready);
	}

	@Test
	public void testReadyToStartMatchMinParticipantsTrue() throws Exception {

		MatchRunner runner = MockMatchRunner.build();
		runner.getGameDefinition().setMinParticipants(2);

		ModelGameComponent a = MockLuchador.build(1);
		ModelGameComponent b = MockLuchador.build(2);
		runner.add(a).blockingFirst();
		runner.add(b).blockingFirst();
		
		MatchReadyToStartVO info = runner.readyToStartMatch();
		assertTrue("CAN start with only one luchador", info.ready);
	}

	@Test
	public void testSingleTeam() throws Exception {

		ModelTeam team = new ModelTeam();
		team.setId(1);
		team.setMinParticipants(2);
		team.setMaxParticipants(2);

		ModelTeam team2 = new ModelTeam();
		team2.setId(2);
		team2.setMinParticipants(2);
		team2.setMaxParticipants(2);
		
		ModelTeamDefinition teamDefinition = new ModelTeamDefinition();
		teamDefinition.addTeamsItem(team);
		
		MatchRunner runner = MockMatchRunner.build();
		runner.getGameDefinition().setMinParticipants(2);
		runner.getGameDefinition().setTeamDefinition(teamDefinition);

		ModelGameComponent a = MockLuchador.build(1, MethodNames.ON_REPEAT, "--");
		ModelGameComponent b = MockLuchador.build(2, MethodNames.ON_REPEAT, "--");
		
		// ONE for each team 
		runner.add(a, 1).blockingFirst();
		runner.add(b, 2).blockingFirst();

		MatchReadyToStartVO info = runner.readyToStartMatch();
		assertFalse("CANT start", info.ready);
	}
	
	@Test
	public void testSingleTeamPositiveCase() throws Exception {

		ModelTeam team = new ModelTeam();
		team.setId(1);
		team.setMinParticipants(2);
		team.setMaxParticipants(2);

		ModelTeam team2 = new ModelTeam();
		team2.setId(2);
		team2.setMinParticipants(2);
		team2.setMaxParticipants(2);
		
		ModelTeamDefinition teamDefinition = new ModelTeamDefinition();
		teamDefinition.addTeamsItem(team);
		
		MatchRunner runner = MockMatchRunner.build();
		runner.getGameDefinition().setMinParticipants(2);
		runner.getGameDefinition().setTeamDefinition(teamDefinition);

		runner.add(MockLuchador.build(1), 1).blockingFirst();
		runner.add(MockLuchador.build(2), 1).blockingFirst();

		runner.add(MockLuchador.build(3), 2).blockingFirst();
		runner.add(MockLuchador.build(4), 2).blockingFirst();

		MatchReadyToStartVO info = runner.readyToStartMatch();
		assertTrue("CAN start", info.ready);
	}
	

	@Test
	public void testSingleTeamMixedCase() throws Exception {

		ModelTeam team = new ModelTeam();
		team.setId(1);
		team.setMinParticipants(2);
		team.setMaxParticipants(2);

		ModelTeam team2 = new ModelTeam();
		team2.setId(2);
		team2.setMinParticipants(2);
		team2.setMaxParticipants(2);
		
		ModelTeamDefinition teamDefinition = new ModelTeamDefinition();
		teamDefinition.addTeamsItem(team);
		
		MatchRunner runner = MockMatchRunner.build();
		runner.getGameDefinition().setMinParticipants(2);
		runner.getGameDefinition().setTeamDefinition(teamDefinition);

		runner.add(MockLuchador.build(1), 1).blockingFirst();

		runner.add(MockLuchador.build(3), 2).blockingFirst();
		runner.add(MockLuchador.build(4), 2).blockingFirst();

		MatchReadyToStartVO info = runner.readyToStartMatch();
		assertFalse("CANNOT start, one team is missing participants", info.ready);
	}
	

	@Test
	public void testAddMorethanMax() throws Exception {

		ModelTeam team = new ModelTeam();
		team.setId(1);
		team.setMinParticipants(2);
		team.setMaxParticipants(2);

		ModelTeam team2 = new ModelTeam();
		team2.setId(2);
		team2.setMinParticipants(2);
		team2.setMaxParticipants(2);
		
		ModelTeamDefinition teamDefinition = new ModelTeamDefinition();
		teamDefinition.addTeamsItem(team);
		
		MatchRunner runner = MockMatchRunner.build();
		runner.getGameDefinition().setMinParticipants(2);
		runner.getGameDefinition().setTeamDefinition(teamDefinition);

		runner.add(MockLuchador.build(1), 1).blockingFirst();
		runner.add(MockLuchador.build(2), 1).blockingFirst();
		try {
			runner.add(MockLuchador.build(3), 1).blockingFirst();
			fail("An exception should be generated, The team only allow 2");
		} catch (Exception e) {

		}
	}
}
