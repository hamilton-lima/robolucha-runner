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
import io.swagger.client.model.ModelActiveMatch;
import io.swagger.client.model.ModelActivity;
import io.swagger.client.model.ModelAvailableMatch;
import io.swagger.client.model.ModelClassroom;
import io.swagger.client.model.ModelConfig;
import io.swagger.client.model.ModelFindLuchadorWithGamedefinition;
import io.swagger.client.model.ModelGameComponent;
import io.swagger.client.model.ModelGameDefinition;
import io.swagger.client.model.ModelJoinMatch;
import io.swagger.client.model.ModelLevelGroup;
import io.swagger.client.model.ModelMatch;
import io.swagger.client.model.ModelMatchMetric;
import io.swagger.client.model.ModelMatchParticipant;
import io.swagger.client.model.ModelMatchScore;
import io.swagger.client.model.ModelPageEventRequest;
import io.swagger.client.model.ModelPlayRequest;
import io.swagger.client.model.ModelScoreList;
import io.swagger.client.model.ModelStudentResponse;
import io.swagger.client.model.ModelUpdateLuchadorResponse;
import io.swagger.client.model.ModelUserDetails;
import io.swagger.client.model.ModelUserSetting;
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
     * find existing activities
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void assignmentidActivitiesPatchTest() throws ApiException {
        List<Integer> response = api.assignmentidActivitiesPatch();

        // TODO: test validations
    }
    /**
     * find existing activities
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void assignmentidStudentsPatchTest() throws ApiException {
        List<Integer> response = api.assignmentidStudentsPatch();

        // TODO: test validations
    }
    /**
     * find existing activities
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void dashboardActivityGetTest() throws ApiException {
        List<ModelActivity> response = api.dashboardActivityGet();

        // TODO: test validations
    }
    /**
     * find existing activities
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void dashboardAssignmentsGetTest() throws ApiException {
        List<ModelActivity> response = api.dashboardAssignmentsGet();

        // TODO: test validations
    }
    /**
     * find existing activities
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void dashboardAssignmentsPostTest() throws ApiException {
        List<ModelActivity> response = api.dashboardAssignmentsPost();

        // TODO: test validations
    }
    /**
     * find all Classroom
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void dashboardClassroomGetTest() throws ApiException {
        List<ModelClassroom> response = api.dashboardClassroomGet();

        // TODO: test validations
    }
    /**
     * add a Classroom
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void dashboardClassroomPostTest() throws ApiException {
        ModelClassroom body = null;
        ModelClassroom response = api.dashboardClassroomPost(body);

        // TODO: test validations
    }
    /**
     * find all Classroom students
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void dashboardClassroomStudentsIdGetTest() throws ApiException {
        Integer id = null;
        List<ModelStudentResponse> response = api.dashboardClassroomStudentsIdGet(id);

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
    public void dashboardGetUserGetTest() throws ApiException {
        ModelUserDetails response = api.dashboardGetUserGet();

        // TODO: test validations
    }
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
        ModelScoreList body = null;
        ModelMatchScore response = api.internalAddMatchScoresPost(body);

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
        ModelMatch body = null;
        ModelMatch response = api.internalEndMatchPut(body);

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
        ModelGameComponent body = null;
        ModelGameComponent response = api.internalGameComponentPost(body);

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
        ModelGameDefinition response = api.internalGameDefinitionIdIdGet(id);

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
        ModelGameDefinition response = api.internalGameDefinitionNameGet(name);

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
        ModelGameDefinition body = null;
        ModelGameDefinition response = api.internalGameDefinitionPost(body);

        // TODO: test validations
    }
    /**
     * update Game definition
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void internalGameDefinitionPutTest() throws ApiException {
        ModelGameDefinition body = null;
        ModelGameDefinition response = api.internalGameDefinitionPut(body);

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
        ModelFindLuchadorWithGamedefinition body = null;
        ModelGameComponent response = api.internalLuchadorPost(body);

        // TODO: test validations
    }
    /**
     * saves a match metric
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void internalMatchMetricPostTest() throws ApiException {
        ModelMatchMetric body = null;
        String response = api.internalMatchMetricPost(body);

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
        ModelMatchParticipant body = null;
        ModelMatchParticipant response = api.internalMatchParticipantPost(body);

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
        ModelMatch response = api.internalMatchSingleGet(matchID);

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
     * notify that the match is running, all participants joined
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void internalRunMatchPutTest() throws ApiException {
        ModelMatch body = null;
        ModelMatch response = api.internalRunMatchPut(body);

        // TODO: test validations
    }
    /**
     * find available matches by classroom
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateAvailableMatchClassroomIdGetTest() throws ApiException {
        Integer id = null;
        List<ModelAvailableMatch> response = api.privateAvailableMatchClassroomIdGet(id);

        // TODO: test validations
    }
    /**
     * find all public available matches
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateAvailableMatchPublicGetTest() throws ApiException {
        List<ModelAvailableMatch> response = api.privateAvailableMatchPublicGet();

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
        List<ModelGameDefinition> response = api.privateGameDefinitionAllGet();

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
    public void privateGameDefinitionIdIdGetTest() throws ApiException {
        Integer id = null;
        ModelGameDefinition response = api.privateGameDefinitionIdIdGet(id);

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
        ModelUserDetails response = api.privateGetUserGet();

        // TODO: test validations
    }
    /**
     * join a classroom
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateJoinClassroomAccessCodePostTest() throws ApiException {
        String accessCode = null;
        ModelClassroom response = api.privateJoinClassroomAccessCodePost(accessCode);

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
        ModelJoinMatch body = null;
        ModelMatch response = api.privateJoinMatchPost(body);

        // TODO: test validations
    }
    /**
     * Sends message to end active tutorial matches
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateLeaveTutorialMatchPostTest() throws ApiException {
        String response = api.privateLeaveTutorialMatchPost();

        // TODO: test validations
    }
    /**
     * find all level groups
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateLevelGroupGetTest() throws ApiException {
        List<ModelLevelGroup> response = api.privateLevelGroupGet();

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
        ModelGameComponent response = api.privateLuchadorGet();

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
        ModelGameComponent body = null;
        ModelUpdateLuchadorResponse response = api.privateLuchadorPut(body);

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
        List<ModelConfig> response = api.privateMaskConfigIdGet(id);

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
        List<ModelConfig> response = api.privateMaskRandomGet();

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
        List<ModelGameComponent> response = api.privateMatchConfigGet(matchID);

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
        List<ModelActiveMatch> response = api.privateMatchGet();

        // TODO: test validations
    }
    /**
     * find active multiplayer matches
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privateMatchMultiplayerGetTest() throws ApiException {
        List<ModelMatch> response = api.privateMatchMultiplayerGet();

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
    public void privateMatchSingleGetTest() throws ApiException {
        Integer matchID = null;
        ModelMatch response = api.privateMatchSingleGet(matchID);

        // TODO: test validations
    }
    /**
     * add page events
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privatePageEventsPostTest() throws ApiException {
        ModelPageEventRequest body = null;
        String response = api.privatePageEventsPost(body);

        // TODO: test validations
    }
    /**
     * request to play a match
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void privatePlayPostTest() throws ApiException {
        ModelPlayRequest body = null;
        ModelMatch response = api.privatePlayPost(body);

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
        List<ModelGameDefinition> response = api.privateTutorialGet();

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
        ModelUserSetting response = api.privateUserSettingGet();

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
        ModelUserSetting body = null;
        ModelUserSetting response = api.privateUserSettingPut(body);

        // TODO: test validations
    }
}
