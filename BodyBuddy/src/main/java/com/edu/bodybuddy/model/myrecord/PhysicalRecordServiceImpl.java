package com.edu.bodybuddy.model.myrecord;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.myrecord.PhysicalRecord;
import com.edu.bodybuddy.exception.PhysicalRecordException;

@Service
public class PhysicalRecordServiceImpl implements PhysicalRecordService{

	@Autowired
	private PhysicalRecordDAO physicalRecordDAO;
	
	
	@Override
	public void regist(PhysicalRecord physicalRecord) throws PhysicalRecordException{
		physicalRecordDAO.insert(physicalRecord);
	}

	@Override
	public List<PhysicalRecord> selectForMonth(Map<String, String> pysicalOneMonthPeriod) throws PhysicalRecordException{
		return physicalRecordDAO.selectPhysicalForMonth(pysicalOneMonthPeriod);
	}

	@Override
	public PhysicalRecord select(PhysicalRecord physicalRecord) {
		physicalRecord=physicalRecordDAO.select(physicalRecord);
		return physicalRecord;
	}

	@Override
	public void update(PhysicalRecord physicalRecord) throws PhysicalRecordException{
		physicalRecordDAO.update(physicalRecord);
	}

	@Override
	public void delete(PhysicalRecord physicalRecord)  throws PhysicalRecordException{
		physicalRecordDAO.delete(physicalRecord);
	}

}
