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

import java.util.ArrayList;
import java.util.Map;

/**
 * Class for presenting VK SDK and VK API errors
 */
public class VKError {
	public static final int VK_API_ERROR = -101;
    /**
     * @deprecated Use {@link #VK_CANCELED} instead
     */
    public static final int VK_API_CANCELED = -102;
    public static final int VK_CANCELED = -102;

    /**
     * @deprecated Use {@link #VK_REQUEST_NOT_PREPARED} instead
     */
    public static final int VK_API_REQUEST_NOT_PREPARED = -103;
    public static final int VK_REQUEST_NOT_PREPARED = -103;

    /**
     * @deprecated Use {@link #VK_JSON_FAILED} instead
     */
    public static final int VK_API_JSON_FAILED = -104;
    public static final int VK_JSON_FAILED = -104;

    /**
     * @deprecated Use {@link #VK_REQUEST_HTTP_FAILED} instead
     */
    public static final int VK_API_REQUEST_HTTP_FAILED = -105;
    public static final int VK_REQUEST_HTTP_FAILED = -105;

    /**
     * Contains system HTTP error
     */
    public Exception httpError;
    /**
     * Describes API error
     */
    public VKError apiError;
    /**
     * Request which caused error
     */
//    F.Promise<WS.Response> request;
    /**
     * May contains such errors:<br/>
     * <b>HTTP status code</b> if HTTP error occured;<br/>
     * <b>VK_API_ERROR</b> if API error occured;<br/>
     * <b>VK_API_CANCELED</b> if request was canceled;<br/>
     * <b>VK_API_REQUEST_NOT_PREPARED</b> if error occured while preparing request;
     */
    public int errorCode;
    /**
     * API error message
     */
    public String errorMessage;
    /**
     * Reason for authorization fail
     */
    public String errorReason;
    /**
     * API parameters passed to request
     */
    public ArrayList<Map<String, String>> requestParams;
    /**
     * Captcha identifier for captcha-check
     */
    public String captchaSid;
    /**
     * Image for captcha-check
     */
    public String captchaImg;
    /**
     * Redirection address if validation check required
     */
    public String redirectUri;

    /**
     * Generate new error with code
     *
     * @param errorCode positive if it's an HTTP error. Negative if it's API or SDK error
     */
    public VKError(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Generate API error from JSON
     *
     * @param json Json description of VK API error
     */
    @SuppressWarnings("unchecked")
	public VKError(JsonNode json) {
        VKError internalError = new VKError(json.get(VKApiConst.ERROR_CODE));
        internalError.errorMessage = json.get(VKApiConst.ERROR_MSG).toString();


        this.errorCode = VK_API_ERROR;
        this.apiError = internalError;
    }

    private static final String FAIL = "fail";
    private static final String ERROR_REASON = "error_reason";
    private static final String ERROR_DESCRIPTION = "error_description";

    /**
     * Generate API error from HTTP-query
     *
     * @param queryParams key-value parameters
     */
    public VKError(Map<String, String> queryParams) {
        this.errorCode = VK_API_ERROR;
        this.errorReason = queryParams.get(ERROR_REASON);
        this.errorMessage = queryParams.get(ERROR_DESCRIPTION);
        if (queryParams.containsKey(FAIL)) {
            this.errorReason = "Action failed";
        }
        if (queryParams.containsKey("cancel")) {
            this.errorCode   = VK_CANCELED;
            this.errorReason = "User canceled request";
        }
    }

    private boolean processCommonError (final VKError error) {
        switch (errorCode) {
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
                return true;
        }
        return false;

    }


    private void appendFields(StringBuilder builder) {
        if (errorReason != null)
            builder.append(String.format("; %s", errorReason));
        if (errorMessage != null)
            builder.append(String.format("; %s", errorMessage));
    }

	@Override public String toString()
	{
		StringBuilder errorString = new StringBuilder("VKError (");
		switch (this.errorCode) {
			case VK_API_ERROR:
				errorString.append("API error");
                if (apiError != null) {
                    errorString.append(apiError.toString());
                }
				break;
			case VK_CANCELED:
				errorString.append("Canceled");
				break;
			case VK_REQUEST_NOT_PREPARED:
				errorString.append("Request wasn't prepared");
				break;
			case VK_JSON_FAILED:
				errorString.append("JSON failed");

				break;
			case VK_REQUEST_HTTP_FAILED:
				errorString.append("HTTP failed");
				break;

			default:
				errorString.append(String.format("code: %d; ", errorCode));
				break;
		}
        appendFields(errorString);
		errorString.append(")");
		return errorString.toString();
	}
}
