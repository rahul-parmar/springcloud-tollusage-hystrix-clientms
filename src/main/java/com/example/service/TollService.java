package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.TollUsage;
import com.example.repository.TollUsageFeignMSRepo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class TollService implements TollServiceIntf{
	
	@Autowired
	TollUsageFeignMSRepo feignRepo;
	
	
	
	@Override
	@HystrixCommand(fallbackMethod="listOfTollUsageFallback")
	public List<TollUsage> findAll(){
	return feignRepo.findAll();
	}
	
	public List<TollUsage> listOfTollUsageFallback(){
		List<TollUsage> list=new ArrayList<>();
		list.add(new TollUsage("dummyid", "dummystation", "dummylicenseplate", 
				"dummytimeStamp"));
		return list;
	}
	
	
	
	@Override
	public TollUsage getById(String id){
	return feignRepo.findById(id);
	
	
	}
	
	
	

}
