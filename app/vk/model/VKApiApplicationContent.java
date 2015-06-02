/**
 * AppInfo.java
 * vk-android-sdk
 *
 * Created by Babichev Vitaly on 19.01.14.
 * Copyright (c) 2014 VK. All rights reserved.
 */
package vk.model;



import com.fasterxml.jackson.databind.JsonNode;
import static vk.model.VKAttachments.*;

/**
 * Describes information about application in the post.
 */
@SuppressWarnings("unused")
public class VKApiApplicationContent extends VKApiAttachment {

    /**
     * ID of the application that posted on the wall;
     */
    public int id;

    /**
     * Application name
     */
    public String name;

    /**
     * Image URL for preview with maximum width in 130px
     */
    public String photo_130;

    /**
     * Image URL for preview with maximum width in 130px
     */
    public String photo_604;

    /**
     * Image URL for preview;
     */
    public VKPhotoSizes photo = new VKPhotoSizes();

	public VKApiApplicationContent(JsonNode source) {
		parse(source);
	}
    /**
     * Fills an ApplicationContent instance from JsonNode.
     */
    public VKApiApplicationContent parse(JsonNode source) {
        id = source.get("id").asInt();
        name = source.get("name").asText();
        photo_130 = source.get("photo_130").asText();
        if(photo_130!=null) {
            photo.add(VKApiPhotoSize.create(photo_130, 130));
        }
        photo_604 = source.get("photo_604").asText();
        if(photo_604!=null) {
            photo.add(VKApiPhotoSize.create(photo_604, 604));
        }
        return this;
    }



    /**
     * Creates empty ApplicationContent instance.
     */
    public VKApiApplicationContent() {

    }

    @Override
    public CharSequence toAttachmentString() {
        throw new UnsupportedOperationException("Attaching app info is not supported by VK.com API");
    }

    @Override
    public String getType() {
        return TYPE_APP;
    }


    public int describeContents() {
        return 0;
    }

    @Override
    public int getId() {
        return id;
    }
}
