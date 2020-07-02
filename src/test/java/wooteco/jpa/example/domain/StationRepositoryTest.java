package wooteco.jpa.example.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StationRepositoryTest {

    @Autowired
    StationRepository stations;

    @Test
    void save() {
        Station expected = new Station();
        expected.setName("잠실역");

        Station actual = stations.save(expected);

        assertAll(
            () -> assertThat(actual.getId()).isEqualTo(expected.getId()),
            () -> assertThat(actual.getName()).isEqualTo(expected.getName()),
            () -> assertThat(actual).isEqualTo(expected)
        );
    }

    @Test
    void findByName() {
        Station expected = new Station();
        expected.setName("잠실역");
        stations.save(expected);

        Station actual = stations.findByName("잠실역");

        assertThat(actual).isEqualTo(expected);
    }
}