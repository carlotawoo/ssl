package com.example.demo.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.*;


public class ToolUtil {

    public static void main(String[] args) throws Exception {
        Map<String, Object> paymentInfoByMoney = getPaymentInfoByMoney(5824.00);
        System.out.println(paymentInfoByMoney);
    }

    public static Map<String, Object> getPaymentInfoByMoney(double money) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        double ZZS = Double.parseDouble(decimalFormat.format((money * 0.01)));//增值税
        double CSJSWHS = Double.parseDouble(decimalFormat.format((ZZS * 0.07 / 2)));//城市建设维护税
        double JYFJS = Double.parseDouble(decimalFormat.format((ZZS * 0.03 / 2)));//教育附加
        double DFJYFJS = Double.parseDouble(decimalFormat.format((ZZS * 0.02 / 2)));//地方教育附加
        double GRSDS = Double.parseDouble(decimalFormat.format((money * 0.005)));//个人所得税
        double count = ZZS + CSJSWHS + JYFJS + DFJYFJS + GRSDS;
        Map<String, Object> map = new HashMap<>();
        map.put("ZZS", ZZS);
        map.put("CSJSWHS", CSJSWHS);
        map.put("JYFJS", JYFJS);
        map.put("DFJYFJS", DFJYFJS);
        map.put("GRSDS", GRSDS);
        map.put("COUNT", count);
        return map;
    }

    /**
     * 将 Map对象转化为JavaBean
     *
     * @param map
     * @return Object对象
     * @author loulan
     */
    public static <T> T convertMap2Bean(Map map, Class T) throws Exception {
        if (map == null || map.size() == 0) {
            return null;
        }
        BeanInfo beanInfo = Introspector.getBeanInfo(T);
        T bean = (T) T.newInstance();
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0, n = propertyDescriptors.length; i < n; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            //将字母大写，这里先注掉，因为本例子直接读取名字匹配即可。
            //String upperPropertyName = propertyName.toUpperCase();
            if (map.containsKey(propertyName)) {
                Object value = map.get(propertyName);
                //这个方法不会报参数类型不匹配的错误。
                BeanUtils.copyProperty(bean, propertyName, value);
//用这个方法对int等类型会报参数类型不匹配错误，需要我们手动判断类型进行转换，比较麻烦。
//descriptor.getWriteMethod().invoke(bean, value);
//用这个方法对时间等类型会报参数类型不匹配错误，也需要我们手动判断类型进行转换，比较麻烦。
//BeanUtils.setProperty(bean, propertyName, value);
            }
        }
        return bean;
    }

    /**
     * 将 JavaBean对象转化为 Map
     *
     * @param bean 要转化的类型
     * @return Map对象
     * @author loulan
     */
    public static Map convertBean2Map(Object bean) throws Exception {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        //PropertyDescriptor:属性描述器
        PropertyDescriptor[] propertyDescriptors = beanInfo
                .getPropertyDescriptors();
        for (int i = 0, n = propertyDescriptors.length; i < n; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                //利用反射
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

  /*  *
     * json字符串转list
     *
     * @return
     * @param jsonFile
     * @throws Exception*/
    public static List<Map<String, Object>> GetJsonListByString(String jsonFile) throws Exception {
        JSONArray arry = JSONArray.fromObject(jsonFile);
        List<Map<String, Object>> rsList = new ArrayList<>();
        for (int i = 0; i < arry.size(); i++) {
            JSONObject jsonObject = arry.getJSONObject(i);
            Map<String, Object> map = new HashMap<>();
            for (Iterator<?> iter = jsonObject.keys(); iter.hasNext(); ) {
                String key = (String) iter.next();
                map.put(key, jsonObject.get(key));
            }
            rsList.add(map);
        }
        return rsList;
    }
    /**
     * 将 json字符串转化为List<JavaBean>
     *
     * @param json json字符串
     * @param T 对象类
     * @return listBean对象
     * @author loulan
     */
    public static <T> List<T> jsonConvertToListBean(String json, Class T) throws Exception {
        List<Map<String, Object>> maps = GetJsonListByString(json);
        List<T> listBean = convertListMap2ListBean(maps, T);
        return listBean;
    }

    /**
     * 将 List<Map>对象转化为List<JavaBean>
     *
     * @return Object对象
     * @author loulan
     */
    public static <T> List<T> convertListMap2ListBean(List<Map<String, Object>> listMap, Class T) throws Exception {
        List<T> beanList = new ArrayList<T>();
        for (int i = 0, n = listMap.size(); i < n; i++) {
            Map<String, Object> map = listMap.get(i);
            T bean = convertMap2Bean(map, T);
            beanList.add(bean);
        }
        return beanList;
    }

    /**
     * 将 List<JavaBean>对象转化为List<Map>
     *
     * @return Object对象
     * @author loulan
     */
    public static List<Map<String, Object>> convertListBean2ListMap(List<Object> beanList) throws Exception {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (int i = 0, n = beanList.size(); i < n; i++) {
            Object bean = beanList.get(i);
            Map map = convertBean2Map(bean);
            mapList.add(map);
        }
        return mapList;
    }
}
