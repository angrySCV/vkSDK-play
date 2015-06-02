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

package vk;


import com.fasterxml.jackson.databind.JsonNode;
import play.Logger;
import vk.model.VKApiModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Class for execution API-requests.
 */
public class VKRequest {


	public enum VKProgressType {
		Download,
		Upload
	}

	public enum HttpMethod {
		GET,
		POST
	}

	/**
	 * Selected method name
	 */
	public final  String       methodName;
	/**
	 * HTTP method for loading
	 */
	public final  HttpMethod   httpMethod;
	/**
	 * Passed parameters for method
	 */
	private final VKParameters mMethodParameters;
	/**
	 * Method parametes with common parameters
	 */
	private       VKParameters mPreparedParameters;
	/**
	 * How much times request was loaded
	 */
	private       int          mAttemptsUsed;

	/**
	 * Requests that should be called after current request.
	 */
	private ArrayList<VKRequest>        mPostRequestsQueue;
	/**
	 * Class for model parsing
	 */
	private Class<? extends VKApiModel> mModelClass;


	/**
	 * Specify language for API request
	 */
	private String mPreferredLang;

	/**
	 * Looper which starts request
	 */
//	private Looper mLooper;

	/**
	 * Specify attempts for request loading if caused HTTP-error. 0 for infinite
	 */
	public int                       attempts;
	/**
	 * Use HTTPS requests (by default is YES). If http-request is impossible (user denied no https access), SDK will load https version
	 */
	public boolean                   secure;
	/**
	 * Sets current system language as default for API data
	 */
	public boolean                   useSystemLanguage;
	/**
	 * Set to false if you don't need automatic model parsing
	 */
	public boolean                   parseModel;
	/**
	 * Response for this request
	 */
	public WeakReference<VKResponse> response;

	/**
	 * @return Returns HTTP-method for current request
	 */
	public HttpMethod getHttpMethod () {
		return httpMethod;
	}

	/**
	 * @return Returns list of method parameters (without common parameters)
	 */
	public VKParameters getMethodParameters () {
		return mMethodParameters;
	}

	/**
	 * Creates new request with parameters. See documentation for methods here https://vk.com/dev/methods
	 *
	 * @param method API-method name, e.g. audio.get
	 */
	public VKRequest (String method) {
		this(method, null);
	}

	/**
	 * Creates new request with parameters. See documentation for methods here https://vk.com/dev/methods
	 *
	 * @param method     API-method name, e.g. audio.get
	 * @param parameters method parameters
	 */
	public VKRequest (String method, VKParameters parameters) {
		this(method, parameters, HttpMethod.GET);
	}

	/**
	 * Creates new request with parameters. See documentation for methods here https://vk.com/dev/methods
	 *
	 * @param method     API-method name, e.g. audio.get
	 * @param parameters method parameters
	 * @param httpMethod HTTP method for execution, e.g. GET, POST
	 */
	public VKRequest (String method, VKParameters parameters, HttpMethod httpMethod) {
		this.methodName = method;
		if (parameters == null) {
			parameters = new VKParameters();
		}
		this.mMethodParameters = new VKParameters(parameters);
		if (httpMethod == null)
			httpMethod = HttpMethod.GET;
		this.httpMethod = httpMethod;
		this.mAttemptsUsed = 0;

		this.secure = true;
		//By default there is 1 attempt for loading.
		this.attempts = 1;

		//If system language is not supported, we use english
		this.mPreferredLang = "en";
		//By default we use system language.
		this.useSystemLanguage = true;
	}

	/**
	 * Creates new request with parameters. See documentation for methods here https://vk.com/dev/methods
	 *
	 * @param method     API-method name, e.g. audio.get
	 * @param parameters method parameters
	 * @param httpMethod HTTP method for execution, e.g. GET, POST
	 * @param modelClass class for automatic parse
	 */
	public VKRequest (String method, VKParameters parameters, HttpMethod httpMethod,
	                  Class<? extends VKApiModel> modelClass) {
		this(method, parameters, httpMethod);
		setModelClass(modelClass);
	}


	private void addPostRequest (VKRequest postRequest) {
		if (mPostRequestsQueue == null) {
			mPostRequestsQueue = new ArrayList<VKRequest>();
		}
		mPostRequestsQueue.add(postRequest);
	}

