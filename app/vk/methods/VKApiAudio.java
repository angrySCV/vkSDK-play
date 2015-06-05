package vk.methods;

import play.libs.F;
import play.libs.WS;
import vk.VKApiConst;

import java.util.Map;

/**
 * Builds requests for API.audio part
 */
public class VKApiAudio  {
    /*
     * https://vk.com/dev/audio.get
     */
    public F.Promise<WS.Response> get() {
        return get(null);
    }

    /*
     * https://vk.com/dev/audio.get
     * значения входящих индитификаторов отрицательные для поиска музыки в группах, а для пользователей положительные
     */
    public F.Promise<WS.Response> get(String input) {
        return WS.url(VKApiConst.API_URL+"audio.get")
                .setQueryParameter("owner_id", input)
                .setQueryParameter("count", "6000") // больше 6000 тысяч результатов получить нельзя, даже с параметром оффсет поэтому оффсет не использую
                .get();
    }

    /*
     * https://vk.com/dev/audio.getById
     */
    public F.Promise<WS.Response> getById(String input) {
        return WS.url(VKApiConst.API_URL+"audio.getById")
                .setQueryParameter("audios", input)
                .get();
    }

    /*
     * https://vk.com/dev/audio.getLyrics
     */
    public F.Promise<WS.Response> getLyrics(String input) {
        return WS.url(VKApiConst.API_URL+"audio.getLyrics")
                .setQueryParameter("lyrics_id", input)
                .get();
    }

    /*
     * https://vk.com/dev/audio.search
     */
    public F.Promise<WS.Response> search(String input) {
        return WS.url(VKApiConst.API_URL+"audio.search")
                .setQueryParameter("q", input)
                .setQueryParameter("count", "300")
                .get();
    }


    /*
     * https://vk.com/dev/audio.getUploadServer
     * у метода нет параметров
     */
    public F.Promise<WS.Response> getUploadServer() {
        return WS.url(VKApiConst.API_URL+"audio.getUploadServer")
                .get();
    }

    /*
     * https://vk.com/dev/audio.save
     */
    public F.Promise<WS.Response> save(String input) {
        return WS.url(VKApiConst.API_URL+"audio.save")
                .setQueryParameter("server", input)
                .setQueryParameter("audio", input)
                .setQueryParameter("hash", input)
                .get();
    }

    /*
     * https://vk.com/dev/audio.add
     */
    public F.Promise<WS.Response> add(String audio_id, String owner) {
        return WS.url(VKApiConst.API_URL+"audio.add")
                .setQueryParameter("audio_id", audio_id)
                .setQueryParameter("owner_id", owner)
                .get();
    }

    /*
     * https://vk.com/dev/audio.delete
     */
    public F.Promise<WS.Response> delete(String audio_id, String owner) {
        return WS.url(VKApiConst.API_URL+"audio.delete")
                .setQueryParameter("audio_id", audio_id)
                .setQueryParameter("owner_id", owner)
                .get();
    }

    /*
     * https://vk.com/dev/audio.edit
     */
    public F.Promise<WS.Response> edit(String audio_id, String owner,  Map<String, String> params) {
        return WS.url(VKApiConst.API_URL+"audio.edit")
                .setQueryParameter("audio_id", audio_id)
                .setQueryParameter("owner_id", owner)
                .get();
    }

    /*
     * https://vk.com/dev/audio.reorder
     */
    public F.Promise<WS.Response> reorder(String input, String before) {
        return WS.url(VKApiConst.API_URL+"audio.reorder")
                .setQueryParameter("audio_id", input)
                .setQueryParameter("before", before)
                .get();
    }

    /*
     * https://vk.com/dev/audio.restore
     */
    public F.Promise<WS.Response> restore(String audio_id) {
        return WS.url(VKApiConst.API_URL+"audio.restore")
                .setQueryParameter("audio_id", audio_id)
                .get();
    }

    /*
     * https://vk.com/dev/audio.getAlbums
     */
    public F.Promise<WS.Response> getAlbums() {
        return getAlbums(null);
    }

