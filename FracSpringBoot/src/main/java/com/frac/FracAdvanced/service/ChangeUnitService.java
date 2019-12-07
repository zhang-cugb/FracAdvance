package com.frac.FracAdvanced.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.frac.FracAdvanced.model.FluidLibraryModel;
import com.frac.FracAdvanced.model.ProjectDetails;
import com.frac.FracAdvanced.model.ReservoirFluidModel;
import com.frac.FracAdvanced.model.ReservoirLithologyModel;
import com.frac.FracAdvanced.model.StressAnalysisModel;
import com.frac.FracAdvanced.repository.ProjectDetailRepo;
import com.frac.FracAdvanced.repository.ReservoirFluidRepo;
import com.frac.FracAdvanced.repository.ReservoirLithologyRepo;
import com.frac.FracAdvanced.repository.StressAnalysisRepo;

@Component
public class ChangeUnitService {
	
	@Autowired
	ProjectDetailRepo repo;
	
	@Autowired
	HttpSession httpSession;
	@Autowired
	ReservoirFluidRepo fluidRepo;
	
	@Autowired
	ReservoirLithologyRepo lithologyRepo;
	@Autowired
	StressAnalysisRepo stressAnalysisRepo;
	ProjectDetails p1;
	ProjectDetails pids;
	String uTypeDataBase;


	public void convertUnit( String uType)
	{
		pids =(ProjectDetails)httpSession.getAttribute("ProjectDetail");
		   p1=repo.findById(pids.getId()).orElse(pids);
		   
		   System.out.println("convertUnit method======"+uType);
	      p1.setUnitType(uType);	
	      repo.save(p1);
	      }		
	public void changeReservoirFluidProperties(String uType)
	{
		
		  pids =(ProjectDetails)httpSession.getAttribute("ProjectDetail");
		  int pid=pids.getId();
		   uTypeDataBase= repo.findById(pid).get().getUnitType();
			List<ReservoirFluidModel> list= fluidRepo.findBydetails(pids);
			ReservoirFluidModel InitialReservoirPressure =fluidRepo.findByParamAndDetails("Initial Reservoir Pressure", pids).get(0);
			ReservoirFluidModel BottomholePressure =fluidRepo.findByParamAndDetails("Bottomhole Pressure", pids).get(0);
			ReservoirFluidModel ReservoirTemperature =fluidRepo.findByParamAndDetails("Reservoir Temperature", pids).get(0);
			ReservoirFluidModel ReservoirPorosity =fluidRepo.findByParamAndDetails("Reservoir Porosity", pids).get(0);
			ReservoirFluidModel ReservoirPermability =fluidRepo.findByParamAndDetails("Reservoir Permability", pids).get(0);
			ReservoirFluidModel OilSaturationFactor =fluidRepo.findByParamAndDetails("Oil Saturation Factor", pids).get(0);
			ReservoirFluidModel GasSpecificGravity =fluidRepo.findByParamAndDetails("Gas Specific Gravity", pids).get(0);
				
			
		if(uTypeDataBase.equals(uType))				
		{System.out.println("no need to change");		
		}
		else if("Field".equals(uType))
		{ 
        InitialReservoirPressure.setValue(Double.toString(((Double.parseDouble(InitialReservoirPressure.getValue()))*3.2)));
        BottomholePressure.setValue(Double.toString(((Double.parseDouble(BottomholePressure.getValue()))*3.2)));
        ReservoirTemperature.setValue(Double.toString(((Double.parseDouble(ReservoirTemperature.getValue()))*3.2)));
        ReservoirPorosity.setValue(Double.toString(((Double.parseDouble(ReservoirPorosity.getValue()))*3.2)));
        ReservoirPermability.setValue(Double.toString(((Double.parseDouble(ReservoirPermability.getValue()))*3.2)));
        OilSaturationFactor.setValue(Double.toString(((Double.parseDouble(OilSaturationFactor.getValue()))*3.2)));
        GasSpecificGravity.setValue(Double.toString(((Double.parseDouble(GasSpecificGravity.getValue()))*3.2)));
		
        
        
        
        fluidRepo.save(InitialReservoirPressure);
		fluidRepo.save(BottomholePressure);
		fluidRepo.save(ReservoirTemperature);
		fluidRepo.save(ReservoirPorosity);
		fluidRepo.save(ReservoirPermability);
		fluidRepo.save(OilSaturationFactor);
		fluidRepo.save(GasSpecificGravity);
				
		}
		else if("Metric".equals(uType))
		{		
		InitialReservoirPressure.setValue(Double.toString(((Double.parseDouble(InitialReservoirPressure.getValue()))/3.2)));
        BottomholePressure.setValue(Double.toString(((Double.parseDouble(BottomholePressure.getValue()))/3.2)));
        ReservoirTemperature.setValue(Double.toString(((Double.parseDouble(ReservoirTemperature.getValue()))/3.2)));
        ReservoirPorosity.setValue(Double.toString(((Double.parseDouble(ReservoirPorosity.getValue()))/3.2)));
        ReservoirPermability.setValue(Double.toString(((Double.parseDouble(ReservoirPermability.getValue()))/3.2)));
        OilSaturationFactor.setValue(Double.toString(((Double.parseDouble(OilSaturationFactor.getValue()))/3.2)));
        GasSpecificGravity.setValue(Double.toString(((Double.parseDouble(GasSpecificGravity.getValue()))/3.2)));
		
		fluidRepo.save(InitialReservoirPressure);
		fluidRepo.save(BottomholePressure);				
		fluidRepo.save(ReservoirTemperature);
		fluidRepo.save(ReservoirPorosity);
		fluidRepo.save(ReservoirPermability);
		fluidRepo.save(OilSaturationFactor);
		fluidRepo.save(GasSpecificGravity);

		}
	}
	
