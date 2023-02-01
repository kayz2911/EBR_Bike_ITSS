package com.ebr.components.gui.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.ebr.bean.options.BikeStatusOption;
import com.ebr.bean.options.DistrictOption;
import com.ebr.bean.options.ProvinceOption;
import com.ebr.bean.options.WardOption;
import com.ebr.bean.subdivisions.District;
import com.ebr.bean.subdivisions.Province;
import com.ebr.bean.subdivisions.Subdivision;
import com.ebr.bean.subdivisions.Ward;
import com.ebr.enums.BikeStatus;

import com.ebr.serverapi.subdivision.SubdivisionApi;

public class GUIUtils {
	// generate bike status options
	public static Vector generateBikeStatusOptions() {
		Vector options = new Vector();

		options.addElement(new BikeStatusOption(BikeStatus.AVAILABLE));
		options.addElement(new BikeStatusOption(BikeStatus.HOLDING));
		options.addElement(new BikeStatusOption(BikeStatus.RENTED));

		return options;
	}

	// generate province options
	public static Vector generateProvinceOptions() {
		Vector options = new Vector();

		ArrayList<Subdivision> provinces = new SubdivisionApi().getProvinces(null);

		for (Subdivision p : provinces) {
			Province province = (Province) p;
			options.addElement(new ProvinceOption(province.getCode(), province.getName()));
		}

		return options;
	}

	// generate district options
	public static Vector generateDistrictOptions(String provinceCode) {
		Vector options = new Vector();

		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("provinceCode", provinceCode);

		ArrayList<Subdivision> districts = new SubdivisionApi().getDistricts(queryParams);

		for (Subdivision d : districts) {
			District district = (District) d;
			options.addElement(new DistrictOption(district.getCode(), district.getName(), district.getProvinceCode()));
		}

		return options;
	}

	// generate ward options
	public static Vector generateWardOptions(String districtCode) {
		Vector options = new Vector();

		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("districtCode", districtCode);

		ArrayList<Subdivision> wards = new SubdivisionApi().getWards(queryParams);

		for (Subdivision w : wards) {
			Ward ward = (Ward) w;
			options.addElement(new WardOption(ward.getCode(), ward.getName(), ward.getDistrictCode()));
		}

		return options;
	}
	
}
