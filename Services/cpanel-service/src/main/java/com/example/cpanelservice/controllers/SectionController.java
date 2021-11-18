package com.example.cpanelservice.controllers;

import com.example.cpanelservice.models.*;
import com.example.cpanelservice.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/cpanel/section")
public class SectionController {

  final
  SectionService sectionService;

  public SectionController(SectionService sectionService) {
    this.sectionService = sectionService;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/persons")
  public ResponseEntity<Void> addPerson(@RequestBody SectionPerson sectionPerson) throws IOException {
    sectionService.postPerson(sectionPerson);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/persons")
  public ResponseEntity<String> getPersonsInATimeFrame(@RequestParam Map<String, String> params) throws IOException {
    return new ResponseEntity<>(sectionService.getPersonsInATimeFrame(params), HttpStatus.OK);
  }

}
