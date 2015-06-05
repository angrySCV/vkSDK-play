

package vk.methods;

import play.libs.F;
import play.libs.WS;
import vk.VKApiConst;

/**
 * Contains single method for forcing captcha
 * Created by angrySCV on 01.06.15.
 */
public class VKApiCaptcha  {
    public F.Promise<WS.Response> force(String sid, String key) {
        return WS.url(VKApiConst.API_URL+"captcha.force")
                .setQueryParameter("captcha_sid", sid)
                .setQueryParameter("captcha_key", key)
                .get();
    }

}
