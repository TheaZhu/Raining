package com.thea.raining.model;

import com.thea.raining.bean.City;

import java.util.List;

/**
 * @author Thea (theazhu0321@gmail.com)
 */
public interface ICityModel {

    void add(String id, String town, String region, String province);

    void add(City city);

    void used(String id, boolean used);

    void delete(String id);

    String findByTown(String town);

    List<City> findByRegion(String region);

    List<City> findByProvince(String province);

    List<String> findTowns();

    List<String> findRegions();

    List<String> findProvinces();

    List<String> findAllUsed();

    List<City> findAll();
}
