package com.example.collectormicroservice.controllers;

import com.example.collectormicroservice.domains.Frame;
import com.example.collectormicroservice.services.CollectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/collector")
public class CollectorController {

  final
  CollectorService collectorService;

  public CollectorController(CollectorService collectorService) {
    this.collectorService = collectorService;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/frame")
  public ResponseEntity<Void> createAFrame(@RequestBody Frame frame) throws IOException {
    collectorService.createAFrame(frame);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
