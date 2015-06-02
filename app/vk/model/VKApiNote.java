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

/**
 * Note.java
 * vk-android-sdk
 *
 * Created by Babichev Vitaly on 19.01.14.
 * Copyright (c) 2014 VK. All rights reserved.
 */
package vk.model;



import org.json.JSONException;
import com.fasterxml.jackson.databind.JsonNode;
import static vk.model.VKAttachments.*;

/**
 * A note object describes a note.
 */
@SuppressWarnings("unused")
public class VKApiNote extends VKAttachments.VKApiAttachment implements Identifiable {

    /**
     * Note ID, positive number
     */
    public int id;

    /**
     * Note owner ID.
     */
    public int user_id;

    /**
     * Note title.
     */
    public String title;

    /**
     * Note text.
     */
    public String text;

    /**
     * Date (in Unix time) when the note was created.
     */
    public long date;

    /**
     * Number of comments.
     */
    public int comments;

    /**
     * Number of read comments (only if owner_id is the current user).
     */
    public int read_comments;

	public VKApiNote(JsonNode from) throws JSONException
	{
		parse(from);
	}
    /**
     * Fills a Note instance from JsonNode.
     */
    public VKApiNote parse(JsonNode source) {
        id = source.optInt("id");
        user_id = source.optInt("user_id");
        title = source.optString("title");
        text = source.optString("text");
        date = source.optLong("date");
        comments = source.optInt("comments");
        read_comments = source.optInt("read_comments");
        return this;
    }


    /**
     * Creates empty Note instance.
     */
    public VKApiNote() {

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public CharSequence toAttachmentString() {
        return new StringBuilder(TYPE_NOTE).append(user_id).append('_').append(id);
    }

    @Override
    public String getType() {
        return TYPE_NOTE;
    }


}
