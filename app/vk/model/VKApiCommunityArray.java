package vk.model;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Array of VKGroup
 * Created by alex_xpert on 28.01.14.
 */
public class VKApiCommunityArray extends VKList<VKApiCommunityFull> {

    public VKApiModel parse(JsonNode response) {
//        fill(response, VKApiCommunityFull.class);
        return this;
    }
}
