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
        Station expected = new Station("잠실역");

        Station actual = stations.save(expected);

        assertAll(
            () -> assertThat(actual.getId()).isEqualTo(expected.getId()),
            () -> assertThat(actual.getName()).isEqualTo(expected.getName()),
            () -> assertThat(actual).isEqualTo(expected)
        );
    }

    @Test
    void findByName() {
        Station expected = new Station("잠실역");
        stations.save(expected);

        Station actual = stations.findByName("잠실역");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void update() {
        Station expected = new Station("잠실역");
        stations.save(expected);

        expected.setName("삼성역");
        System.out.println("???");

        // DB 커넥션 없이 영속성 컨텍스트에서 바로 들고 온다 => 이름 바뀐 채로 온다. => update쿼리는 안날라가 있다.
        Station findById = stations.findById(expected.getId()).get();
        Station findByName = stations.findByName("삼성역"); // update 쿼리가 flush되는 시점

        assertAll(
            () -> assertThat(findById).isEqualTo(expected),
            () -> assertThat(findByName).isEqualTo(expected)
        );
    }

    @Test
    void saveWithLine() {
        Station expected = new Station("잠실역");
        Line line = new Line("2호선");
        expected.setLine(line);
        Station actual = stations.save(expected);

        assertAll(
            () -> assertThat(actual).isEqualTo(expected),
            () -> assertThat(actual.getLine()).isEqualTo(line)
        );
    }
}