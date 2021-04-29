package fr.univrouen.cv21rest.controllers;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import fr.univrouen.cv21rest.model.CV;
import fr.univrouen.cv21rest.model.CVResume;
import fr.univrouen.cv21rest.repositories.CVRepository;
import fr.univrouen.cv21rest.exceptions.ResourceAlreadyExistsException;
import fr.univrouen.cv21rest.exceptions.ResourceNotFoundException;

@RestController
public class POSTController {
	
	@Autowired
	private CVRepository cvRepository;

	@PostMapping("/insert")
	public CV insertCV(@RequestBody CV cv) {
		Optional<CV> checkCv = cvRepository.findById(cv.getId());
		if (checkCv.isPresent()) {
		throw new ResourceAlreadyExistsException("CV déjà existant.");
		} else {
			CV insertedCV = cvRepository.insert(cv);
			return insertedCV;
		}
	}
	
}