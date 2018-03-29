package br.com.kanleitos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Hospital;
import br.com.kanleitos.repository.HospitalRepository;
import br.com.kanleitos.util.Response;
import br.com.kanleitos.validators.HospitalValidator;

@Controller
public class HospitalController {

	@Autowired
	private HospitalRepository hospitalRepository;

	@InitBinder("hospital")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new HospitalValidator());
	}

	@GetMapping("/hospitais")
	public @ResponseBody ResponseEntity<Response<List<Hospital>>> getHospitals() {
		List<Hospital> hospitais = hospitalRepository.findAll();
		Response<List<Hospital>> response = new Response<>();
		response.setData(hospitais);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/hospital")
	public @ResponseBody ResponseEntity<Response<Integer>> cadastrarHospital(@RequestBody @Valid Hospital hospital,
			BindingResult result) {

		Response<Integer> response = new Response<Integer>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			hospital = hospitalRepository.save(hospital);
			response.setData(hospital.getId_hospital());

			return ResponseEntity.ok(response);
		}

	}

	@PutMapping("/hospital")
	public @ResponseBody ResponseEntity<Response<Integer>> updateHospital(@RequestParam Integer idHospital,
			@RequestBody @Valid Hospital hospital, BindingResult result) {

		Response<Integer> response = new Response<Integer>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else if (!hospitalRepository.exists(idHospital)) {
			response.setData(null);
			response.addError("Não conseguimos encontrar este hospital");

			return ResponseEntity.badRequest().body(response);
		} else {

			hospital.setId_hospital(idHospital);
			hospitalRepository.save(hospital);
			response.setData(idHospital);

			return ResponseEntity.ok(response);
		}
	}

	@PostMapping("/inativarHospital")
	public @ResponseBody ResponseEntity<Response<Integer>> inativarHospital(@RequestBody Integer idHospital) {
		Response<Integer> response = new Response<Integer>();
		if (!hospitalRepository.exists(idHospital)) {
			response.setData(null);
			response.addError("Não conseguimos encontrar este hospital");

			return ResponseEntity.badRequest().body(response);
		} else {
			Hospital isolamento = hospitalRepository.findOne(idHospital);
			isolamento.setInativo(true);
			hospitalRepository.save(isolamento);

			response.setData(idHospital);
			return ResponseEntity.ok(response);
		}
	}

}
