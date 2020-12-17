package com.quick.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quick.app.web.rest.TestUtil;

public class EditorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Editor.class);
        Editor editor1 = new Editor();
        editor1.setId(1L);
        Editor editor2 = new Editor();
        editor2.setId(editor1.getId());
        assertThat(editor1).isEqualTo(editor2);
        editor2.setId(2L);
        assertThat(editor1).isNotEqualTo(editor2);
        editor1.setId(null);
        assertThat(editor1).isNotEqualTo(editor2);
    }
}
