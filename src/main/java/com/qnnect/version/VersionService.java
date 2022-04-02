package com.qnnect.version;

import com.qnnect.ingredients.domain.Ingredient;
import com.qnnect.ingredients.dto.IngredientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VersionService {

    private final VersionRepository versionRepository;

    public Boolean checkIsVersionValid(EOs os, String currentVersion){
        VersionInfo latestVersion = versionRepository.findTop1ByOsOrderByCreatedAtDesc(os.toString());
        return (latestVersion.getVersion().equals(currentVersion)) ? true : false;
    }
}
