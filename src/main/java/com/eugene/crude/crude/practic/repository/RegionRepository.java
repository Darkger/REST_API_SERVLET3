package com.eugene.crude.crude.practic.repository;



import com.eugene.crude.crude.practic.model.Region;


public interface RegionRepository extends GenericRepository<Region, Integer> {

    Region findByName (Region region);
}
