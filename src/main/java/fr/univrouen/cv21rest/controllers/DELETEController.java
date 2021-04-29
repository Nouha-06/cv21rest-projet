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
import fr.univrouen.cv21rest.exceptions.ResourceNotFoundException;

@RestController
public class DELETEController {
	
	@Autowired
	private CVRepository cvRepository;

	@DeleteMapping("/delete/{id}")
	public String deleteCV(@PathVariable long id) {
		Optional<CV> cv = cvRepository.findById(id);
		if (cv.isPresent()) {
			CV cvDelete = cv.get();
			cvRepository.delete(cvDelete);
			JSONObject jsonObject = new JSONObject();
			jsonObject.append("id", Long.toString(id));
			jsonObject.append("status", "DELETED");
			return XML.toString(jsonObject);
		} else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.append("status","ERROR");
			return XML.toString(jsonObject);
		}
	}

}