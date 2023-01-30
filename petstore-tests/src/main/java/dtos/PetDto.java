package dtos;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;

@Builder
@Getter
public class PetDto {
    public int id;
    public Category category;
    public String name;
    @Builder.Default
    public  ArrayList<String> photoUrls = new ArrayList<>();
    @Builder.Default
    public ArrayList<Tag> tags = new ArrayList<>();
    public String status;

    @Builder
    public static class Tag {
        public int id;
        public String name;
    }

    @Builder
    public static class Category {
        public int id;
        public String name;
    }

    public PetDto addPhotoUrls(String...urls) {
        getPhotoUrls().addAll(Arrays.asList(urls));
        return this;
    }
}
