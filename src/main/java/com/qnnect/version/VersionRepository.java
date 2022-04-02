package com.qnnect.version;

import com.qnnect.questions.domain.CafeQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface VersionRepository extends JpaRepository<VersionInfo, Long> {

    VersionInfo findTop1ByOsOrderByCreatedAtDesc(@Param(value = "os") String os);

}
