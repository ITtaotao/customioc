package com.ittaotao.module2.ioc;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tao.liu
 * @desc 通过 jdk 动态代理 和 第三方 CGLIB 动态代理
 */
public class BeanFactory {


    private static Map<String, Object> map = new HashMap<>();


    static {

        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");

        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();
            List<Element> beanList = rootElement.selectNodes("//bean");
            for (int i = 0; i < beanList.size(); i++) {
                Element element = beanList.get(i);

                String id = element.attributeValue("id");
                String clazz = element.attributeValue("class");

                Class<?> aClass = Class.forName(clazz);
                Object o = aClass.newInstance();  // 实例化之后的对象

                // 存储到map中待用
                map.put(id, o);

            }


            List<Element> propertyList = rootElement.selectNodes("//property");

            for (int i = 0; i < propertyList.size(); i++) {
                Element element = propertyList.get(i);
                String name = element.attributeValue("name");
                String ref = element.attributeValue("ref");


                Element parent = element.getParent();


                String parentId = parent.attributeValue("id");
                Object parentObject = map.get(parentId);

                Method[] methods = parentObject.getClass().getMethods();
                for (int j = 0; j < methods.length; j++) {
                    Method method = methods[j];
                    if (method.getName().equalsIgnoreCase("set" + name)) {
                        method.invoke(parentObject, map.get(ref));
                    }
                }

                map.put(parentId, parentObject);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static Object getBean(String id) {
        return map.get(id);
    }
}
