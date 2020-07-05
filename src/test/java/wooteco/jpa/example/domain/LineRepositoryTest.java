package wooteco.jpa.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class LineRepositoryTest {

    @Autowired
    LineRepository lines;

    @Test
    void findByName() {
        Line line = lines.findByName("3호선");

        //지연로딩(lazyLoading)에 의해 getStations()를 요청하기 전까지 station에 대한 정보요청을 하지 않는다!
        assertThat(line.getStations()).hasSize(1);
    }
}