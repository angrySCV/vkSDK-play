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
 * Represents full user profile.
 */
@SuppressWarnings("unused")
public class VKApiUserFull extends VKApiUser {

    /**
     * Filed last_seen from VK fields set
     */
    public static final String LAST_SEEN = "last_seen";

    /**
     * Filed bdate from VK fields set
     */
    public static final String BDATE = "bdate";

    /**
     * Filed city from VK fields set
     */
    public static final String CITY = "city";

    /**
     * Filed country from VK fields set
     */
    public static final String COUNTRY = "country";

    /**
     * Filed universities from VK fields set
     */
    public static final String UNIVERSITIES = "universities";

    /**
     * Filed schools from VK fields set
     */
    public static final String SCHOOLS = "schools";

    /**
     * Filed activity from VK fields set
     */
    public static final String ACTIVITY = "activity";

    /**
     * Filed personal from VK fields set
     */
    public static final String PERSONAL = "personal";

    /**
     * Filed sex from VK fields set
     */
    public static final String SEX = "sex";

    /**
     * Filed site from VK fields set
     */
    public static final String SITE = "site";

    /**
     * Filed contacts from VK fields set
     */
    public static final String CONTACTS = "contacts";

    /**
     * Filed can_post from VK fields set
     */
    public static final String CAN_POST = "can_post";

    /**
     * Filed can_see_all_posts from VK fields set
     */
    public static final String CAN_SEE_ALL_POSTS = "can_see_all_posts";

    /**
     * Filed can_write_private_message from VK fields set
     */
    public static final String CAN_WRITE_PRIVATE_MESSAGE = "can_write_private_message";

    /**
     * Filed relation from VK fields set
     */
    public static final String RELATION = "relation";

    /**
     * Filed counters from VK fields set
     */
    public static final String COUNTERS = "counters";

    /**
     * Filed activities from VK fields set
     */
    public static final String ACTIVITIES = "activities";

    /**
     * Filed interests from VK fields set
     */
    public static final String INTERESTS = "interests";

    /**
     * Filed movies from VK fields set
     */
    public static final String MOVIES = "movies";

    /**
     * Filed tv from VK fields set
     */
    public static final String TV = "tv";

    /**
     * Filed books from VK fields set
     */
    public static final String BOOKS = "books";

    /**
     * Filed games from VK fields set
     */
    public static final String GAMES = "games";

    /**
     * Filed about from VK fields set
     */
    public static final String ABOUT = "about";

    /**
     * Filed quotes from VK fields set
     */
    public static final String QUOTES = "quotes";

    /**
     * Filed connections from VK fields set
     */
    public static final String CONNECTIONS = "connections";

    /**
     * Filed relatives from VK fields set
     */
    public static final String RELATIVES = "relatives";

    /**
     * Filed wall_default from VK fields set
     */
    public static final String WALL_DEFAULT = "wall_default";

    /**
     * Filed verified from VK fields set
     */
    public static final String VERIFIED = "verified";

    /**
     * Filed screen_name from VK fields set
     */
    public static final String SCREEN_NAME = "screen_name";

    /**
     * Filed blacklisted_by_me from VK fields set
     */
    public static final String BLACKLISTED_BY_ME = "blacklisted_by_me";

    /**
     * Text of user status.
     */
    public String activity;

    /**
     * Audio which broadcasting to status.
     */
    public VKApiAudio status_audio;

    /**
     * User's date of birth.  Returned as DD.MM.YYYY or DD.MM (if birth year is hidden).
     */
    public String bdate;

    /**
     * City specified on user's page in "Contacts" section.
     */
    public VKApiCity city;

    /**
     * Country specified on user's page in "Contacts" section.
     */
    public VKApiCountry country;

    /**
     * Last visit date(in Unix time).
     */
    public long last_seen;

    /**
     * List of user's universities
     */
    public VKList<VKApiUniversity> universities;

    /**
     * List of user's schools
     */
    public VKList<VKApiSchool> schools;

    /**
     * Views on smoking.
     * @see vk.model.VKApiUserFull.Attitude
     */
    public int smoking;

    /**
     * Views on alcohol.
     * @see vk.model.VKApiUserFull.Attitude
     */
    public int alcohol;

    /**
     * Views on policy.
     * @see vk.model.VKApiUserFull.Political
     */
    public int political;

    /**
     * Life main stuffs.
     * @see vk.model.VKApiUserFull.LifeMain
     */
    public int life_main;

    /**
     * People main stuffs.
     * @see vk.model.VKApiUserFull.PeopleMain
     */
    public int people_main;

    /**
     * Stuffs that inspire the user.
     */
    public String inspired_by;

    /**
     * List of user's languages
     */
    public String[] langs;

    /**
     * Religion of user
     */
    public String religion;

    /**
     * Name of user's account in Facebook
     */
    public String facebook;

    /**
     * ID of user's facebook
     */
    public String facebook_name;

    /**
     * Name of user's account in LiveJournal
     */
    public String livejournal;

    /**
     * Name of user's account in Skype
     */
    public String skype;

    /**
     * URL of user's site
     */
    public String site;

