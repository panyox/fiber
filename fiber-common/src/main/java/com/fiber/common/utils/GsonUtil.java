package com.fiber.common.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author panyox
 */
public class GsonUtil {

    public static final GsonUtil INSTANCE = new GsonUtil();

    public static final Gson GSON = new GsonBuilder().serializeNulls().create();

    private static final Gson GSON_MAP = new GsonBuilder().serializeNulls().registerTypeHierarchyAdapter(new TypeToken<Map<String, Object>>() {
    }.getRawType(), new MapDeserializer<String, Object>()).create();

    private static final String DOT = ".";

    private static final String E = "e";

    public static GsonUtil getInstance() {
        return INSTANCE;
    }

    public String toJson(Object data) {
        return GSON.toJson(data);
    }

    public Map<String, Object> toObjectMap(final String json) {
        return GSON_MAP.fromJson(json, new TypeToken<LinkedHashMap<String, Object>>() {
        }.getType());
    }

    public <T> Map<String, T> toObjectMap(final String json, final Class<T> clazz) {
        return GSON.fromJson(json, TypeToken.getParameterized(Map.class, String.class, clazz).getType());
    }

    public <T> Map<String, List<T>> toObjectMapList(final String json, final Class<T> clazz) {
        return GSON.fromJson(json, TypeToken.getParameterized(Map.class, String.class, TypeToken.getParameterized(List.class, clazz).getType()).getType());
    }

    public <T> List<T> fromList(final String json, final Class<T> clazz) {
        return GSON.fromJson(json, TypeToken.getParameterized(List.class, clazz).getType());
    }

    public Map<String, Object> convertToMap(final String json) {
        Map<String, Object> map = GSON_MAP.fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());

        if (map == null || map.isEmpty()) {
            return map;
        }

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                String valueStr = ((String) value).trim();
                if (valueStr.startsWith("{") && valueStr.endsWith("}")) {
                    Map<String, Object> mv = convertToMap(value.toString());
                    map.put(key, mv);
                }
            } else if (value instanceof JsonObject) {
                map.put(key, convertToMap(value.toString()));
            } else if (value instanceof JsonArray) {
                JsonArray jsonArray = (JsonArray) value;
                map.put(key, jsonArrayToListInConvertToMap(jsonArray));
            } else if (value instanceof JsonNull) {
                map.put(key, null);
            }
        }

        return map;
    }

    private List<Object> jsonArrayToListInConvertToMap(final JsonArray jsonArray) {
        List<Object> list = new ArrayList<>(jsonArray.size());
        for (JsonElement jsonElement : jsonArray) {
            String objStr = jsonElement.getAsString();
            if (objStr.startsWith("{") && objStr.endsWith("}")) {
                list.add(convertToMap(jsonElement.toString()));
            } else {
                list.add(objStr);
            }
        }

        return list;
    }

    private static class MapDeserializer<T, U> implements JsonDeserializer<Map<T, U>> {
        @SuppressWarnings("unchecked")
        @SneakyThrows
        @Override
        public Map<T, U> deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context) {
            if (!json.isJsonObject()) {
                return null;
            }

            JsonObject jsonObject = json.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> jsonEntrySet = jsonObject.entrySet();

            String className = ((ParameterizedType) type).getRawType().getTypeName();
            Class<Map<?, ?>> mapClass = (Class<Map<?, ?>>) Class.forName(className);

            Map<T, U> resultMap;
            if (mapClass.isInterface()) {
                resultMap = new LinkedHashMap<>();
            } else {
                resultMap = (Map<T, U>) mapClass.getConstructor().newInstance();
            }

            for (Map.Entry<String, JsonElement> entry : jsonEntrySet) {
                if (entry.getValue().isJsonNull()) {
                    resultMap.put((T) entry.getKey(), null);
                } else {
                    U value = context.deserialize(entry.getValue(), this.getType(entry.getValue()));
                    resultMap.put((T) entry.getKey(), value);
                }
            }

            return resultMap;
        }

        public Class<?> getType(final JsonElement element) {
            if (!element.isJsonPrimitive()) {
                return element.getClass();
            }

            final JsonPrimitive primitive = element.getAsJsonPrimitive();
            if (primitive.isString()) {
                return String.class;
            }
            if (primitive.isNumber()) {
                String numStr = primitive.getAsString();
                if (numStr.contains(DOT) || numStr.contains(E)
                        || numStr.contains("E")) {
                    return Double.class;
                }
                return Long.class;
            }
            if (primitive.isBoolean()) {
                return Boolean.class;
            }
            return element.getClass();
        }
    }
    
}
