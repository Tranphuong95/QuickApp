package com.quick.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quick.app.web.rest.TestUtil;

public class ProductestTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Productest.class);
        Productest productest1 = new Productest();
        productest1.setId(1L);
        Productest productest2 = new Productest();
        productest2.setId(productest1.getId());
        assertThat(productest1).isEqualTo(productest2);
        productest2.setId(2L);
        assertThat(productest1).isNotEqualTo(productest2);
        productest1.setId(null);
        assertThat(productest1).isNotEqualTo(productest2);
    }
}