    /**
     * Name of user's account in Twitter
     */
    public String twitter;

    /**
     * Name of user's account in Instagram
     */
    public String instagram;

    /**
     * User's mobile phone number
     */
    public String mobile_phone;

    /**
     * User's home phone number
     */
    public String home_phone;

    /**
     * Page screen name.
     */
    public String screen_name;

    /**
     * Nickname of user.
     */
    public String nickname;

    /**
     * User's activities
     */
    public String activities;

    /**
     * User's interests
     */
    public String interests;

    /**
     * User's favorite movies
     */
    public String movies;

    /**
     * User's favorite TV Shows
     */
    public String tv;

    /**
     * User's favorite books
     */
    public String books;

    /**
     * User's favorite games
     */
    public String games;

    /**
     * User's about information
     */
    public String about;

    /**
     * User's favorite quotes
     */
    public String quotes;

    /**
     * Information whether others can posts on user's wall.
     */
    public boolean can_post;

    /**
     * Information whether others' posts on user's wall can be viewed
     */
    public boolean can_see_all_posts;

    /**
     * Information whether private messages can be sent to this user.
     */
    public boolean can_write_private_message;

    /**
     * Information whether user can comment wall posts.
     */
    public boolean wall_comments;

    /**
     * Information whether the user is banned in VK.
     */
    public boolean is_banned;

    /**
     * Information whether the user is deleted in VK.
     */
    public boolean is_deleted;

    /**
     * Information whether the user's post of wall shows by default.
     */
    public boolean wall_default_owner;

    /**
     * Information whether the user has a verified page in VK
     */
    public boolean verified;

    /**
     * User sex.
     * @see vk.model.VKApiUserFull.Sex
     */
    public int sex;

    /**
     * Set of user's counters.
     */
    public Counters counters;

    /**
     * Relationship status.
     * @see vk.model.VKApiUserFull.Relation
     */
    public int relation;

    /**
     * List of user's relatives
     */
    public VKList<Relative> relatives;

    /**
     * Information whether the current user has add this user to the blacklist.
     */
    public boolean blacklisted_by_me;

	public VKApiUserFull(JsonNode from)
	{
		parse(from);
	}
    public VKApiUserFull parse(JsonNode user) {
        super.parse(user);

        // general
        last_seen = user.get(LAST_SEEN).asLong();
        bdate = user.get(BDATE).asText();

        JsonNode city = user.get(CITY);
        if(city != null) {
            this.city = new VKApiCity().parse(city);
        }
        JsonNode country = user.get(COUNTRY);
        if(country != null) {
            this.country = new VKApiCountry().parse(country);
        }

        // education
//        universities = new VKList<VKApiUniversity>(user.get(UNIVERSITIES), VKApiUniversity.class);
//        schools = new VKList<VKApiSchool>(user.get(SCHOOLS), VKApiSchool.class);

        // status
        activity = user.get(ACTIVITY).asText();

        JsonNode status_audio = user.get("status_audio");
        if(status_audio != null) this.status_audio = new VKApiAudio().parse(status_audio);

        // personal views
        JsonNode personal = user.get(PERSONAL);
        if (personal != null) {
            smoking = personal.get("smoking").asInt();
            alcohol = personal.get("alcohol").asInt();
            political = personal.get("political").asInt();
            life_main = personal.get("life_main").asInt();
            people_main = personal.get("people_main").asInt();
            inspired_by = personal.get("inspired_by").asText();
            religion = personal.get("religion").asText();
            if (personal.has("langs")) {
                JsonNode langs = personal.get("langs");
                if (langs != null) {
                    this.langs = new String[langs.size()];
                    for (int i = 0; i < langs.size(); i++) {
                        this.langs[i] = langs.get(i).asText();
                    }
                }
            }
        }

        // contacts
        facebook = user.get("facebook").asText();
        facebook_name = user.get("facebook_name").asText();
        livejournal = user.get("livejournal").asText();
        site = user.get(SITE).asText();
        screen_name = user.get("screen_name").asText();
        skype = user.get("skype").asText();
        mobile_phone = user.get("mobile_phone").asText();
        home_phone = user.get("home_phone").asText();
        twitter = user.get("twitter").asText();
        instagram = user.get("instagram").asText();

        // personal info
        about = user.get(ABOUT).asText();
        activities = user.get(ACTIVITIES).asText();
        books = user.get(BOOKS).asText();
        games = user.get(GAMES).asText();
        interests = user.get(INTERESTS).asText();
        movies = user.get(MOVIES).asText();
        quotes = user.get(QUOTES).asText();
        tv = user.get(TV).asText();

        // settings
        nickname = user.get("nickname").asText();
        can_post = user.get(CAN_POST).asBoolean();
        can_see_all_posts = user.get(CAN_SEE_ALL_POSTS).asBoolean();
        blacklisted_by_me = user.get(BLACKLISTED_BY_ME).asBoolean();
        can_write_private_message = user.get(CAN_WRITE_PRIVATE_MESSAGE).asBoolean();
        wall_comments = user.get(WALL_DEFAULT).asBoolean();
        String deactivated = user.get("deactivated").asText();
        is_deleted = "deleted".equals(deactivated);
        is_banned = "banned".equals(deactivated);
        wall_default_owner = "owner".equals(user.get(WALL_DEFAULT).asText());
        verified = user.get(VERIFIED).asBoolean();

        // other
        sex = user.get(SEX).asInt();
        JsonNode counters = user.get(COUNTERS);
        if (counters != null) this.counters = new Counters(counters);

        relation = user.get(RELATION).asInt();

        if (user.has(RELATIVES)) {
            if (relatives == null) {
                relatives = new VKList<Relative>();
            }
//            relatives.fill(user.get(RELATIVES), Relative.class);
        }
        return this;
    }

