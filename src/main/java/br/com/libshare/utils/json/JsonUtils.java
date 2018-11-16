package br.com.libshare.utils.json;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import br.com.libshare.utils.StringUtils;
import br.com.libshare.utils.TimeUtils;

public class JsonUtils {
	public static String getString(JsonObject json, String property) {
		JsonElement element = getJsonElement(json, property);

		return element == null ? null : element.getAsString();
	}

	public static JsonElement getJsonElement(JsonObject json, String property){
		JsonElement jsonElement = null;
		if (json.has(property)) {
			JsonElement element = json.get(property);
			if (!element.isJsonNull()) {
				jsonElement = element; 
			}
		}

		return jsonElement;
	}

	public static JsonObject getJsonObject(JsonObject json, String property){
		return (JsonObject) getJsonElement(json, property);
	}

	public static JsonArray getJsonArray(JsonObject json, String property){
		return (JsonArray) getJsonElement(json, property);
	}

	public static int getInt(JsonObject json, String property) {
		JsonElement element = getJsonElement(json, property);

		return element == null ? 0 : element.getAsInt();
	}

	public static BigDecimal getBigDecimal(JsonObject json, String property) {
		JsonElement element = getJsonElement(json, property);

		return element == null ? null : element.getAsBigDecimal() ;
	}

	public static Double getDouble(JsonObject json, String property) {
		JsonElement element = getJsonElement(json, property);

		return element == null ? null : element.getAsDouble() ;
	}

	public static BigDecimal getBigDecimalOrZero(JsonObject json, String property) {
		JsonElement element = getJsonElement(json, property);

		return element == null ? BigDecimal.ZERO : element.getAsBigDecimal();
	}

	public static Timestamp getTimestamp(JsonObject json, String property) {
		Timestamp result = null;
		JsonElement elemen = json.get(property);

		if (elemen != null && !elemen.isJsonNull()) {
			String valueAsString = StringUtils.getEmptyAsNull(elemen.getAsString());

			if (valueAsString != null) {
				String[] arrValue = valueAsString.split("-"); 

				if(arrValue.length > 1){
					valueAsString = arrValue[0];
				}

				result = TimeUtils.buildTimestamp(valueAsString);
			}
		}

		return result;
	}

	public static boolean isEmpty(JsonObject object){
		boolean isEmpty = true;
		if(object != null){
			for (int i = 0; i < object.entrySet().size(); i++) {
				Entry<String, JsonElement> entry =  object.entrySet().iterator().next();
				
				if (entry.getKey() != null) {
					isEmpty = false;
					break;
				}
			}
		}

	    return isEmpty;
	}

	public static BigDecimal formatToBigDecimalOrZero(JsonObject json, String property) {
		BigDecimal value = formatToBigDecimal(json, property);

		return value == null ? BigDecimal.ZERO : value;
	}

	public static BigDecimal formatToBigDecimal(JsonObject json, String property) {
		JsonElement element = getJsonElement(json, property);

		BigDecimal jsonResponse = null;
		if (element != null) {
			jsonResponse = StringUtils.convertToBigDecimal(element.getAsString());
		}

		return jsonResponse;
	}
	
	public static JsonObject convertStringToJsonObject(String content) {
		
		if (StringUtils.getEmptyAsNull(content) != null) {
			try {
				return new JsonParser().parse(content).getAsJsonObject();
				
			} catch (Exception e) {
				IllegalArgumentException error = new IllegalArgumentException("Verifique se o formato do conteudo é Json.");
				error.initCause(e);
				throw error;
			}
		}
		
		return null;
	}
	
	public static List<JsonObject> getJsonObjectList(JsonArray jsonArray) throws Exception {
		List<JsonObject> result = new ArrayList<JsonObject>();

		Iterator<JsonElement> iterator = jsonArray.iterator();

		while (iterator.hasNext()) {
			JsonObject jsonObj = ((JsonObject) iterator.next());

			result.add(jsonObj);
		}

		return result;
	}

	public static Collection<BigDecimal> getChildrenAsBigDecimalCollection(JsonObject jsonObject, String property) throws Exception {
		JsonArray jsonArray = jsonObject.getAsJsonArray(property);

		if (jsonArray == null) {
			return Collections.emptyList();
		}

		Collection<BigDecimal> bigDecimalColl = new ArrayList<BigDecimal>();

		Iterator<JsonElement> iterator = jsonArray.iterator();

		while (iterator.hasNext()) {
			JsonPrimitive jsonObj = ((JsonPrimitive) iterator.next());

			try {
				BigDecimal numero = jsonObj.getAsBigDecimal();
				bigDecimalColl.add(numero);
			} catch (NumberFormatException ignored) {
			}
		}

		return bigDecimalColl;
	}
	
	public static Collection<BigDecimal> getChildrenAsBigDecimalCollection(JsonArray array, String propertyName) throws Exception {
		Collection<BigDecimal> list = new ArrayList<BigDecimal>();
		
		if (array != null && array.size() > 0) {
			for (Iterator<JsonElement> it = array.iterator(); it.hasNext();) {
				JsonElement element = it.next();
				
				if (element.isJsonObject()) {
					JsonObject object = element.getAsJsonObject();
					
					JsonPrimitive primitive = object.getAsJsonPrimitive(propertyName);
					
					if (primitive != null && primitive.isNumber()) {
						list.add(primitive.getAsBigDecimal());
					}
				}
			}
		}
		
		return list;
	}

	public static JsonArray convertStringToJsonArray(String content) {
		if (StringUtils.getEmptyAsNull(content) != null) {
			try {
				return new JsonParser().parse(content).getAsJsonArray();
			} catch (Exception e) {
				IllegalArgumentException error = new IllegalArgumentException("Verifique se o formato do conteudo é um Json Array = [{chave: value}].");
				error.initCause(e);
				throw error;
			}
		}

		return null;
	}

	public static JsonElement parserString(String content) {
		if (StringUtils.getEmptyAsNull(content) != null) {
			try {
				return new JsonParser().parse(content);
			} catch (Exception e) {
				IllegalArgumentException error = new IllegalArgumentException("Verifique se o formato do conteudo é um Json valido.");
				error.initCause(e);
				throw error;
			}
		}

		return null;
	}

	public static Boolean getBoolean(JsonObject json, String property) {
		boolean result = false;
		JsonElement element = getJsonElement(json, property);

		if (!StringUtils.isEmpty(element)) {
			if (element.getAsString().equals("true") || element.getAsString().equals("S")) {
				result = true;
			}
		}

		return result;
	}

	public static JsonObject buildJsonObjectFromResultSet(ResultSet rset) throws Exception {
		ResultSetMetaData rsmd = rset.getMetaData();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		JsonObject objJson = new JsonObject();

		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			String col = rsmd.getColumnLabel(i).toUpperCase();

			Class<?> type = Class.forName(rsmd.getColumnClassName(i));

			Object value = null;

			if (Number.class.isAssignableFrom(type)) {
				value = rset.getBigDecimal(i);
			} else if (java.util.Date.class.isAssignableFrom(type)) {
				if (rset.getTimestamp(i) != null) {
					value = sdf.format(rset.getTimestamp(i));
				}
			} else if (String.class.isAssignableFrom(type)) {
				value = rset.getString(i);
			} else {
				continue;
			}

			objJson.addProperty(col, value == null ? "" : value.toString());
		}

		return objJson;
	}
}