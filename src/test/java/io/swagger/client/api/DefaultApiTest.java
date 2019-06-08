/*
 * Robolucha API
 * Robolucha API
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.model.MainConfig;
import io.swagger.client.model.MainFindLuchadorWithGamedefinition;
import io.swagger.client.model.MainGameComponent;
import io.swagger.client.model.MainGameDefinition;
import io.swagger.client.model.MainJoinMatch;
import io.swagger.client.model.MainMatch;
import io.swagger.client.model.MainMatchParticipant;
import io.swagger.client.model.MainMatchScore;
import io.swagger.client.model.MainScoreList;
import io.swagger.client.model.MainUpdateLuchadorResponse;
import io.swagger.client.model.MainUser;
import io.swagger.client.model.MainUserSetting;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 * API tests for DefaultApi
 */
@Ignore
public class DefaultApiTest {

    private final DefaultApi api = new DefaultApi();

    /**
     * saves a match score
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void internalAddMatchScoresPostTest() throws ApiException {
        MainScoreList body = null;
        MainMatchScore response = api.internalAddMatchScoresPost(body);

        // TODO: test validations
    }
    /**
     * ends existing match
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void internalEndMatchPutTest() throws ApiException {
        MainMatch body = null;
        MainMatch response = api.internalEndMatchPut(body);

        // TODO: test validations
    }
    /**
     * Create Gamecomponent as Luchador
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void internalGameComponentPostTest() throws ApiException {
        MainGameComponent body = null;
        MainGameComponent response = api.internalGameComponentPost(body);

        // TODO: test validations
    }
    /**
     * find a game definition
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void internalGameDefinitionIdIdGetTest() throws ApiException {
        Integer id = null;
        MainGameDefinition response = api.internalGameDefinitionIdIdGet(id);

        // TODO: test validations
    }
    /**
     * find a game definition
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void internalGameDefinitionNameGetTest() throws ApiException {
        String name = null;
        MainGameDefinition response = api.internalGameDefinitionNameGet(name);

        // TODO: test validations
    }
    /**
     * create Game definition
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void internalGameDefinitionPostTest() throws ApiException {
        MainGameDefinition body = null;
        MainGameDefinition response = api.internalGameDefinitionPost(body);

        // TODO: test validations
    }
    /**
     * find Luchador by ID
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void internalLuchadorPostTest() throws ApiException {
        MainFindLuchadorWithGamedefinition body = null;
        MainGameComponent response = api.internalLuchadorPost(body);

        // TODO: test validations
    }
    /**
     * Adds luchador to a match
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void internalMatchParticipantPostTest() throws ApiException {
        MainMatchParticipant body = null;
        MainMatchParticipant response = api.internalMatchParticipantPost(body);

        // TODO: test validations
    }
    /**
     * find one match
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void internalMatchSingleGetTest() throws ApiException {
        Integer matchID = null;
        MainMatch response = api.internalMatchSingleGet(matchID);

        // TODO: test validations
    }
    /**
     * returns application health check information
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void internalReadyGetTest() throws ApiException {
        api.internalReadyGet();

        // TODO: test validations
    }
    /**
     * create Match
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void internalStartMatchNamePostTest() throws ApiException {
        String name = null;
        MainMatch response = api.internalStartMatchNamePost(name);

        // TODO: test validations
    }
    /**
     * find all game definitions
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateGameDefinitionAllGetTest() throws ApiException {
        List<MainGameDefinition> response = api.privateGameDefinitionAllGet();

        // TODO: test validations
    }
    /**
     * find The current user information
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateGetUserGetTest() throws ApiException {
        MainUser response = api.privateGetUserGet();

        // TODO: test validations
    }
    /**
     * Sends message with the request to join the match
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateJoinMatchPostTest() throws ApiException {
        MainJoinMatch body = null;
        MainMatch response = api.privateJoinMatchPost(body);

        // TODO: test validations
    }
    /**
     * find or create Luchador for the current user
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateLuchadorGetTest() throws ApiException {
        MainGameComponent response = api.privateLuchadorGet();

        // TODO: test validations
    }
    /**
     * Updates Luchador
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateLuchadorPutTest() throws ApiException {
        MainGameComponent body = null;
        MainUpdateLuchadorResponse response = api.privateLuchadorPut(body);

        // TODO: test validations
    }
    /**
     * find maskConfig for a luchador
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateMaskConfigIdGetTest() throws ApiException {
        Integer id = null;
        List<MainConfig> response = api.privateMaskConfigIdGet(id);

        // TODO: test validations
    }
    /**
     * create random maskConfig
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateMaskRandomGetTest() throws ApiException {
        List<MainConfig> response = api.privateMaskRandomGet();

        // TODO: test validations
    }
    /**
     * return luchador configs for current match
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateMatchConfigGetTest() throws ApiException {
        Integer matchID = null;
        List<MainGameComponent> response = api.privateMatchConfigGet(matchID);

        // TODO: test validations
    }
    /**
     * find active matches
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateMatchGetTest() throws ApiException {
        List<MainMatch> response = api.privateMatchGet();

        // TODO: test validations
    }
    /**
     * create Match and publish
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateStartTutorialMatchNamePostTest() throws ApiException {
        String name = null;
        MainJoinMatch response = api.privateStartTutorialMatchNamePost(name);

        // TODO: test validations
    }
    /**
     * find tutorial GameDefinition
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateTutorialGetTest() throws ApiException {
        List<MainGameDefinition> response = api.privateTutorialGet();

        // TODO: test validations
    }
    /**
     * find current user userSetting
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateUserSettingGetTest() throws ApiException {
        MainUserSetting response = api.privateUserSettingGet();

        // TODO: test validations
    }
    /**
     * Updates user userSetting
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateUserSettingPutTest() throws ApiException {
        MainUserSetting body = null;
        MainUserSetting response = api.privateUserSettingPut(body);

        // TODO: test validations
    }
}