    public static class Relative extends VKApiModel implements Identifiable {

        public int id;
        public String name;

        @Override
        public int getId() {
            return id;
        }

        public Relative parse(JsonNode response) {
            id = response.get("id").asInt();
            name = response.get("name").asText();
            return this;
        }


        public int describeContents() {
            return 0;
        }


    }

    public static class Counters {
        /**
         * Count was not in server response.
         */
        public final static int NO_COUNTER = -1;

        public int albums = NO_COUNTER;
        public int videos = NO_COUNTER;
        public int audios = NO_COUNTER;
        public int notes = NO_COUNTER;
        public int friends = NO_COUNTER;
        public int photos = NO_COUNTER;
        public int groups = NO_COUNTER;
        public int online_friends = NO_COUNTER;
        public int mutual_friends = NO_COUNTER;
        public int user_videos = NO_COUNTER;
        public int followers = NO_COUNTER;
        public int subscriptions = NO_COUNTER;
        public int pages = NO_COUNTER;

        Counters(JsonNode counters) {
            albums = counters.get("albums").asInt();
            audios = counters.get("audios").asInt();
            followers = counters.get("followers").asInt();
            photos = counters.get("photos").asInt();
            friends = counters.get("friends").asInt();
            groups = counters.get("groups").asInt();
            mutual_friends = counters.get("mutual_friends").asInt();
            notes = counters.get("notes").asInt();
            online_friends = counters.get("online_friends").asInt();
            user_videos = counters.get("user_videos").asInt();
            videos = counters.get("videos").asInt();
            subscriptions = counters.get("subscriptions").asInt();
            pages = counters.get("pages").asInt();
        }


        public int describeContents() {
            return 0;
        }


    }

    public static class Sex {
        private Sex() {
        }

        public static final int FEMALE = 1;
        public static final int MALE = 2;
    }

    public static class Relation {
        private Relation() {
        }

        public static final int SINGLE = 1;
        public static final int RELATIONSHIP = 2;
        public static final int ENGAGED = 3;
        public static final int MARRIED = 4;
        public static final int COMPLICATED = 5;
        public static final int SEARCHING = 6;
        public static final int IN_LOVE = 7;
    }

    public static class Attitude {
        private Attitude() {
        }

        public static final int VERY_NEGATIVE = 1;
        public static final int NEGATIVE = 2;
        public static final int COMPROMISABLE = 3;
        public static final int NEUTRAL = 4;
        public static final int POSITIVE = 5;
    }

    public static class Political {
        private Political() {
        }

        public static final int COMMUNNIST = 1;
        public static final int SOCIALIST = 2;
        public static final int CENTRIST = 3;
        public static final int LIBERAL = 4;
        public static final int CONSERVATIVE = 5;
        public static final int MONARCHIST = 6;
        public static final int ULTRACONSERVATIVE = 7;
        public static final int LIBERTARIAN = 8;
        public static final int APATHETIC = 9;
    }

    public static class LifeMain {
        private LifeMain() {
        }

        public static final int FAMILY_AND_CHILDREN = 1;
        public static final int CAREER_AND_MONEY = 2;
        public static final int ENTERTAINMENT_AND_LEISURE = 3;
        public static final int SCIENCE_AND_RESEARCH = 4;
        public static final int IMPROOVING_THE_WORLD = 5;
        public static final int PERSONAL_DEVELOPMENT = 6;
        public static final int BEAUTY_AND_ART = 7;
        public static final int FAME_AND_INFLUENCE = 8;
    }

    public static class PeopleMain {
        private PeopleMain() {
        }

        public static final int INTELLECT_AND_CREATIVITY = 1;
        public static final int KINDNESS_AND_HONESTLY = 2;
        public static final int HEALTH_AND_BEAUTY = 3;
        public static final int WEALTH_AND_POWER = 4;
        public static final int COURAGE_AND_PERSISTENCE = 5;
        public static final int HUMOR_AND_LOVE_FOR_LIFE = 6;
    }

    public static class RelativeType {
        private RelativeType() {
        }

        public static final String PARTNER = "partner";
        public static final String GRANDCHILD = "grandchild";
        public static final String GRANDPARENT = "grandparent";
        public static final String CHILD = "child";
        public static final String SUBLING = "sibling";
        public static final String PARENT = "parent";
    }

    public int describeContents() {
        return 0;
    }


    public VKApiUserFull() {}

}
