package vk.model;


import com.fasterxml.jackson.databind.JsonNode;

public class VkAudioArray extends VKList<VKApiAudio> {

    public VKApiModel parse(JsonNode response)  {
        fill(response, VKApiAudio.class);
        return this;
    }
}
