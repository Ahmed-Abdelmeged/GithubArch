package com.ahmedabdelmeged.githubarch.util;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by Ahmed Abd-Elmeged on 2/6/2018.
 */
public class StringUtilTest {

    @Test
    public void stringIsNull_shouldReturnEmptyString() {
        String string = null;
        string = StringUtil.notNullOrEmpty(string);
        assertThat(string).isEqualTo("");
    }

    @Test
    public void stringNotNull_shouldReturnTheSameValue() {
        String string = "Hello";
        string = StringUtil.notNullOrEmpty(string);
        assertThat(string).isEqualTo("Hello");
    }

}