	public VKParameters getPreparedParameters () {
		if (mPreparedParameters == null) {
			mPreparedParameters = new VKParameters(mMethodParameters);

			//Set current access token from SDK object
//            VKAccessToken token = VKSdk.getAccessToken();
//	        if (token != null)
//                mPreparedParameters.put(VKApiConst.ACCESS_TOKEN, token.accessToken);
//            if (!this.secure)
//	            if (token != null && (token.secret != null || token.httpsRequired)) {
//                    this.secure = true;
//                }
			//Set actual version of API
			mPreparedParameters.put(VKApiConst.VERSION, VKApiConst.API_VERSION);
			//Set preferred language for request
			mPreparedParameters.put(VKApiConst.LANG, getLang());

			if (this.secure) {
				//If request is secure, we need all urls as https
				mPreparedParameters.put(VKApiConst.HTTPS, "1");
			}
//            if (token != null && token.secret != null) {
//                //If it not, generate signature of request
//                String sig = generateSig(token);
//                mPreparedParameters.put(VKApiConst.SIG, sig);
//            }
			//From that moment you cannot modify parameters.
			//Specially for http loading
		}
		return mPreparedParameters;
	}


	/**
	 * Starts loading of prepared request. You can use it instead of executeWithResultBlock
	 */
	public void start () {

	}

	/**
	 * Repeats this request with initial parameters and blocks.
	 * Used attempts will be set to 0.
	 */
	public void repeat () {
		this.mAttemptsUsed = 0;
		this.mPreparedParameters = null;
//        this.mLoadingOperation   = null;
		start();
	}

	/**
	 * Cancel current request. Result will be not passed. errorBlock will be called with error code
	 */
	public void cancel () {
		provideError(new VKError(VKError.VK_CANCELED));
	}

	/**
	 * Method used for errors processing
	 *
	 * @param error error caused by this request
	 */
	private void provideError (final VKError error) {
		error.request = this;


	}

	/**
	 * Method used for response processing
	 *
	 * @param jsonResponse response from API
	 * @param parsedModel  model parsed from json
	 */
	private void provideResponse (final JsonNode jsonResponse, Object parsedModel) {

	}

	/**
	 * Adds additional parameter to that request
	 *
	 * @param key   parameter name
	 * @param value parameter value
	 */
	public void addExtraParameter (String key, Object value) {
		mMethodParameters.put(key, value);
	}

	/**
	 * Adds additional parameters to that request
	 *
	 * @param extraParameters parameters supposed to be added
	 */
	public void addExtraParameters (VKParameters extraParameters) {
		mMethodParameters.putAll(extraParameters);
	}


