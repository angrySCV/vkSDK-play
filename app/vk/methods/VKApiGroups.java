package vk.methods;

import play.libs.F;
import play.libs.WS;
import util.Util;
import vk.VKApiConst;

import java.util.Map;

/**
 * Section groups
 * Created by alex_xpert on 29.01.14.
 */
public class VKApiGroups {

	public F.Promise<WS.Response> isMember (String group_id, String user_ids) {
		return WS.url(VKApiConst.API_URL+"groups.isMember")
				.setQueryParameter("group_id", group_id)
				.setQueryParameter("user_ids", user_ids)
				.get();
	}

	public F.Promise<WS.Response> getById (String group_ids) {
		return WS.url(VKApiConst.API_URL+"groups.getById")
				.setQueryParameter("group_ids", group_ids)
				.setQueryParameter("fields", "city,country,place,description,members_count,can_see_all_posts,activity,contacts,fixed_post,site")
				.get();
	}

	public F.Promise<WS.Response> getUserGroupe (String user_id) {
		return WS.url(VKApiConst.API_URL+"groups.get")
				.setQueryParameter("user_id", user_id)
				.get();

	}

	public F.Promise<WS.Response> getMembers (String group_id) {
		return getMembers(group_id, "0");
	}

	public F.Promise<WS.Response> getMembers (String group_id, String offset) {
		return WS.url(VKApiConst.API_URL+"groups.getMembers")
				.setQueryParameter("group_id", group_id)
				.setQueryParameter("offset", offset)
				.get();
	}

	public F.Promise<WS.Response> join (String group_id) {
		return WS.url(VKApiConst.API_URL+"groups.join")
				.setQueryParameter("group_id", group_id)
				.get();
	}

	public F.Promise<WS.Response> leave (String group_id) {
		return WS.url(VKApiConst.API_URL+"groups.leave")
				.setQueryParameter("group_id", group_id)
				.get();
	}


	public F.Promise<WS.Response> search (String input) {
		return search(input, "0");
	}

	public F.Promise<WS.Response> search (String input, String offset) {
		return WS.url(VKApiConst.API_URL+"groups.search")
				.setQueryParameter("q", input)
				.setQueryParameter("offset", offset)
				.setQueryParameter("count", "1000")
				.get();
	}

	public F.Promise<WS.Response> search (String input, String offset, Map<String, String> parametrs) {
		WS.WSRequestHolder holder = WS.url(VKApiConst.API_URL+"groups.search")
				.setQueryParameter("q", input)
				.setQueryParameter("offset", offset)
				.setQueryParameter("count", "1000");
		Util.mergeParams(holder, parametrs);
		return holder.get();
	}


	public F.Promise<WS.Response> getInvites (String input) {
		return WS.url(VKApiConst.API_URL+"groups.getInvites")
				.get();
	}

	public F.Promise<WS.Response> banUser (String user_id, String group_id, Long end_date, String reason, String comment, Boolean comment_visible) {
		return WS.url(VKApiConst.API_URL+"groups.banUser")
				.setQueryParameter("user_id", user_id)
				.setQueryParameter("group_id", group_id)
				.setQueryParameter("end_date", end_date.toString())
				.setQueryParameter("comment", comment)
				.setQueryParameter("comment_visible", comment_visible.toString())
				.get();
	}

	public F.Promise<WS.Response> unbanUser (String user_id, String group_id) {
		return WS.url(VKApiConst.API_URL+"groups.unbanUser")
				.setQueryParameter("user_id", user_id)
				.setQueryParameter("group_id", group_id)
				.get();
	}

	public F.Promise<WS.Response> getBanned (String group_id) {
		return WS.url(VKApiConst.API_URL+"groups.getBanned")
				.setQueryParameter("group_id", group_id)
				.setQueryParameter("count", "200")
				.get();
	}

	public F.Promise<WS.Response> create (String title, String description, String type) {
		return WS.url(VKApiConst.API_URL+"groups.create")
				.setQueryParameter("title", title)
				.setQueryParameter("description", description)
				.setQueryParameter("type", type)
				.get();
	}

	public F.Promise<WS.Response> getRequests (String group_id) {
		return getRequests(group_id, "0");
	}

	public F.Promise<WS.Response> getRequests (String group_id, String offset) {
		return WS.url(VKApiConst.API_URL+"groups.getRequests")
				.setQueryParameter("group_id", group_id)
				.setQueryParameter("offset", offset)
				.setQueryParameter("count", "200")
				.setQueryParameter("count", "sex,bdate,city,country,photo_50,photo_100,photo_200_orig")
				.get();
	}

	public F.Promise<WS.Response> approveRequest (String user_id, String group_id) {
		return WS.url(VKApiConst.API_URL+"groups.approveRequest")
				.setQueryParameter("user_id", user_id)
				.setQueryParameter("group_id", group_id)
				.get();
	}

}
