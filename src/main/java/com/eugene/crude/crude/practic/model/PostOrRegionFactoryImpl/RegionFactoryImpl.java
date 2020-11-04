package com.eugene.crude.crude.practic.model.PostOrRegionFactoryImpl;

import com.eugene.crude.crude.practic.model.PostOrRegion;
import com.eugene.crude.crude.practic.model.PostOrRegionFactory;
import com.eugene.crude.crude.practic.model.Region;

public class RegionFactoryImpl implements PostOrRegionFactory {
    @Override
    public PostOrRegion create() {
        return new Region() ;
    }
}
