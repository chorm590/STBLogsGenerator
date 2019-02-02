package com.chorm.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Gson gson = new GsonBuilder()
            .setVersion(1.0)
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            .serializeNulls()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    /*//序列化
    .registerTypeHierarchyAdapter(Date.class, new JsonSerializer<Date>() {
        @Override
        public JsonElement serialize(Date date, Type type, JsonSerializationContext context) {
            if (date == null)
                return new JsonPrimitive(0);
            return new JsonPrimitive(date.getTime());
        }
    })
    //反序列化
    .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
        @Override
        public Date deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
            long tick = 0;
            try {
                tick = Long.parseLong(json.getAsString());
                return new Date(tick);
            } catch (Exception e) {
                return null;
            }
        }
    })*/
    public static String gsonToJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T gsonToBean(Class<T> clazz, String json) {
        return gson.fromJson(json, clazz);
    }

    public static <T> List<T> gsonToList(Class<T> clazz, String json) {
        TypeToken<List<T>> type = new TypeToken<List<T>>() {
        };
        return gson.fromJson(json, type.getType());
    }

    public static <K, V> Map<K, V> gsonToMap(Class<K> k, Class<V> v, String json) {
        TypeToken<Map<K, V>> type = new TypeToken<Map<K, V>>() {
        };
        return gson.fromJson(json, type.getType());
    }

    public static <T> T fastToBean(Class<T> clazz, String text) {
        T t = null;
        try {
            t = JSON.parseObject(text, clazz);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new JSONException("toFastJson error", ex);
        }
        return t;
    }

    public static <T> List<T> fastToList(Class<T> clazz, String text) {
        List<T> list;
        try {
            list = JSON.parseArray(text, clazz);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new JSONException("fastToList error", ex);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> fastToMap(String text) {
        //Map<K,V> map = JSON.parseObject(text, new TypeReference<Map<K,V>>() {});
        return (Map<K, V>) JSON.parse(text);
    }

    public static Object fastToJson(Object javaObject) {
        ParserConfig mapping = ParserConfig.getGlobalInstance();
        if (javaObject == null)
            return null;

        if (javaObject instanceof JSON)
            return javaObject;

        if (javaObject instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<Object, Object> map = (Map<Object, Object>) javaObject;
            com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject(map.size());
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                String key = TypeUtils.castToString(entry.getKey());
                Object value = fastToJson(entry.getValue());
                json.put(key, value);
            }

            return json;
        }

        if (javaObject instanceof Collection) {
            @SuppressWarnings({"unchecked"})
            Collection<Object> collection = (Collection<Object>) javaObject;
            JSONArray array = new JSONArray(collection.size());

            for (Object item : collection) {
                Object value = fastToJson(item);
                array.add(value);
            }

            return array;
        }

        Class<?> clazz = javaObject.getClass();
        if (clazz.isEnum())
            return ((Enum<?>) javaObject).name();

        if (clazz.isArray()) {
            int len = Array.getLength(javaObject);
            JSONArray array = new JSONArray(len);
            for (int i = 0; i < len; ++i) {
                Object item = Array.get(javaObject, i);
                Object value = fastToJson(item);
                array.add(value);
            }

            return array;
        }

        if (mapping.isPrimitive(clazz)) {
            return clazz.equals(Date.class) ? dateFormat.format(javaObject) : javaObject;
        }

        try {
            List<FieldInfo> getters = TypeUtils.computeGetters(clazz, null);
            com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject(getters.size());
            for (FieldInfo field : getters) {
                if ("metaClass".equalsIgnoreCase(field.getName()))
                    continue;

                Object item = field.get(javaObject);
                Object value = fastToJson(item);
                json.put(field.getName(), value);
            }

            return json;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new JSONException("toFastJson error", ex);
        }
    }

}
