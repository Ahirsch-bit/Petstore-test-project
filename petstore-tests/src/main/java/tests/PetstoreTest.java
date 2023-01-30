package tests;

import dtos.PetDto;
import org.testng.annotations.Test;
import requests.PetRequest;
import utils.assertions.PetAssert;

public class PetstoreTest {

    @Test
    public void createPetTest() {
        PetDto myPet = PetDto.builder()
                .name("Milky")
                .status("Available")
                .category(PetDto.Category.builder().name("Husky").build())
                .build()
                .addPhotoUrls("www.milky.com","www.amazingpet.com");
        PetRequest postRequest = PetRequest.builder().petDto(myPet)
                .build();
        postRequest.post().sendRequest();
        PetAssert.assertThat(postRequest).assertHttpSuccess().assertResponse();
        PetRequest getRequest = PetRequest.builder()
                .petId(postRequest.getJsonResponse().getInt("id"))
                .build();
        getRequest.get().sendRequest();
        PetAssert.assertThat(getRequest)
                .assertHttpSuccess()
                .assertResponse(postRequest.getJsonResponse());
    }
}
