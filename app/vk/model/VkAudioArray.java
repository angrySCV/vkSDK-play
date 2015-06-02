package vk.model;

import org.json.JSONException;
import com.fasterxml.jackson.databind.JsonNode;

public class VkAudioArray extends VKList<VKApiAudio> {
    @Override
    public VKApiModel parse(JsonNode response) throws JSONException {
        fill(response, VKApiAudio.class);
        return this;
    }
}
