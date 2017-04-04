/*
*  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.carbon.apimgt.core.workflow;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTP workflow response. Can be used to pass workflow complete redirections
 */
public class HttpWorkflowResponse extends AbstractWorkflowResponse {

    private String redirectUrl = "";
    private String redirectConfirmationMsg = "";
    private JSONObject jsonPayloadObj = new JSONObject();
    private Map<String, String> additionalParameters = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public String getJSONPayload() {
        if (additionalParameters != null && !additionalParameters.isEmpty()) {
            redirectUrl = redirectUrl.concat("?");
            for (Map.Entry<String, String> entry : additionalParameters.entrySet()) {
                redirectUrl = redirectUrl.concat(((entry.getKey().concat("=")).concat(entry.getValue())).concat("&"));
            }
            //remove tailing "&"
            redirectUrl = redirectUrl.substring(0, redirectUrl.length() - 1);
        }

        jsonPayloadObj.put("redirectUrl", redirectUrl);
        jsonPayloadObj.put("redirectConfirmationMsg", redirectConfirmationMsg);

        return jsonPayloadObj.toJSONString();
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getRedirectConfirmationMsg() {
        return redirectConfirmationMsg;
    }

    public void setRedirectConfirmationMsg(String redirectConfirmationMsg) {
        this.redirectConfirmationMsg = redirectConfirmationMsg;
    }

    @SuppressWarnings("unchecked")
    public void setAdditionalParameters(String paramName, String paramValue) {
        additionalParameters.put(paramName, paramValue);
    }

    public Map getAdditionalParameterss() {
        return additionalParameters;
    }    
}
