/**
 * xxx
 * Copyright (c) 2012-2014 All Rights Reserved.
 */
package x.framework.lang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述注解
 * <p>
 * 用于对类、方法、字段描述
 * 
 * @author xman 2014年1月2日
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {

    /** 描述 */
    String[] value();

}
