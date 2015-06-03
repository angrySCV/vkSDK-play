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
 * A school object describes a school.
 */
@SuppressWarnings("unused")
public class VKApiSchool extends VKApiModel implements Identifiable {

    /**
     * School ID, positive number
     */
    public int id;

    /**
     * ID of the country the school is located in, positive number
     */
    public int country_id;

    /**
     * ID of the city the school is located in, positive number
     */
    public int city_id;

    /**
     * School name
     */
    public String name;

    /**
     * Year the user started to study
     */
    public int year_from;

    /**
     * Year the user finished to study
     */
    public int year_to;

    /**
     * Graduation year
     */
    public int year_graduated;

    /**
     * School class letter
     */
    public String clazz;

    /**
     * Speciality
     */
    public String speciality;

	public VKApiSchool(JsonNode from)
	{
		parse(from);
	}
    /**
     * Fills a School instance from JsonNode.
     */
    public VKApiSchool parse(JsonNode from) {
        id = from.get("id").asInt();
        country_id = from.get("country_id").asInt();
        city_id = from.get("city_id").asInt();
        name = from.get("name").asText();
        year_from = from.get("year_from").asInt();
        year_to = from.get("year_to").asInt();
        year_graduated = from.get("year_graduated").asInt();
        clazz = from.get("class").asText();
        speciality = from.get("speciality").asText();
        return this;
    }



    /**
     * Creates empty School instance.
     */
    public VKApiSchool() {

    }

    @Override
    public int getId() {
        return id;
    }

    private String fullName;

    @Override
    public String toString() {
        if(fullName == null) {
            StringBuilder builder = new StringBuilder(name);
            if(year_graduated != 0) {
                builder.append(" \'");
                builder.append(String.format("%02d", year_graduated % 100));
            }
            if(year_from != 0 && year_to != 0) {
                builder.append(", ");
                builder.append(year_from);
                builder.append('-');
                builder.append(year_to);
            }
            if(clazz!=null) {
                builder.append('(');
                builder.append(clazz);
                builder.append(')');
            }
            if(speciality!=null) {
                builder.append(", ");
                builder.append(speciality);
            }
            fullName = builder.toString();
        }
        return fullName;
    }


    public int describeContents() {
        return 0;
    }



}
