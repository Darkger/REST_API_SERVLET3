package com.eugene.crude.crude.practic.repository;


import com.eugene.crude.crude.practic.model.PostOrRegion;
import com.eugene.crude.crude.practic.model.Region;

import java.util.List;

public interface RegionRepository extends Genericrepository<PostOrRegion, Long> {

    @Override
    PostOrRegion  getById(Long aLong);

    @Override
    List<PostOrRegion> getAll();

    @Override
    PostOrRegion save(PostOrRegion PostOrRegion);

    @Override
    PostOrRegion update(PostOrRegion region);

    @Override
    void deleteById(Long aLong);
}
