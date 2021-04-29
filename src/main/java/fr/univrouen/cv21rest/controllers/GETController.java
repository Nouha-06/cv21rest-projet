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
public class GETController {
	
	@Autowired
	private CVRepository cvRepository;

	@GetMapping("/")
	public ModelAndView index() {
		 ModelAndView page = new ModelAndView();
		 page.setViewName("index");
		 return page;
	}
	
	@GetMapping("/help")
	public ModelAndView help() {
		ModelAndView page = new ModelAndView();
		 page.setViewName("help");
		 return page;
	}
	
	
	@RequestMapping(value = "/resume", method = RequestMethod.GET, produces="application/xml")
	@ResponseBody
	public List<CVResume> getCVResume() throws JsonProcessingException {
		List<CV> cvs = cvRepository.findAll();
		List<CVResume> cvsResume = new ArrayList<CVResume>();
		for (CV cv : cvs) {
			CVResume cvResume = new CVResume(cv.getId(), cv.getIdentite(), cv.getObjectif());
			cvsResume.add(cvResume);
		}
		return cvsResume;
	}
	
	
	@GetMapping(value = "/cv/{id}", produces="application/xml")
	public String getCV(@PathVariable long id) throws JsonProcessingException {
		Optional<CV> cv = cvRepository.findById(id);
		JSONObject jsonObject = new JSONObject();
		if (cv.isPresent()) {
			CV returnCV = cv.get();
			jsonObject.append("cv21", new JSONObject(returnCV));
			
		} else {
			JSONObject errorObject = new JSONObject();
			errorObject.append("id", id);
			errorObject.append("status", "ERROR");
			jsonObject.append("error", errorObject);
		}
		return XML.toString(jsonObject);
	}
	
}