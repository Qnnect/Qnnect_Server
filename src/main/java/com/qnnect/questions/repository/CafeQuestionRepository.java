package com.qnnect.questions.repository;

import com.qnnect.questions.domain.CafeQuestion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CafeQuestionRepository extends JpaRepository<CafeQuestion, Long> {
//    @Query(value="SELECT 1 * FROM table ORDER BY anyField DESC LIMIT 1", nativeQuery = true)
//    @Query(value = "select top 1 c from cafeQuestion c where c.cafe_id=:cafe_id order by id", nativeQuery = true)
//    CafeQuestion findByCafe_IdTop1ById(@Param(value="cafeId") long cafeId);
//AaaEntity findTop1ByFlagEqualsOrderByCreatedAtDesc(Integer flag);

    CafeQuestion findTop1ByCafe_IdOrderByCreatedAtDesc(@Param(value="cafeId") long cafeId);
    public List<CafeQuestion> findAllByCafe_Id(@Param(value = "cafeId") long cafeId, Pageable pageable);
}
