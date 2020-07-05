package wooteco.jpa.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository members;

    @Autowired
    FavoriteRepository favorites;

    @Test
    void save() {
        Member expected = new Member("test");
        expected.addFavorite(favorites.save(new Favorite()));
        Member actual = members.save(expected);
        members.flush();

        assertThat(actual).isEqualTo(expected);
    }
}