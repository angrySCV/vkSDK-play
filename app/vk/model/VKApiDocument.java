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

import static vk.model.VKAttachments.TYPE_DOC;
import static vk.model.VKAttachments.VKApiAttachment;

/**
 * A document object describes a document file.
 */
@SuppressWarnings("unused")
public class VKApiDocument extends VKApiAttachment implements Identifiable {

    /**
     * Document ID.
     */
    public int id;

    /**
     * ID of the user or group who uploaded the document.
     */
    public int owner_id;

    /**
     * Document title.
     */
    public String title;

    /**
     * Document size (in bytes).
     */
    public long size;

    /**
     * Document extension.
     */
    public String ext;

    /**
     * Document URL for downloading.
     */
    public String url;

    /**
     * URL of the 100x75px image (if the file is graphical).
     */
    public String photo_100;

    /**
     * URL of the 130x100px image (if the file is graphical).
     */
    public String photo_130;

    /**
     * Array of all photos.
     */
    public VKPhotoSizes photo = new VKPhotoSizes();

    /**
     * An access key using for get information about hidden objects.
     */
    public String access_key;

    private boolean mIsGif;
    private boolean mIsImage;

	public VKApiDocument(JsonNode from)
	{
		parse(from);
	}
    /**
     * Fills a Doc instance from JsonNode.
     */
    public VKApiDocument parse(JsonNode jo) {
        id = jo.get("id").asInt();
        owner_id = jo.get("owner_id").asInt();
        title = jo.get("title").asText();
        size = jo.get("size").asLong();
        ext = jo.get("ext").asText();
        url = jo.get("url").asText();
        access_key = jo.get("access_key").asText();

        photo_100 = jo.get("photo_100").asText();
        if(photo_100!=null) {
            photo.add(VKApiPhotoSize.create(photo_100, 100, 75));
        }
        photo_130 = jo.get("photo_130").asText();
        if(photo_130!=null) {
            photo.add(VKApiPhotoSize.create(photo_130, 130, 100));
        }
        photo.sort();
        return this;
    }



    /**
     * Creates empty Doc instance.
     */
    public VKApiDocument() {

    }

    public boolean isImage() {
        mIsImage = mIsImage ||
                "jpg".equals(ext) ||
                "jpeg".equals(ext) ||
                "png".equals(ext) ||
                "bmp".equals(ext);
        return mIsImage;
    }

    public boolean isGif() {
        mIsGif = mIsGif || "gif".equals(ext);
        return mIsGif;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public CharSequence toAttachmentString() {
        StringBuilder result = new StringBuilder(TYPE_DOC).append(owner_id).append('_').append(id);
        if(access_key!=null) {
            result.append('_');
            result.append(access_key);
        }
        return result;
    }

    @Override
    public String getType() {
        return TYPE_DOC;
    }


    public int describeContents() {
        return 0;
    }

}
