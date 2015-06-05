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
 * Builds requests for API.wall part
 */
public class VKApiWall {
	public static final String EXTENDED = VKApiConst.EXTENDED;

	public F.Promise<WS.Response> get (String owner_id) {
		return get(owner_id, "0");
	}
	public F.Promise<WS.Response> get (String owner_id, String offset) {
		return WS.url(VKApiConst.API_URL+"wall.get")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("offset", offset)
				.setQueryParameter("count", "100")
				.get();
	}

	public F.Promise<WS.Response> search(String owner_id,String query) {
		return search(owner_id, query, "0");
	}
	public F.Promise<WS.Response> search(String owner_id,String query, String offset) {
		return WS.url(VKApiConst.API_URL+"wall.search")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("query", query)
				.setQueryParameter("offset", offset)
				.setQueryParameter("count", "100")
				.get();
	}
	public F.Promise<WS.Response> getById (String posts) {
		return WS.url(VKApiConst.API_URL+"wall.getById")
		.setQueryParameter("posts", posts)
				.get();
	}


	public F.Promise<WS.Response> post (String owner_id, String message) {
		return WS.url(VKApiConst.API_URL+"wall.post")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("message", message)
				.get();
	}

	public F.Promise<WS.Response> post (String owner_id, String message, Map<String,String> parameters) {
		WS.WSRequestHolder holder = WS.url(VKApiConst.API_URL+"wall.post")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("message", message);
		Util.mergeParams(holder, parameters);
				return holder.get();
	}

	public F.Promise<WS.Response> repost (String object, String message, String group_id) {
		return WS.url(VKApiConst.API_URL+"wall.repost")
				.setQueryParameter("object", object)
				.setQueryParameter("message", message)
				.setQueryParameter("group_id", group_id)
				.get();
	}

	public F.Promise<WS.Response> getReposts (String owner_id) {
		return WS.url(VKApiConst.API_URL+"wall.getReposts")
				.setQueryParameter("owner_id", owner_id)
				.get();
	}

	public F.Promise<WS.Response> edit (String owner_id, String post_id, String message) {
		return WS.url(VKApiConst.API_URL+"wall.edit")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("post_id", post_id)
				.setQueryParameter("message", message)
				.get();
	}

	public F.Promise<WS.Response> delete (String owner_id, String post_id) {
		return WS.url(VKApiConst.API_URL+"wall.delete")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("post_id", post_id)
				.get();
	}

	public F.Promise<WS.Response> restore (String owner_id, String post_id) {
		return WS.url(VKApiConst.API_URL+"wall.restore")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("post_id", post_id)
				.get();
	}

	public F.Promise<WS.Response> getComments (String owner_id, String post_id) {
		return getComments(owner_id, post_id, "0");
	}
	public F.Promise<WS.Response> getComments (String owner_id, String post_id, String offset) {
		return WS.url(VKApiConst.API_URL+"wall.getComments")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("post_id", post_id)
				.setQueryParameter("need_likes", "0")
				.setQueryParameter("count", "100")
				.setQueryParameter("offset", offset)
				.get();
	}

	public F.Promise<WS.Response> addComment (String owner_id, String post_id, String text) {
		return WS.url(VKApiConst.API_URL+"wall.addComment")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("post_id", post_id)
				.setQueryParameter("text", text)
				.get();
	}


	public F.Promise<WS.Response> addComment (String owner_id, String post_id, String text, Map<String,String> parameters) {
		 WS.WSRequestHolder holder = WS.url(VKApiConst.API_URL+"wall.addComment")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("post_id", post_id)
				.setQueryParameter("text", text);
		Util.mergeParams(holder, parameters);
		return holder.get();
	}

	public F.Promise<WS.Response> editComment (String owner_id, String comment_id, String message) {
		return WS.url(VKApiConst.API_URL+"wall.editComment")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("comment_id", comment_id)
				.setQueryParameter("message", message)
				.get();
	}

	public F.Promise<WS.Response> deleteComment (String owner_id, String comment_id) {
		return WS.url(VKApiConst.API_URL+"wall.deleteComment")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("comment_id", comment_id)
				.get();
	}

	public F.Promise<WS.Response> restoreComment (String owner_id, String comment_id) {
		return WS.url(VKApiConst.API_URL+"wall.restoreComment")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("comment_id", comment_id)
				.get();
	}

	public F.Promise<WS.Response> reportPost (String owner_id,String post_id, String reason) {
		return WS.url(VKApiConst.API_URL+"wall.reportPost")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("post_id", post_id)
				.setQueryParameter("reason", reason)
				.get();
	}

	public F.Promise<WS.Response> reportComment (String owner_id, String comment_id, String reason) {
		return WS.url(VKApiConst.API_URL+"wall.reportComment")
				.setQueryParameter("owner_id", owner_id)
				.setQueryParameter("comment_id", comment_id)
				.setQueryParameter("reason", reason)
				.get();
	}

}
