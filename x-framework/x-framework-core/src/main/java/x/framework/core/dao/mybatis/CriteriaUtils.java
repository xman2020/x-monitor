package x.framework.core.dao.mybatis;

import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;

/**
 * 常用条件工具类
 *
 * @author shuyuan
 * @date 2020-04-15
 */
public class CriteriaUtils {

    public static Example.Criteria andEqual(Example.Criteria criteria, String property, Object value) {
        if (isNotEmpty(value)) {
            criteria.andEqualTo(property, value);
        }

        return criteria;
    }

    public static Example.Criteria andLike(Example.Criteria criteria, String property, String value) {
        if (isNotEmpty(value)) {
            criteria.andLike(property, "%" + value + "%");
        }

        return criteria;
    }

    public static Example.Criteria andBetween(Example.Criteria criteria, String property, Object value1,
        Object value2) {
        if (isNotEmpty(value1) && isNotEmpty(value2)) {
            criteria.andBetween(property, value1, value2);
        }

        return criteria;
    }

    public static Example.Criteria andNotBetween(Example.Criteria criteria, String property, Object value1,
        Object value2) {
        if (isNotEmpty(value1) && isNotEmpty(value2)) {
            criteria.andNotBetween(property, value1, value2);
        }

        return criteria;
    }

    public static Example.Criteria andIn(Example.Criteria criteria, String property, String value) {
        if (isNotEmpty(value)) {
            criteria.andIn(property, Arrays.asList(value.split(",")));
        }

        return criteria;
    }

    public static Example.Criteria andGreater(Example.Criteria criteria, String property, Object value) {
        if (isNotEmpty(value)) {
            criteria.andGreaterThan(property, value);
        }

        return criteria;
    }

    public static Example.Criteria andGreaterEqual(Example.Criteria criteria, String property, Object value) {
        if (isNotEmpty(value)) {
            criteria.andGreaterThanOrEqualTo(property, value);
        }

        return criteria;
    }

    public static Example.Criteria andLess(Example.Criteria criteria, String property, Object value) {
        if (isNotEmpty(value)) {
            criteria.andLessThan(property, value);
        }

        return criteria;
    }

    public static Example.Criteria andLessEqual(Example.Criteria criteria, String property, Object value) {
        if (isNotEmpty(value)) {
            criteria.andLessThanOrEqualTo(property, value);
        }

        return criteria;
    }

    public static boolean isNotEmpty(Object value) {
        return value != null && value.toString().length() > 0;
    }

}
