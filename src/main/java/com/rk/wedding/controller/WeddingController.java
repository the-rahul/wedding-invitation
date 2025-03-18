package com.rk.wedding.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rk.wedding.model.Event;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class WeddingController {

	@Value("${gallery.photo.list}")
	private String photos;

	@GetMapping("/")
	public ModelAndView getHomePage(@RequestParam(name = "welcome", defaultValue = "Guest") String name) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("photoList", getPhotoList());
		modelAndView.addObject("eventsList", getEventsList());
		modelAndView.addObject("name", name);
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@GetMapping("/view/invite")
	public ModelAndView viewInvite() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("photoList", getInvitePhotoList());
		modelAndView.setViewName("view-invite");
		return modelAndView;
	}

	@GetMapping("/invite/download")
	public String downloadInvite() {
		String fileName = "khushboo-rahul-wedding-invitation.pdf";
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/downloadFile/").path(fileName)
				.toUriString();

	}

	@GetMapping("/invite/view")
	public ResponseEntity<InputStreamResource> viewLog(HttpServletRequest request) {
		// Load file as Resource
		try {
			String fileName = "khushboo-rahul-wedding-invitation.pdf";
			File file = new ClassPathResource(fileName, this.getClass().getClassLoader()).getFile();
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			HttpHeaders headers = new HttpHeaders();
			headers.add("content-disposition", "inline;filename=\"" + fileName + "\"");
			return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/pdf"))
					.contentLength(file.length()).headers(headers).body(resource);
		} catch (MalformedURLException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ResponseEntity.notFound().build();
	}

	private List<Event> getEventsList() {
		String venue = "Raj Laxmi Farm House, East Gola Road, Patna, Bihar.";
		String mapLocation = "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3597.568510680543!2d85.05882947551169!3d25.619250614476517!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x39ed5653bb8bd6e9%3A0x12cc3c03afe3829e!2sRaj%20Laxmi%20Farm%20House%20.!5e0!3m2!1sen!2sin!4v1728206486663!5m2!1sen!2sin";
		Event mehendi = new Event("mehendi", venue, "15 November 2024", "10:00 AM", mapLocation);
		Event haldi = new Event("haldi", venue, "15 November 2024", "3:00 PM", mapLocation);
		Event sangeet = new Event("sangeet", venue, "15 November 2024", "7:00 PM", mapLocation);
		Event wedding = new Event("wedding", venue, "16 November 2024", "4:00 PM", mapLocation);
		return Arrays.asList(mehendi, haldi, sangeet, wedding);
	}

	private List<String> getPhotoList() {
		return Arrays.asList(photos.split(",")).stream().map(p -> "/images/".concat(p).concat(".jpg")).toList();
	}

	private List<String> getInvitePhotoList() {
		return Arrays.asList("invitation-cover.png", "invitation-mehendi.png", "invitation-haldi.png",
				"invitation-sangeet.png", "invitation-wedding.png").stream().map("/images/"::concat).toList();
	}

}
