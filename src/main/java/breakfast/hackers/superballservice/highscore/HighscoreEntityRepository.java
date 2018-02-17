package breakfast.hackers.superballservice.highscore;

import org.springframework.data.jpa.repository.JpaRepository;

interface HighscoreEntityRepository extends JpaRepository<HighscoreEntity, Long> {

}
