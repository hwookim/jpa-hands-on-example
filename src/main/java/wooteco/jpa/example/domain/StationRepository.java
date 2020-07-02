package wooteco.jpa.example.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {

    // 메소드 이름 기반으로 aql문을 작성한다.
    Station findByName(String name);
}
