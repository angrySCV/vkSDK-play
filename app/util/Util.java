package util;

import play.libs.WS;

import java.util.Map;

/**
 * Created by AngrySCV on 04.06.15.
 * https://github.com/angrySCV
 */
public class Util {
	public static void mergeParams (WS.WSRequestHolder holder, Map<String, String> parametrs) {
		if (parametrs != null) {
			parametrs.forEach((k, v) -> holder.setQueryParameter(k, v));
		}
	}
}
