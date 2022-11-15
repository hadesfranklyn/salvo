package com.github.hadesfranklyn.ufal.waterconsumption.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.hadesfranklyn.ufal.waterconsumption.config.IrrigationConfiguration;
import com.github.hadesfranklyn.ufal.waterconsumption.services.IrrigationService;

@RestController
@RequestMapping(value = "/irrigations")
public class IrrigationController {

	@Autowired
	private IrrigationConfiguration irrigationConfiguration;

	@Autowired
	private IrrigationService service;

//	@GetMapping
//	public Page<IrrigationDTO> findAll(Pageable pageable) {
//		return service.findAll(pageable);
//	}
//
//	@GetMapping(value = "/{id}")
//	public IrrigationDTO findById(@PathVariable Long id) {
//		return service.findById(id);
//	}
//
//	@PostMapping
//	public ResponseEntity<IrrigationDTO> insert(@Valid @RequestBody IrrigationDTO objDTO) {
//		Irrigation newObj = service.insert(objDTO);
//
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
//
//		return ResponseEntity.created(uri).build();
//	}
//
//	@DeleteMapping(value = "/{id}")
//	public ResponseEntity<Void> delete(@PathVariable Long id) {
//		service.delete(id);
//		return ResponseEntity.noContent().build();
//	}

	@GetMapping(value = "/areaToBeIrrigated")
	public ResponseEntity<Double> areaToBeIrrigated(
			@RequestParam(value = "irrigationArea", defaultValue = "") Double irrigationArea,
			@RequestParam(value = "appliedBlade", defaultValue = "") Double appliedBlade) {

		if (irrigationArea == null || appliedBlade == null) {
			irrigationArea = irrigationConfiguration.getIrrigationArea();
			appliedBlade = irrigationConfiguration.getAppliedBlade();
		}

		irrigationConfiguration.setIrrigationArea(irrigationArea);
		irrigationConfiguration.setAppliedBlade(appliedBlade);
		double resultIrrigationConsumption = irrigationConfiguration.getIrrigationArea()
				* irrigationConfiguration.getAppliedBlade();
		return new ResponseEntity<>(resultIrrigationConsumption, HttpStatus.OK);
	}

}
