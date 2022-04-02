package com.qnnect.version;

import com.qnnect.questions.domain.CafeQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionInfoRepository extends JpaRepository<VersionInfo, Long> {
    VersionInfo findTop1ByOsOrderByIdDesc(@Param(value = "os") EOs os);
}
