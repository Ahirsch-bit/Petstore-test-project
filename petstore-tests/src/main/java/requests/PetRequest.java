package requests;

import com.google.gson.Gson;
import dtos.PetDto;
import lombok.Builder;
import lombok.Getter;
import requestutils.RestRequest;
@Getter@Builder
public class PetRequest extends RestRequest {

    private PetDto petDto;
    private int petId;
    @Override
    public String setPath() {
        String path = "/pet";
        if (petId!=0){
            path=path.concat("/"+petId);
        }
        return path;
    }

    @Override
    public String setDto() {
        if (petDto==null){
            return "";
        }
        Gson gson = new Gson();
        return gson.toJson(petDto);
    }
}