	////changeReservoirLithology
	
	public void changeReservoirLithology(String uType)
	{
		List<ReservoirLithologyModel> list =lithologyRepo.findBydetails(pids);
		
		if(uTypeDataBase.equals(uType))				
		{System.out.println("no need to change");		
		}
		else if("Field".equals(uType))
		{ 				
		for(int i=0;i<list.size();i++)
		{			
			list.get(i).setLayer(Double.toString((Double.parseDouble(list.get(i).getLayer())*3.2)));
			list.get(i).setLeakoff(Double.toString((Double.parseDouble(list.get(i).getLeakoff())*3.2)));
			list.get(i).setMd(Double.toString((Double.parseDouble(list.get(i).getMd())*3.2)));
			list.get(i).setPoro(Double.toString((Double.parseDouble(list.get(i).getPoro())*3.2)));
			list.get(i).setRock(Double.toString((Double.parseDouble(list.get(i).getRock())*3.2)));
			list.get(i).setTvd(Double.toString((Double.parseDouble(list.get(i).getTvd())*3.2)));
			list.get(i).setYoungs(Double.toString((Double.parseDouble(list.get(i).getYoungs())*3.2)));
			list.get(i).setPerm(Double.toString((Double.parseDouble(list.get(i).getPerm())*3.2)));
			lithologyRepo.save(list.get(i));
		}	}
		else if("Metric".equals(uType))
		{for(int i=0;i<list.size();i++)
		{			
			list.get(i).setLayer(Double.toString((Double.parseDouble(list.get(i).getLayer())/3.2)));
			list.get(i).setLayer(Double.toString((Double.parseDouble(list.get(i).getLayer())/3.2)));
			list.get(i).setLeakoff(Double.toString((Double.parseDouble(list.get(i).getLeakoff())/3.2)));
			list.get(i).setMd(Double.toString((Double.parseDouble(list.get(i).getMd())/3.2)));
			list.get(i).setPoro(Double.toString((Double.parseDouble(list.get(i).getPoro())/3.2)));
			list.get(i).setRock(Double.toString((Double.parseDouble(list.get(i).getRock())/3.2)));
			list.get(i).setTvd(Double.toString((Double.parseDouble(list.get(i).getTvd())/3.2)));
			list.get(i).setYoungs(Double.toString((Double.parseDouble(list.get(i).getYoungs())/3.2)));
			list.get(i).setPerm(Double.toString((Double.parseDouble(list.get(i).getPerm())/3.2)));
            lithologyRepo.save(list.get(i));
		}}
		
	}
	///////////changeStressAnalysis
	public void changeStressAnalysis(String uType)
	{
		List<StressAnalysisModel> list =stressAnalysisRepo.findBydetails(pids);
		
		if(uTypeDataBase.equals(uType))				
		{System.out.println("no need to change");		
		}
		else if("Field".equals(uType))
		{ 				
		for(int i=0;i<list.size();i++)
		{			
			list.get(i).setDensity(Double.toString((Double.parseDouble(list.get(i).getDensity())*3.2)));
			list.get(i).setDepth(Double.toString((Double.parseDouble(list.get(i).getDepth())*3.2)));
			list.get(i).setPore(Double.toString((Double.parseDouble(list.get(i).getPore())*3.2)));
			list.get(i).setRatio(Double.toString((Double.parseDouble(list.get(i).getRatio())*3.2)));
			stressAnalysisRepo.save(list.get(i));

		}	}
		else if("Metric".equals(uType))
		{for(int i=0;i<list.size();i++)
		{			
			list.get(i).setDensity(Double.toString((Double.parseDouble(list.get(i).getDensity())/3.2)));
			list.get(i).setDepth(Double.toString((Double.parseDouble(list.get(i).getDepth())/3.2)));
			list.get(i).setPore(Double.toString((Double.parseDouble(list.get(i).getPore())/3.2)));
			list.get(i).setRatio(Double.toString((Double.parseDouble(list.get(i).getRatio())/3.2)));
			stressAnalysisRepo.save(list.get(i));
		}}
		
	}
	
	//////////
}
