package com.qnnect.version;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VersionService {

    private final VersionInfoRepository versionRepository;

    public Boolean checkIsVersionValid(EOs os, String currentVersion){
        VersionInfo latestVersion = versionRepository.findTop1ByOsOrderByIdDesc(os);
        return (latestVersion.getVersion().equals(currentVersion)) ? true : false;
    }
}
