package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SessionImageTest {
    @Test
    void 강의_이미지_생성() {
        SessionImage sessionImage = new SessionImage(1L, "next.jpg", 300, 200, 1000);
        assertThat(sessionImage).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"next.jjpg", "nexxxx"})
    void 강의_이미지_확장자_검증(String value) {
        assertThatThrownBy(() -> new SessionImage(1L, value, 300, 200, 1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
