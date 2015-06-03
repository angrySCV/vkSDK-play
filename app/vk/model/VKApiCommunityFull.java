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

/**
 * Represents full community profile.
 */
@SuppressWarnings("unused")
public class VKApiCommunityFull extends VKApiCommunity {

    /**
     * Filed city from VK fields set
     */
    public final static String CITY = "city";

    /**
     * Filed country from VK fields set
     */
    public final static String COUNTRY = "country";

    /**
     * Filed place from VK fields set
     */
    public final static String PLACE = "place";

    /**
     * Filed description from VK fields set
     */
    public final static String DESCRIPTION = "description";

    /**
     * Filed wiki_page from VK fields set
     */
    public final static String WIKI_PAGE = "wiki_page";

    /**
     * Filed members_count from VK fields set
     */
    public final static String MEMBERS_COUNT = "members_count";

    /**
     * Filed counters from VK fields set
     */
    public final static String COUNTERS = "counters";

    /**
     * Filed start_date from VK fields set
     */
    public final static String START_DATE = "start_date";

    /**
     * Filed end_date from VK fields set
     */
    public final static String END_DATE = "end_date";

    /**
     * Filed can_post from VK fields set
     */
    public final static String CAN_POST = "can_post";

    /**
     * Filed can_see_all_posts from VK fields set
     */
    public final static String CAN_SEE_ALL_POSTS = "can_see_all_posts";

    /**
     * Filed status from VK fields set
     */
    public final static String STATUS = "status";

    /**
     * Filed contacts from VK fields set
     */
    public final static String CONTACTS = "contacts";

    /**
     * Filed links from VK fields set
     */
    public final static String LINKS = "links";

    /**
     * Filed fixed_post from VK fields set
     */
    public final static String FIXED_POST = "fixed_post";

    /**
     * Filed verified from VK fields set
     */
    public final static String VERIFIED = "verified";

    /**
     * Filed blacklisted from VK fields set
     */
    public final static String BLACKLISTED = "blacklisted";

    /**
     * Filed site from VK fields set
     */
    public final static String SITE = "site";

    /**
     * Filed activity from VK fields set
     */
    public final static String ACTIVITY = "activity";

    /**
     * City specified in information about community.
     */
    public VKApiCity city;

    /**
     * Country specified in information about community.
     */
    public VKApiCountry country;

    /**
     * Audio which broadcasting to status.
     */
    public VKApiAudio status_audio;

    /**
     * The location which specified in information about community
     */
    public VKApiPlace place;

    /**
     * Community description text.
     */
    public String description;

    /**
     * Name of the home wiki-page of the community.
     */
    public String wiki_page;

    /**
     * Number of community members.
     */
    public int members_count;

    /**
     * Counters object with community counters.
     */
    public Counters counters;

    /**
     * Returned only for meeting and contain start time of the meeting as unixtime.
     */
    public long start_date;

    /**
     * Returned only for meeting and contain end time of the meeting as unixtime.
     */
    public long end_date;

    /**
     * Whether the current user can post on the community's wall
     */
    public boolean can_post;

    /**
     * Whether others' posts on the community's wall can be viewed
     */
    public boolean can_see_all_posts;

    /**
     * Group status.
     */
    public String status;

    /**
     * Information from public page contact module.
     */
    public VKList<Contact> contacts;

    /**
     * Information from public page links module.
     */
    public VKList<Link> links;

    /**
     * ID of fixed post of this community.
     */
    public int fixed_post;

    /**
     * Information whether the community has a verified page in VK
     */
    public boolean verified;

    /**
     * URL of community site
     */
    public String site;

    /**
     * Information whether the current community has add current user to the blacklist.
     */
    public boolean blacklisted;

    public VKApiCommunityFull() {
        super();
    }

    public VKApiCommunityFull parse(JsonNode json) {
        super.parse(json);

        JsonNode city = json.get(CITY);
        if(city != null) {
            this.city = new VKApiCity().parse(city);
        }
        JsonNode country = json.get(COUNTRY);
        if(country != null) {
            this.country = new VKApiCountry().parse(country);
        }

        JsonNode place = json.get(PLACE);
        if(place != null) this.place = new VKApiPlace().parse(place);

        description = json.get(DESCRIPTION).asText();
        wiki_page = json.get(WIKI_PAGE).asText();
        members_count = json.get(MEMBERS_COUNT).asInt();

        JsonNode counters = json.get(COUNTERS);
        if(counters != null) this.counters = new Counters(place);

        start_date = json.get(START_DATE).asLong();
        end_date = json.get(END_DATE).asLong();
        can_post = json.get(CAN_POST).asBoolean();
        can_see_all_posts = json.get(CAN_SEE_ALL_POSTS).asBoolean();
        status = json.get(STATUS).asText();

        JsonNode status_audio = json.get("status_audio");
        if(status_audio != null) this.status_audio = new VKApiAudio().parse(status_audio);

//        contacts = new VKList<Contact>(Json.fromJson(json.get(CONTACTS), Contact.class));
//        links = new VKList<Link>(json.optJsonNode(LINKS), Link.class);
        fixed_post = json.get(FIXED_POST).asInt();
        verified = json.get(VERIFIED).asBoolean();
        blacklisted = json.get(VERIFIED).asBoolean();
        site = json.get(SITE).asText();
        return this;
    }



    public static class Counters {

        /**
         * Значение в том случае, если счетчик не был явно указан.
         */
        public final static int NO_COUNTER = -1;

        public int photos = NO_COUNTER;
        public int albums = NO_COUNTER;
        public int audios = NO_COUNTER;
        public int videos = NO_COUNTER;
        public int topics = NO_COUNTER;
        public int docs = NO_COUNTER;

        public Counters(JsonNode from) {
            photos = from.get("photos").asInt();
            albums = from.get("albums").asInt();
            audios = from.get("audios").asInt();
            videos = from.get("videos").asInt();
            topics = from.get("topics").asInt();
            docs = from.get("docs").asInt();
        }



        public int describeContents() {
            return 0;
        }


    }

    public static class Contact extends VKApiModel implements Identifiable {
        public int user_id;
        public VKApiUser user;
        public String email;
        public String desc;

        public Contact(JsonNode from) {
            parse(from);
        }

	    public Contact parse(JsonNode from) {
		    user_id = from.get("user_id").asInt();
		    desc = from.get("desc").asText();
		    email = from.get("email").asText();
		    return this;
	    }


        public int describeContents() {
            return 0;
        }





        @Override
        public int getId() {
            return user_id;
        }

        @Override
        public String toString() {
            if(user != null) {
                return user.toString();
            } else if(email != null) {
                return email;
            }
            return null;
        }
    }

    public static class Link extends VKApiModel implements Identifiable {

        public String url;
        public String name;
        public String desc;
        public VKPhotoSizes photo = new VKPhotoSizes();

        public Link(JsonNode from) {
            parse(from);
        }
	    public Link parse(JsonNode from) {
		    url = from.get("url").asText();
		    name = from.get("name").asText();
		    desc = from.get("desc").asText();

		    String photo_50 = from.get("photo_50").asText();
		    if(photo_50!=null) {
			    photo.add(VKApiPhotoSize.create(photo_50, 50));
		    }
		    String photo_100 = from.get("photo_100").asText();
		    if(photo_100!=null) {
			    photo.add(VKApiPhotoSize.create(photo_100, 100));
		    }
		    photo.sort();
		    return this;
	    }

        public int describeContents() {
            return 0;
        }


        @Override
        public int getId() {
            return 0;
        }
    }


    public int describeContents() {
        return 0;
    }


}
