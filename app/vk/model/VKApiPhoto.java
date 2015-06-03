//
//  Copyright (c) 2014 VK.com
//
//  Permission is hereby granted, free of charge, to any person obtaining a copy of
//  this software and associated documentation files (the "Software"), to deal in
//  the Software without restriction, including without limitation the rights to
//  use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
//  the Software, and to permit persons to whom the Software is furnished to do so,
//  subject to the following conditions:
//
//  The above copyright notice and this permission notice shall be included in all
//  copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
//  FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
//  COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
//  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
//  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//

package vk.model;


import com.fasterxml.jackson.databind.JsonNode;

import static vk.model.VKAttachments.TYPE_PHOTO;

/**
 * Describes a photo object from VK.
 */
public class VKApiPhoto extends VKAttachments.VKApiAttachment implements Identifiable {

    /**
     * Photo ID, positive number
     */
    public int id;

    /**
     * Photo album ID.
     */
    public int album_id;

    /**
     * ID of the user or community that owns the photo.
     */
    public int owner_id;

    /**
     * Width (in pixels) of the original photo.
     */
    public int width;

    /**
     * Height (in pixels) of the original photo.
     */
    public int height;

    /**
     * Text describing the photo.
     */
    public String text;

    /**
     * Date (in Unix time) the photo was added.
     */
    public long date;

    /**
     * URL of image with maximum size 75x75px.
     */
    public String photo_75;

    /**
     * URL of image with maximum size 130x130px.
     */
    public String photo_130;

    /**
     * URL of image with maximum size 604x604px.
     */
    public String photo_604;

    /**
     * URL of image with maximum size 807x807px.
     */
    public String photo_807;

    /**
     * URL of image with maximum size 1280x1024px.
     */
    public String photo_1280;

    /**
     * URL of image with maximum size 2560x2048px.
     */
    public String photo_2560;

    /**
     * All photo thumbs in photo sizes.
     * It has data even if server returned them without {@code PhotoSizes} format.
     */
    public VKPhotoSizes src = new VKPhotoSizes();

    /**
     * Information whether the current user liked the photo.
     */
    public boolean user_likes;

    /**
     * Whether the current user can comment on the photo
     */
    public boolean can_comment;

    /**
     * Number of likes on the photo.
     */
    public int likes;

    /**
     * Number of comments on the photo.
     */
    public int comments;

    /**
     * Number of tags on the photo.
     */
    public int tags;

    /**
     * An access key using for get information about hidden objects.
     */
    public String access_key;

	public VKApiPhoto(JsonNode from)
	{
		parse(from);
	}
    /**
     * Fills a Photo instance from JsonNode.
     */
    public VKApiPhoto parse(JsonNode from) {
        album_id = from.get("album_id").asInt();
        date = from.get("date").asLong();
        height = from.get("height").asInt();
        width = from.get("width").asInt();
        owner_id = from.get("owner_id").asInt();
        id = from.get("id").asInt();
        text = from.get("text").asText();
        access_key = from.get("access_key").asText();

        photo_75 = from.get("photo_75").asText();
        photo_130 = from.get("photo_130").asText();
        photo_604 = from.get("photo_604").asText();
        photo_807 = from.get("photo_807").asText();
        photo_1280 = from.get("photo_1280").asText();
        photo_2560 = from.get("photo_2560").asText();

        JsonNode likes = from.get("likes");
        this.likes = likes.get("count").asInt();
        this.user_likes = likes.get("user_likes").asBoolean();
        comments = from.get("comments").get("count").asInt();
        tags = from.get("tags").get("count").asInt();
        can_comment = from.get("can_comment").asBoolean();

        src.setOriginalDimension(width, height);
        JsonNode photo_sizes = from.get("sizes");
        if(photo_sizes != null) {
            src.fill(photo_sizes);
        } else {
            if(photo_75!=null) {
                src.add(VKApiPhotoSize.create(photo_75, VKApiPhotoSize.S, width, height));
            }
            if(photo_130!=null) {
                src.add(VKApiPhotoSize.create(photo_130, VKApiPhotoSize.M, width, height));
            }
            if(photo_604!=null) {
                src.add(VKApiPhotoSize.create(photo_604, VKApiPhotoSize.X, width, height));
            }
            if(photo_807!=null) {
                src.add(VKApiPhotoSize.create(photo_807, VKApiPhotoSize.Y, width, height));
            }
            if(photo_1280!=null) {
                src.add(VKApiPhotoSize.create(photo_1280, VKApiPhotoSize.Z, width, height));
            }
            if(photo_2560!=null) {
                src.add(VKApiPhotoSize.create(photo_2560, VKApiPhotoSize.W, width, height));
            }
            src.sort();
        }
        return this;
    }



    /**
     * Init photo object with attachment string like photo45898586_334180483
     * @param photoAttachmentString string of format photo[OWNER_ID]_[PHOTO_ID]
     */
    public VKApiPhoto(String photoAttachmentString) {
        if (photoAttachmentString.startsWith(TYPE_PHOTO)) {
            photoAttachmentString = photoAttachmentString.substring(TYPE_PHOTO.length());
            String[] ids  = photoAttachmentString.split("_");
            this.owner_id = Integer.parseInt(ids[0]);
            this.id       = Integer.parseInt(ids[1]);
        }
    }

    /**
     * Creates empty Photo instance.
     */
    public VKApiPhoto() {

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public CharSequence toAttachmentString() {
        StringBuilder result = new StringBuilder(TYPE_PHOTO).append(owner_id).append('_').append(id);
        if(access_key!=null) {
            result.append('_');
            result.append(access_key);
        }
        return result;
    }

    @Override
    public String getType() {
        return TYPE_PHOTO;
    }

    public int describeContents() {
        return 0;
    }



}
