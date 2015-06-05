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

package vk.methods;

import play.libs.F;
import play.libs.WS;
import util.Util;
import vk.VKApiConst;

import java.util.Map;

/**
 * Builds requests for API.users part
 */
public class VKApiUsers {
	/**
	 * Returns basic information about current user
	 *
	 * @return Request for load
	 */
	public F.Promise<WS.Response> get () {
		return get(null);
	}

	/**
	 * https://vk.com/dev/users.get
	 *
	 * @param user_ids use parameters from description with VKApiConst class
	 * @return Request for load
	 */
	public F.Promise<WS.Response> get (String user_ids) {
		return WS.url(VKApiConst.API_URL+"users.get")
				.setQueryParameter("user_ids", user_ids)
				.setQueryParameter("fields", "sex,bdate,city,country,photo_50,photo_100,photo_200,photo_400_orig,photo_max,photo_id,online,online_mobile,domain,has_mobile,contacts,connections,site,education,universities,schools,can_post,can_see_all_posts,can_see_audio,can_write_private_message,status,last_seen,common_count,relation,relatives,counters,screen_name,maiden_name,occupation,activities,interests,music,movies,tv,books,games")
				.get();
	}

	/**
	 * https://vk.com/dev/users.search
	 *
	 * @param search use parameters from description with Mapped parameters
	 * @return Request for load
	 */

	public F.Promise<WS.Response> search (String search) {
		return search(search, "0");
	}


	public F.Promise<WS.Response> search (String search, String offset) {
		return WS.url(VKApiConst.API_URL + "users.search")
				.setQueryParameter("q", search)
				.setQueryParameter("count", "1000")
				.setQueryParameter("offset", offset)
				.setQueryParameter("fields", "nickname,screen_name,sex,bdate,city,country,photo_50,photo_100,photo_200_orig")
				.get();
	}



	public F.Promise<WS.Response> search (String search, String offset, Map<String, String> params) {
		WS.WSRequestHolder holder = WS.url(VKApiConst.API_URL + "users.search")
				.setQueryParameter("q", search)
				.setQueryParameter("count", "1000")
				.setQueryParameter("offset", offset)
				.setQueryParameter("fields", "nickname,screen_name,sex,bdate,city,country,photo_50,photo_100,photo_200_orig");
		Util.mergeParams(holder, params);
		return holder.get();
	}

	/**
	 * https://vk.com/dev/users.isAppUser
	 *
	 * @return Request for load
	 */
	public F.Promise<WS.Response> isAppUser () {
		return WS.url(VKApiConst.API_URL+"users.isAppUser")
				.get();
	}

	/**
	 * https://vk.com/dev/users.isAppUser
	 *
	 * @param user_id ID of user to check
	 * @return Request for load
	 */
	public F.Promise<WS.Response> isAppUser (final Integer user_id) {
		return WS.url(VKApiConst.API_URL+"users.isAppUser")
				.setQueryParameter("user_id", user_id.toString())
				.setQueryParameter("count", "1000")
				.get();
	}

	/**
	 * https://vk.com/dev/users.getSubscriptions
	 *
	 * @return Request for load
	 */
	public F.Promise<WS.Response> getSubscriptions () {
		return getSubscriptions(null);
	}

	/**
	 * https://vk.com/dev/users.getSubscriptions
	 *
	 * @param user_id params use parameters from description with VKApiConst class
	 * @return Request for load
	 */
	public F.Promise<WS.Response> getSubscriptions (String user_id) {
		return WS.url(VKApiConst.API_URL+"users.getSubscriptions")
				.setQueryParameter("user_id", user_id)
				.get();
	}

	/**
	 * https://vk.com/dev/users.getFollowers
	 *
	 * @return Request for load
	 */
	public F.Promise<WS.Response> getFollowers () {
		return getFollowers(null);
	}

	/**
	 * https://vk.com/dev/users.getFollowers
	 *
	 * @param user_id use parameters from description with VKApiConst class
	 * @return Request for load
	 */
	public F.Promise<WS.Response> getFollowers (String user_id) {
		return WS.url(VKApiConst.API_URL+"users.getFollowers")
				.setQueryParameter("user_id", user_id)
				.setQueryParameter("count", "1000")
				.get();
	}

	/**
	 * https://vk.com/dev/users.report
	 * Created on 29.01.14.
	 *
	 * @param user_id use parameters from description with VKApiConst class
	 * @return Request for load
	 */
	public F.Promise<WS.Response> report (String user_id, String type, String comment) {
		return WS.url(VKApiConst.API_URL+"users.report")
				.setQueryParameter("user_id", user_id)
				.setQueryParameter("type", type)
				.setQueryParameter("comment", comment)
				.get();
	}

}
