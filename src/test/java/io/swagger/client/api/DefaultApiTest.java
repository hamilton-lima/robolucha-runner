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
import io.swagger.client.model.MainLoginRequest;
import io.swagger.client.model.MainLoginResponse;
import io.swagger.client.model.MainMatch;
import io.swagger.client.model.MainUserSetting;
import org.junit.Ignore;
import org.junit.Test;

/**
 * API tests for DefaultApi
 */
@Ignore
public class DefaultApiTest {

    private final DefaultApi api = new DefaultApi();

    
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
        MainMatch response = api.internalMatchPost();

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
        MainUserSetting request = null;
        MainUserSetting response = api.privateUserSettingPut(request);

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
        MainLoginRequest request = null;
        MainLoginResponse response = api.publicLoginPost(request);

        // TODO: test validations
    }
    
}