	private boolean processCommonError (final VKError error) {
		switch (error.errorCode) {
			case 1:
				Logger.info("Произошла неизвестная ошибка.");
				break;
			case 2:
				Logger.info("Приложение выключено.  ");
				break;
			case 3:
				Logger.info("Передан неизвестный метод. ");
				break;
			case 4:
				Logger.info("Неверная подпись.  ");
				break;
			case 5:
				Logger.info("Авторизация пользователя не удалась. Убедитесь, что Вы используете верную схему авторизации. Для работы с методами без префикса secure Вам нужно авторизовать пользователя одним из этих способов: http://vk.com/dev/auth_sites, http://vk.com/dev/auth_mobile.");
				break;
			case 6:
				Logger.info("Слишком много запросов в секунду. Задайте больший интервал между вызовами или используйте метод execute. Подробнее об ограничениях на частоту вызовов см. на странице vk.com/dev/api_requests.");
				break;
			case 7:
				Logger.info("Нет прав для выполнения этого действия. Проверьте, получены ли нужные права доступа при авторизации. Это можно сделать с помощью метода account.getAppPermissions.");
				break;
			case 8:
				Logger.info("Неверный запрос. Проверьте синтаксис запроса и список используемых параметров (его можно найти на странице с описанием метода).");
				break;
			case 9:
				Logger.info("Слишком много однотипных действий. Нужно сократить число однотипных обращений. Для более эффективной работы Вы можете использовать execute или JSONP.");
				break;
			case 10:
				Logger.info("Произошла внутренняя ошибка сервера. Попробуйте повторить запрос позже.");
				break;
			case 11:
				Logger.info("В тестовом режиме приложение должно быть выключено или пользователь должен быть залогинен. Выключите приложение в настройках vk.com/editapp?id={Ваш API_ID}");
				break;
			case 14:
				Logger.info("Требуется ввод кода с картинки (Captcha). Процесс обработки этой ошибки подробно описан на отдельной странице.");
				break;
			case 15:
				Logger.info("Доступ запрещён. Убедитесь, что Вы используете верные идентификаторы, и доступ к контенту для текущего пользователя есть в полной версии сайта.");
				break;
			case 16:
				Logger.info("Требуется выполнение запросов по протоколу HTTPS, т.к. пользователь включил настройку, требующую работу через безопасное соединение. Чтобы избежать появления такой ошибки, в Standalone-приложении Вы можете предварительно проверять состояние этой настройки у пользователя методом account.getInfo.");
				break;
			case 17:
				Logger.info("Требуется валидация пользователя. Убедитесь, что Вы не используете токен, полученный по схеме http://vk.com/dev/auth_mobile, для вызовов с сервера — это запрещено. Процесс валидации пользователя описан на отдельной странице.");
				break;
			case 20:
				Logger.info(" Данное действие запрещено для не Standalone приложений. Если ошибка возникает несмотря на то, что Ваше приложение имеет тип Standalone, убедитесь, что при авторизации Вы используете redirect_uri=oauth.vk.com/blank.html. Подробнее см. vk.com/dev/auth_mobile.");
				break;
			case 21:
				Logger.info("Данное действие разрешено только для Standalone и Open API приложений.");
				break;
			case 23:
				Logger.info("Метод был выключен. Все актуальные методы ВК API, которые доступны в настоящий момент, перечислены здесь: vk.com/dev/methods.");
				break;
			case 24:
				Logger.info("Требуется подтверждение со стороны пользователя. Процесс подтверждения действия пользователем описан на отдельной странице.");
				break;
			case 100:
				Logger.info("Один из необходимых параметров был не передан или неверен. Проверьте список требуемых параметров и их формат на странице с описанием метода.");
				break;
			case 101:
				Logger.info(" Неверный API ID приложения. Найдите приложение в списке администрируемых на странице http://vk.com/apps?act=settings и укажите в запросе верный API_ID (идентификатор приложения).");
				break;
			case 113:
				Logger.info("Неверный идентификатор пользователя. Убедитесь, что Вы используете верный идентификатор. Получить ID по короткому имени можно методом utils.resolveScreenName.");
				break;
			case 150:
				Logger.info("Неверный timestamp. Получить актуальное значение Вы можете методом utils.getServerTime.");
				break;
			case 200:
				Logger.info("Доступ к альбому запрещён. Убедитесь, что Вы используете верные идентификаторы (для пользователей owner_id положительный, для сообществ — отрицательный), и доступ к запрашиваемому контенту для текущего пользователя есть в полной версии сайта.");
				break;
			case 201:
				Logger.info("Доступ к аудио запрещён. Убедитесь, что Вы используете верные идентификаторы (для пользователей owner_id положительный, для сообществ — отрицательный), и доступ к запрашиваемому контенту для текущего пользователя есть в полной версии сайта.");
				break;
			case 203:
				Logger.info("Доступ к группе запрещён. Убедитесь, что текущий пользователь является участником или руководителем сообщества (для закрытых и частных групп и встреч).");
				break;
			case 300:
				Logger.info("Альбом переполнен. Перед продолжением работы нужно удалить лишние объекты из альбома или использовать другой альбом.");
				break;
			case 500:
				Logger.info(" Действие запрещено. Вы должны включить переводы голосов в настройках приложения. Проверьте настройки приложения: vk.com/editapp?id={Ваш API_ID}&section=payments");
				break;
			case 600:
				Logger.info("Нет прав на выполнение данных операций с рекламным кабинетом.");
				break;
			case 603:
				Logger.info(" Произошла ошибка при работе с рекламным кабинетом.");
				break;
			default:
				Logger.info("ошибка с не обрабатываемым кодом. ");
				return false;
		}
		return false;

	}

	private String getLang () {
		// наследие из приложения на андройде, на серваке язык можно и самому выставить.
		String result = mPreferredLang;
		result = "en";
		return result;
	}

	/**
	 * Sets preferred language for api results.
	 *
	 * @param lang Two letter language code. May be "ru", "en", "ua", "es", "fi", "de", "it"
	 */
	public void setPreferredLang (String lang) {
		useSystemLanguage = false;
		mPreferredLang = lang;
	}

	/**
	 * Sets class for parse object model
	 *
	 * @param modelClass Class extends VKApiModel
	 */
	public void setModelClass (Class<? extends VKApiModel> modelClass) {
		mModelClass = modelClass;
		if (mModelClass != null)
			parseModel = true;
	}
}
