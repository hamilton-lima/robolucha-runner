package com.robolucha.support;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.robolucha.models.Code;
import com.robolucha.models.GameComponent;
import com.robolucha.models.GameDefinition;
import com.robolucha.runner.luchador.MethodNames;

import io.swagger.client.model.MainGameComponent;
import io.swagger.client.model.MainGameDefinition;

/**
 * Create a json file with the default Game definition values to be used as
 * template for game definitions
 * 
 * @author hamiltonlima TODO: call this using a parameter in the Server class
 */
public class DefaultGameDefinitionFileCreator {

	public static void main(String[] args) throws IOException {
		MainGameDefinition gameDefinition = new MainGameDefinition();
		addGameComponent(gameDefinition);

		String json = generateJson(gameDefinition);

		if (args.length > 0) {
			String fileName = args[0];
			writeFile(fileName, json);
		} else {
			System.out.println(json);
		}

	}

	private static void writeFile(String fileName, String json) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(json);
		writer.flush();
		writer.close();
	}

	private static String generateJson(MainGameDefinition gameDefinition) {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

		String result = gson.toJson(gameDefinition);
		return result;
	}

	public static final String OTTO = "otto";
	public static final String FAROL = "farol";

	public static void addGameComponent(MainGameDefinition gameDefinition) {

		MainGameComponent otto = new MainGameComponent();
		otto.setName(OTTO);
		otto.getCodes().add(new Code(MethodNames.ON_START, "turnGun(90)"));
		otto.getCodes().add(new Code(MethodNames.ON_REPEAT, "move(20)\nfire(3)"));
		otto.getCodes().add(new Code(MethodNames.ON_HIT_WALL, "turn(90)\nturnGun(90)"));

		MainGameComponent farol = new MainGameComponent();
		farol.setName(FAROL);
		farol.getCodes().add(new Code(MethodNames.ON_REPEAT, "turn(10)\nturnGun(-10)\nfire(1)"));

		gameDefinition.getGameComponents().add(otto);
		gameDefinition.getGameComponents().add(farol);
	}

}
