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
import io.swagger.client.model.MainJoinMatch;
import io.swagger.client.model.MainLoginRequest;
import io.swagger.client.model.MainLoginResponse;
import io.swagger.client.model.MainLuchador;
import io.swagger.client.model.MainMatch;
import io.swagger.client.model.MainMatchParticipant;
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
     * find Luchador by ID
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void internalLuchadorGetTest() throws ApiException {
        Integer luchadorID = null;
        MainLuchador response = api.internalLuchadorGet(luchadorID);

        // TODO: test validations
    }
    /**
     * adds match participant
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
     * create Match
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void internalMatchPostTest() throws ApiException {
        MainMatch body = null;
        MainMatch response = api.internalMatchPost(body);

        // TODO: test validations
    }
    /**
     * join match
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
        MainLuchador response = api.privateLuchadorGet();

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
        MainLuchador body = null;
        MainLuchador response = api.privateLuchadorPut(body);

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
    /**
     * Logs the user
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void publicLoginPostTest() throws ApiException {
        MainLoginRequest body = null;
        MainLoginResponse response = api.publicLoginPost(body);

        // TODO: test validations
    }
}
