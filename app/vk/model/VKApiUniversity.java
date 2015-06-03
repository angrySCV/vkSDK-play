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

package vk.model;





import com.fasterxml.jackson.databind.JsonNode;

/**
 * An university object describes an university.
 */
@SuppressWarnings("unused")
public class VKApiUniversity extends VKApiModel implements Identifiable {

    /**
     * University ID, positive number
     */
    public int id;

    /**
     * ID of the country the university is located in, positive number
     */
    public int country_id;

    /**
     * ID of the city the university is located in, positive number
     */
    public int city_id;

    /**
     * University name
     */
    public String name;

    /**
     * Faculty ID
     */
    public String faculty;

    /**
     * Faculty name
     */
    public String faculty_name;

    /**
     * University chair ID;
     */
    public int chair;

    /**
     * Chair name
     */
    public String chair_name;

    /**
     * Graduation year
     */
    public int graduation;

    /**
     * Form of education
     */
    public String education_form;

    /**
     * Status of education
     */
    public String education_status;

	public VKApiUniversity(JsonNode from)
	{
		parse(from);
	}
    /**
     * Fills a University instance from JsonNode.
     */
    public VKApiUniversity parse(JsonNode from) {
        id = from.get("id").asInt();
        country_id = from.get("country_id").asInt();
        city_id = from.get("city_id").asInt();
        name = from.get("name").asText();
        faculty = from.get("faculty").asText();
        faculty_name = from.get("faculty_name").asText();
        chair = from.get("chair").asInt();
        chair_name = from.get("chair_name").asText();
        graduation = from.get("graduation").asInt();
        education_form = from.get("education_form").asText();
        education_status = from.get("education_status").asText();
        return this;
    }



    /**
     * Creates empty University instance.
     */
    public VKApiUniversity() {

    }

    private String fullName;

    @Override
    public String toString() {
        if(fullName == null) {
            StringBuilder result = new StringBuilder(name);
            result.append(" \'");
            result.append(String.format("%02d", graduation % 100));
            if(faculty_name!=null) {
                result.append(", ");
                result.append(faculty_name);
            }
            if(chair_name!=null) {
                result.append(", ");
                result.append(chair_name);
            }
            fullName = result.toString();
        }
        return fullName;
    }

    @Override
    public int getId() {
        return id;
    }


    public int describeContents() {
        return 0;
    }



}
