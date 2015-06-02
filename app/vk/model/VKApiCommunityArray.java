package vk.model;

import org.json.JSONException;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Array of VKGroup
 * Created by alex_xpert on 28.01.14.
 */
public class VKApiCommunityArray extends VKList<VKApiCommunityFull> {
    @Override
    public VKApiModel parse(JsonNode response) throws JSONException {
        fill(response, VKApiCommunityFull.class);
        return this;
    }
}
