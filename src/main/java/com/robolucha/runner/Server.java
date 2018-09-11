package com.robolucha.runner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.io.File;

import com.google.gson.Gson;
import com.robolucha.game.action.OnInitAddNPC;
import com.robolucha.models.GameDefinition;
import com.robolucha.models.Match;
import com.robolucha.monitor.ThreadMonitor;
import com.robolucha.publisher.MatchEventPublisher;
import com.robolucha.publisher.MatchMessagePublisher;
import com.robolucha.publisher.MatchStatePublisher;
import com.robolucha.publisher.RemoteQueue;
import com.robolucha.score.ScoreUpdater;

/*
 * Runs a Match based on the input MatchDefinition ID
 */
public class Server {

    MatchMessagePublisher matchMessagePublisher;

    public static void main(String[] args) throws Exception {

        System.out.println(">> path =" + Paths.get(".").toAbsolutePath().normalize().toString());

        addRunTimeHook();

        if (args.length < 1) {
            throw new RuntimeException("Invalid use, must provide GameDefinition json file name");
        }

        RemoteQueue queue = new RemoteQueue(Config.getInstance());
        MatchStatePublisher publisher = new MatchStatePublisher(queue);

        GameDefinition gameDefinition = loadGameDefinition(args[0]);
        ThreadMonitor threadMonitor = ThreadMonitor.getInstance();

        Match match = MatchRunnerAPI.getInstance().createMatch(gameDefinition);
        MatchRunner runner = new MatchRunner(gameDefinition, match);

        Thread thread = buildRunner(runner, queue, threadMonitor, publisher);
        thread.start();

    }

    private static void addRunTimeHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                ThreadMonitor.getInstance().contextDestroyed();
            }
        });
    }

    static GameDefinition loadGameDefinition(String filename) throws Exception {
        Gson gson = new Gson();
        String fileContent = readFile(filename);
        GameDefinition definition = gson.fromJson(fileContent, GameDefinition.class);
        return definition;
    }

    private static String readFile(String filename) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuffer buffer = new StringBuffer();

        String line = reader.readLine();
        while (line != null) {
            buffer.append(line);
            line = reader.readLine();
        }
        reader.close();
        return buffer.toString();
    }

    public static Thread buildRunner(MatchRunner runner, RemoteQueue queue, ThreadMonitor threadMonitor, MatchStatePublisher publisher) {
        ThreadMonitor.getInstance().register(runner);

        //add NPC to the match
        runner.addListener(new OnInitAddNPC());

        // listener to record match events
        runner.addListener(new MatchEventPublisher(runner.getMatch(), queue, threadMonitor));

        // listener to update scores
        runner.addListener(new ScoreUpdater());

        // listener to the match state
        runner.setPublisher(publisher);

        // message listener TODO: convert all listeners to this format
        runner.addListener(new MatchMessagePublisher(queue));


        return new Thread(runner);
    }
}
