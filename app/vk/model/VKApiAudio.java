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
 * Audio.java
 * vk-android-sdk
 *
 * Created by Babichev Vitaly on 19.01.14.
 * Copyright (c) 2014 VK. All rights reserved.
 */
package vk.model;



import com.fasterxml.jackson.databind.JsonNode;
import static vk.model.VKAttachments.*;

/**
 * An audio object describes an audio file and contains the following fields.
 */
@SuppressWarnings("unused")
public class VKApiAudio extends VKApiAttachment implements Identifiable {

    /**
     * Audio ID.
     */
    public int id;

    /**
     * Audio owner ID.
     */
    public int owner_id;

    /**
     * Artist name.
     */
    public String artist;

    /**
     * Audio file title.
     */
    public String title;

    /**
     * Duration (in seconds).
     */
    public int duration;

    /**
     * Link to mp3.
     */
    public String url;

    /**
     * ID of the lyrics (if available) of the audio file.
     */
    public int lyrics_id;

    /**
     * ID of the album containing the audio file (if assigned).
     */
    public int album_id;

    /**
     * Genre ID. See the list of audio genres.
     */
    public int genre;

    /**
     * An access key using for get information about hidden objects.
     */
    public String access_key;

	public VKApiAudio(JsonNode from) {
		parse(from);
	}
    /**
     * Fills an Audio instance from JsonNode.
     */
    public VKApiAudio parse(JsonNode from) {
        id = from.get("id").asInt();
        owner_id = from.get("owner_id").asInt();
        artist = from.get("artist").asText();
        title = from.get("title").asText();
        duration = from.get("duration").asInt();
        url = from.get("url").asText();
        lyrics_id = from.get("lyrics_id").asInt();
        album_id = from.get("album_id").asInt();
        genre = from.get("genre_id").asInt();
        access_key = from.get("access_key").asText();
        return this;
    }


    /**
     * Creates empty Audio instance.
     */
    public VKApiAudio() {

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public CharSequence toAttachmentString() {
        StringBuilder result = new StringBuilder(TYPE_AUDIO).append(owner_id).append('_').append(id);
        if(access_key!=null) {
            result.append('_');
            result.append(access_key);
        }
        return result;
    }

    @Override
    public String getType() {
        return TYPE_AUDIO;
    }

    public int describeContents() {
        return 0;
    }



    /**
     * Audio object genres.
     */
    public final static class Genre {

        private Genre() {}

        public final static int ROCK = 1;
        public final static int POP = 2;
        public final static int RAP_AND_HIPHOP = 3;
        public final static int EASY_LISTENING = 4;
        public final static int DANCE_AND_HOUSE = 5;
        public final static int INSTRUMENTAL = 6;
        public final static int METAL = 7;
        public final static int DUBSTEP = 8;
        public final static int JAZZ_AND_BLUES = 9;
        public final static int DRUM_AND_BASS = 10;
        public final static int  TRANCE = 11;
        public final static int CHANSON = 12;
        public final static int ETHNIC = 13;
        public final static int ACOUSTIC_AND_VOCAL = 14;
        public final static int REGGAE = 15;
        public final static int CLASSICAL = 16;
        public final static int INDIE_POP = 17;
        public final static int OTHER = 18;
        public final static int SPEECH = 19;
        public final static int ALTERNATIVE = 21;
        public final static int ELECTROPOP_AND_DISCO = 22;
    }

}
