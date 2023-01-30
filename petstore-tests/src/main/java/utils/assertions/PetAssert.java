package utils.assertions;

import org.assertj.core.api.AbstractAssert;
import org.json.JSONArray;
import org.json.JSONObject;
import requests.PetRequest;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;

public class PetAssert extends AbstractAssert<PetAssert,PetRequest> {

    protected PetAssert(PetRequest request) {
        super(request, PetAssert.class);
    }

    public static PetAssert assertThat(PetRequest request){
        return new PetAssert(request);
    }

    public PetAssert assertHttpCreated(){
        if(actual.getStatusCode()!=201){
            failWithActualExpectedAndMessage(actual.getStatusCode(),201,
                    "Expected Status code 201 CREATED, but received"+actual.getStatusCode());
        }
        return this;
    }

    public PetAssert assertHttpSuccess(){
        if(actual.getStatusCode()!=200){
            failWithActualExpectedAndMessage(actual.getStatusCode(),200,
                    "Expected Status code 200 SUCCESS, but received "+actual.getStatusCode());
        }
        return this;
    }

    public PetAssert assertResponse(){
        assertResponse(actual.getJsonResponse());
        return this;
    }

    public PetAssert assertResponse(JSONObject jsonResponse){
        if (jsonResponse.getInt("id")<=0){
            failWithMessage("PetId should be greater than 0.");
        }
        if(actual.getPetDto().getCategory()!=null){
            if(actual.getPetDto().getCategory().id !=
                    jsonResponse.getJSONObject("category").getInt("id")){
                failWithActualExpectedAndMessage(jsonResponse.getJSONObject("category").getInt("id"),
                        actual.getPetDto().getCategory().id,
                        "The Category ID sent does not match the response.");
            }
            if(!actual.getPetDto().getCategory().name.equals(
                    jsonResponse.getJSONObject("category").getString("name"))){
                failWithActualExpectedAndMessage(jsonResponse.getJSONObject("category").getString("name"),
                        actual.getPetDto().getCategory().name,
                        "The Category name sent does not match the response.");
            }
        }
        if (!actual.getPetDto().getName().equals(jsonResponse.getString("name"))){
            failWithActualExpectedAndMessage(jsonResponse.getString("name"),actual.getPetDto().getName(),
                    "The name on the request does not match the name on the response.");
        }
        ArrayList<String> responseUrls = new ArrayList<>();
        for (int i = 0; i < jsonResponse.getJSONArray("photoUrls").length(); i++) {
            responseUrls.add(jsonResponse.getJSONArray("photoUrls").getString(i));
        }
        if(!CollectionUtils.isEqualCollection(actual.getPetDto().photoUrls,responseUrls)){
            failWithActualExpectedAndMessage(new JSONArray(actual.getPetDto().getPhotoUrls()),
                    jsonResponse.getJSONArray("photoUrls"),
                    "Photo urls in the response do not match the urls in the request.");
        }
        if (actual.getPetDto().getStatus()!=null){
            if (!actual.getPetDto().getStatus().equals(jsonResponse.getString("status"))){
                failWithActualExpectedAndMessage(actual.getPetDto().getStatus(),
                        jsonResponse.getString("status"),
                        "Status in the request does not match the status in the response. ");
            }
        }
        return this;
    }

}