    /*
     * https://vk.com/dev/audio.getAlbums
     */
    public F.Promise<WS.Response> getAlbums(String owner_id) {
        return getAlbums(owner_id, "0");
    }
    public F.Promise<WS.Response> getAlbums(String owner_id, String offset) {
        return WS.url(VKApiConst.API_URL+"audio.getAlbums")
                .setQueryParameter("owner_id", owner_id)
                .setQueryParameter("offset", offset)
                .setQueryParameter("count", "100")
                .get();
    }

    /*
     * https://vk.com/dev/audio.addAlbum
     */
    public F.Promise<WS.Response> addAlbum(String title) {
        return WS.url(VKApiConst.API_URL+"audio.addAlbum")
                .setQueryParameter("title", title)
                .get();
    }

    /*
     * https://vk.com/dev/audio.editAlbum
     */
    public F.Promise<WS.Response> editAlbum(String album_id, String title) {
        return WS.url(VKApiConst.API_URL+"audio.editAlbum")
                .setQueryParameter("album_id", album_id)
                .setQueryParameter("title", title)
                .get();
    }

    /*
     * https://vk.com/dev/audio.deleteAlbum
     */
    public F.Promise<WS.Response> deleteAlbum(String album_id) {
        return WS.url(VKApiConst.API_URL+"audio.deleteAlbum")
                .setQueryParameter("album_id", album_id)
                .get();
    }

    /*
     * https://vk.com/dev/audio.moveToAlbum
     */
    public F.Promise<WS.Response> moveToAlbum(String audio_ids, String album_id) {
        return WS.url(VKApiConst.API_URL+"audio.moveToAlbum")
                .setQueryParameter("audio_ids", audio_ids)
                .setQueryParameter("album_id", album_id)
                .get();
    }

    /*
     * https://vk.com/dev/audio.setBroadcast
     */
    public F.Promise<WS.Response> setBroadcast() {
        return WS.url(VKApiConst.API_URL+"audio.setBroadcast")
                .get();
    }

    /*
     * https://vk.com/dev/audio.getBroadcastList
     */

    public F.Promise<WS.Response> getBroadcastList() {
        return WS.url(VKApiConst.API_URL+"audio.getBroadcastList")
                .setQueryParameter("active", "1")
                .get();
    }

    /*
     * https://vk.com/dev/audio.getRecommendations
     */
    public F.Promise<WS.Response> getRecommendations() {
        return WS.url(VKApiConst.API_URL+"audio.getRecommendations")
                .setQueryParameter("count", "1000")
                .get();
    }

    /*
     * https://vk.com/dev/audio.getRecommendations
     */
    public F.Promise<WS.Response> getRecommendations(String user_id) {
        return getRecommendations(user_id, "0");
    }
    public F.Promise<WS.Response> getRecommendations(String user_id, String offset) {
        return WS.url(VKApiConst.API_URL+"audio.getRecommendations")
                .setQueryParameter("user_id", user_id)
                .setQueryParameter("count", offset)
                .setQueryParameter("count", "1000")
                .get();
    }

    /*
     * https://vk.com/dev/audio.getPopular
     */
    public F.Promise<WS.Response> getPopular(String genre_id) {
        return getPopular(genre_id, "0");
    }

    /*
     * https://vk.com/dev/audio.getPopular
     */
    public F.Promise<WS.Response> getPopular(String genre_id, String offset) {
        return WS.url(VKApiConst.API_URL+"audio.getPopular")
                .setQueryParameter("genre_id", genre_id)
                .setQueryParameter("offset", offset)
                .setQueryParameter("count", "1000")
                .get();
    }

    /*
     * https://vk.com/dev/audio.getCount
     */
    public F.Promise<WS.Response> getCount(String owner_id) {
        return WS.url(VKApiConst.API_URL+"audio.getCount")
                .setQueryParameter("owner_id", owner_id)
                .get();
    }
}
