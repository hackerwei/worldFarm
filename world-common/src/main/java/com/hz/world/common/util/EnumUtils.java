package com.hz.world.common.util;

import org.apache.commons.lang3.StringUtils;


/**
 * 枚举操作工具类
 * @author <a href="mailto:liqg@hiwitech.com">liqiangen</a>
 * @version 1.0
 */
public class EnumUtils {

    /**
     * 根据属性值获取枚举对象
     * @param enumValus 枚举实例数组
     * @param propertyName  属性名
     * @param value 属性值
     * @return
     */
    public static <E extends Enum<E>> E getEnum(E[] enumValus, String propertyName, Object value) {
        if (StringUtils.isNotBlank(propertyName) && value != null) {
            for (E e : enumValus) {
                if (value.toString().equals(ReflectionUtils.getFieldValue(e, propertyName).toString())) {
                    return e;
                }
            }
        }
        return null;
    }
    
    /**
     * 测试枚举类
     * @author <a href="mailto:liqg@hiwitech.com">liqiangen</a>
     * @version 1.0
     */
    enum Test {
        a(1, "aa"), b(2, "bb");
        
        private int type;
        private String name;
        
        private Test(int type, String name) {
            this.type = type;
            this.name = name;
        }
        
        public int getType() {
            return type;
        }
        public void setType(int type) {
            this.type = type;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        
    }
    
    public static void main(String[] args) {
        Test test = EnumUtils.getEnum(Test.values(), "type", 1);
        System.out.println(test.name);
        
        Test test2 = EnumUtils.getEnum(Test.values(), "name", "aa");
        System.out.println(test2.name);
    }
}
