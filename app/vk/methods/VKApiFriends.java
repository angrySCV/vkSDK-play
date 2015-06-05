package vk.methods;


import play.libs.F;
import play.libs.WS;
import vk.VKApiConst;

/**
 * Section friends
 * Created by alex_xpert on 29.01.14.
 */
public class VKApiFriends {



	public F.Promise<WS.Response> get (String user_id) {
		return WS.url(VKApiConst.API_URL+"friends.get")
				.setQueryParameter("user_id", user_id)
				.setQueryParameter("fields", "nickname,domain,sex,bdate,city,country,photo_50,photo_100,photo_200_orig,has_mobile,contacts,education,relation,last_seen,can_write_private_message,can_see_all_posts,can_post,universities")
				.get();
	}

	public F.Promise<WS.Response> getOnline (String user_id) {
		return WS.url(VKApiConst.API_URL+"friends.getOnline")
				.setQueryParameter("user_id", user_id)
				.get();
	}

	public F.Promise<WS.Response> getMutual (String source_uid, String target_uid) {
		return WS.url(VKApiConst.API_URL+"friends.getMutual")
				.setQueryParameter("source_uid", source_uid)
				.setQueryParameter("source_uid", target_uid)
				.get();
	}

	public F.Promise<WS.Response> getRecent () {
		return WS.url(VKApiConst.API_URL+"friends.getRecent")
				.get();
	}

	public F.Promise<WS.Response> getRequests () {
		return WS.url(VKApiConst.API_URL+"friends.getRequests")
				.setQueryParameter("out", "1")
				.get();
	}

	public F.Promise<WS.Response> add (String user_id) {
		return add(user_id, "");
	}
	public F.Promise<WS.Response> add (String user_id,String text) {
		return WS.url(VKApiConst.API_URL+"friends.add")
				.setQueryParameter("user_id", user_id)
				.setQueryParameter("text", text)
				.get();
	}

	public F.Promise<WS.Response> edit (String user_id, String list_ids) {
		return WS.url(VKApiConst.API_URL+"friends.edit")
				.setQueryParameter("user_id", user_id)
				.setQueryParameter("list_ids", list_ids)
				.get();
	}

	public F.Promise<WS.Response> delete (String user_id) {
		return WS.url(VKApiConst.API_URL+"friends.delete")
				.setQueryParameter("user_id", user_id)
				.get();
	}

	public F.Promise<WS.Response> getLists (String user_id) {
		return WS.url(VKApiConst.API_URL+"friends.getLists")
				.setQueryParameter("user_id",user_id)
				.get();
	}

	public F.Promise<WS.Response> addList (String name) {
		return WS.url(VKApiConst.API_URL+"friends.addList")
				.setQueryParameter("name", name)
				.get();
	}
	public F.Promise<WS.Response> addList (String name, String user_ids) {
		return WS.url(VKApiConst.API_URL+"friends.addList")
				.setQueryParameter("name", name)
				.setQueryParameter("user_ids", user_ids)
				.get();
	}
	public F.Promise<WS.Response> editList (String list_id, String user_ids) {
		return WS.url(VKApiConst.API_URL+"friends.editList")
				.setQueryParameter("list_id", list_id)
				.setQueryParameter("user_ids", user_ids)
				.get();
	}

	public F.Promise<WS.Response> deleteList (String list_id) {
		return WS.url(VKApiConst.API_URL+"friends.deleteList")
				.setQueryParameter("list_id", list_id)
				.get();
	}

	public F.Promise<WS.Response> getAppUsers (String input) {
		return WS.url(VKApiConst.API_URL+"friends.getAppUsers")

				.get();
	}

	public F.Promise<WS.Response> getByPhones (String phones) {
		return WS.url(VKApiConst.API_URL+"friends.getByPhones")
				.setQueryParameter("phones", phones)
				.setQueryParameter("fields", "nickname,screen_name,sex,bdate,city,country,timezone,photo_50,photo_100,photo_200_orig,has_mobile,contacts,education,counters,relation,last_seen,status,can_write_private_message,can_see_all_posts,can_post,universities")
				.get();
	}

	public F.Promise<WS.Response> deleteAllRequests (String input) {
		return WS.url(VKApiConst.API_URL+"friends.deleteAllRequests")
				.get();
	}

	public F.Promise<WS.Response> getSuggestions (String input) {
		return WS.url(VKApiConst.API_URL+"friends.getSuggestions")
				.setQueryParameter("filter", "mutual")
				.setQueryParameter("count", "500")
				.setQueryParameter("fields", "nickname,screen_name,sex,bdate,city,country,timezone,photo_50,photo_100,photo_200_orig,has_mobile,contacts,education,counters,relation,last_seen,status,can_write_private_message,can_see_all_posts,can_post,universities")
				.get();
	}

	public F.Promise<WS.Response> areFriends (String user_ids) {
		return WS.url(VKApiConst.API_URL+"friends.areFriends")
				.setQueryParameter("user_ids", user_ids)
				.get();
	}

}
