package com.quick.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quick.app.web.rest.TestUtil;

public class ProductIdTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductId.class);
        ProductId productId1 = new ProductId();
        productId1.setId(1L);
        ProductId productId2 = new ProductId();
        productId2.setId(productId1.getId());
        assertThat(productId1).isEqualTo(productId2);
        productId2.setId(2L);
        assertThat(productId1).isNotEqualTo(productId2);
        productId1.setId(null);
        assertThat(productId1).isNotEqualTo(productId2);
    }
}
