package com.eugene.crude.crude.practic.repository;



import com.eugene.crude.crude.practic.model.Region;

import java.util.List;

public interface RegionRepository extends Genericrepository<Region, Integer> {

    @Override
    Region  getById(Integer aLong);

    @Override
    List<Region> getAll();

    @Override
   Region save(Region region);

    @Override
    Region update(Region region);

    @Override
    void deleteById(Integer aLong);
}
