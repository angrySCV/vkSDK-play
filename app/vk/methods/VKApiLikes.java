package vk.methods;

import play.libs.F;
import play.libs.WS;
import vk.VKApiConst;

/**
 * Created by AngrySCV on 05.06.15.
 * https://github.com/angrySCV
 */
public class VKApiLikes {


	/*
	* возможные типы объектов для лайка (object type:)
	* post — запись на стене пользователя или группы;
	* comment — комментарий к записи на стене;
	* photo — фотография;
	* audio — аудиозапись;
	* video — видеозапись;
	* note — заметка;
	* photo_comment — комментарий к фотографии;
	* video_comment — комментарий к видеозаписи;
	* topic_comment — комментарий в обсуждении;
	* sitepage — страница сайта, на котором установлен
	 */
	public F.Promise<WS.Response> getList (String owner_id, String item_id, String type) {
		return WS.url(VKApiConst.API_URL+"likes.getList")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("item_id", item_id)
				.setQueryParameter("type", type)
				.get();
	}


	public F.Promise<WS.Response> add (String owner_id, String item_id, String type) {
		return WS.url(VKApiConst.API_URL+"likes.add")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("item_id", item_id)
				.setQueryParameter("type", type)
				.get();
	}

	public F.Promise<WS.Response> delete (String owner_id, String item_id, String type) {
		return WS.url(VKApiConst.API_URL+"likes.delete")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("item_id", item_id)
				.setQueryParameter("type", type)
				.get();
	}

	public F.Promise<WS.Response> isLiked (String user_id, String owner_id, String item_id, String type) {
		return WS.url(VKApiConst.API_URL+"likes.isLiked")
				.setQueryParameter("user_id", user_id)
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("item_id", item_id)
				.setQueryParameter("type", type)
				.get();
	}
}
