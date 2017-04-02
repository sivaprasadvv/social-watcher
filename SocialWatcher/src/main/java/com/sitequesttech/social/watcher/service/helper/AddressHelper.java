package com.sitequesttech.social.watcher.service.helper;

import org.springframework.stereotype.Component;

import com.sitequesttech.social.watcher.domain.entity.Address;
import com.sitequesttech.social.watcher.common.support.ValueGenerator;


/**
 * Address Helper 
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
@Component
public class AddressHelper implements EntityHelper<Address> {

    @Override
    public Address createEntityInstance() {
        return new Address();
    }

    @Override
    public Address createRandomEntity() {
    	Address entity = new Address();
        entity.setStreet1(ValueGenerator.getUniqueString(10));
        return entity;
    }

    @Override
    public Address copyFrom(Address entity) {
    	Address copy = new Address();
        copy.setId(entity.getId());
        copy.setStreet1(entity.getStreet1());
        copy.setStreet2(entity.getStreet2());
        copy.setCity(entity.getCity());
        copy.setState(entity.getState());
        copy.setCountry(entity.getCountry());
        copy.setZipPostal(entity.getZipPostal());
        copy.setCreatedBy(entity.getCreatedBy());
        copy.setCreatedDate(entity.getCreatedDate());
        return copy;
    }

    @Override
    public Address copyWithoutPkFrom(Address entity) {
    	Address copy = new Address();
    	copy.setStreet1(entity.getStreet1());
        copy.setStreet2(entity.getStreet2());
        copy.setCity(entity.getCity());
        copy.setState(entity.getState());
        copy.setCountry(entity.getCountry());
        copy.setZipPostal(entity.getZipPostal());
        copy.setCreatedBy(entity.getCreatedBy());
        copy.setCreatedDate(entity.getCreatedDate());
        return copy;
    }

    @Override
    public Address updateFrom(Address fromentity, Address toEntity) {
    	toEntity.setStreet1(fromentity.getStreet1());
    	toEntity.setStreet2(fromentity.getStreet2());
    	toEntity.setCity(fromentity.getCity());
    	toEntity.setState(fromentity.getState());
    	toEntity.setCountry(fromentity.getCountry());
    	toEntity.setZipPostal(fromentity.getZipPostal());
    	toEntity.setModifiedBy(fromentity.getModifiedBy());
    	toEntity.setModifiedDate(fromentity.getModifiedDate());
        return toEntity;
    }
}
