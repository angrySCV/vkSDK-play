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
 * Message.java
 * vk-android-sdk
 *
 * Created by Babichev Vitaly on 19.01.14.
 * Copyright (c) 2014 VK. All rights reserved.
 */
package vk.model;


import com.fasterxml.jackson.databind.JsonNode;

/**
 * A message object describes a private message
 */
@SuppressWarnings("unused")
public class VKApiMessage extends VKApiModel implements Identifiable {

    /**
     * 	Message ID. (Not returned for forwarded messages), positive number
     */
    public int id;

    /**
     * For an incoming message, the user ID of the author. For an outgoing message, the user ID of the receiver.
     */
    public int user_id;

    /**
     * 	Date (in Unix time) when the message was sent.
     */
    public long date;

    /**
     * Message status (false — not read, true — read). (Not returned for forwarded messages.)
     */
    public boolean read_state;

    /**
     * Message type (false — received, true — sent). (Not returned for forwarded messages.)
     */
    public boolean out;

    /**
     * Title of message or chat.
     */
    public String title;

    /**
     * Body of the message.
     */
    public String body;

    /**
     * List of media-attachments;
     */
    public VKAttachments attachments = new VKAttachments();

    /**
     * Array of forwarded messages (if any).
     */
    public VKList<VKApiMessage> fwd_messages;

    /**
     *	Whether the message contains smiles (false — no, true — yes).
     */
    public boolean emoji;

    /**
     * Whether the message is deleted (false — no, true — yes).
     */
    public boolean deleted;

	public VKApiMessage(JsonNode from)
	{
		parse(from);
	}
    /**
     * Fills a Message instance from JsonNode.
     */
    public VKApiMessage parse(JsonNode source) {
        id = source.get("id").asInt();
        user_id = source.get("user_id").asInt();
        date = source.get("date").asLong();
        read_state = source.get("read_state").asBoolean();
        out = source.get("out").asBoolean();
        title = source.get("title").asText();
        body = source.get("body").asText();
//        attachments .fill(source.get("attachments"));
//        fwd_messages = new VKList<VKApiMessage>(source.optJsonNode("fwd_messages"), VKApiMessage.class);
        emoji = source.get("emoji").asBoolean();
        deleted = source.get("deleted").asBoolean();
        return this;
    }


    /**
     * Creates empty Country instance.
     */
    public VKApiMessage() {

    }

    @Override
    public int getId() {
        return id;
    }

    public int describeContents() {
        return 0;
    }


}
