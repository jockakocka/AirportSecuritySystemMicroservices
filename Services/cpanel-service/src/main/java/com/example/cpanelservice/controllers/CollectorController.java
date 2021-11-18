package com.example.cpanelservice.controllers;

import com.example.cpanelservice.models.*;
import com.example.cpanelservice.services.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/cpanel/collector")
public class CollectorController {

  final
  CollectorService collectorService;

  public CollectorController(CollectorService collectorService) {
    this.collectorService = collectorService;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/frame")
  public ResponseEntity<Void> createFrame(@RequestBody Frame frame) throws IOException {
    collectorService.createFrame(frame);
    return new ResponseEntity<>(HttpStatus.OK);
  }


}
